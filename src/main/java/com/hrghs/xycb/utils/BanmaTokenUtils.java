package com.hrghs.xycb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.enums.BanmaerpAuthEnums;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import static com.hrghs.xycb.config.BanmaerpProperties.*;
import static com.hrghs.xycb.domains.BanmaerpURL.banmaerp_GetToken_GET;
import static com.hrghs.xycb.domains.BanmaerpURL.banmaerp_RefreshToken_GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BanmaTokenUtils {

    private WebClient.Builder webClientBuilder = WebClient.builder();
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private  HttpClientsUtils httpClients;

    /**
     * todo 提供非阻塞式调用方式，返回结果序列化有问题，暂时不修复.
     * 调用之前确保BanmaerpProperties非空checkNull(BanmaerpProperties)
     * @param banmaerpProperties
     * @return
     */
    public Mono<TokenResponseDTO> getBanmaErpMasterTokenMono(BanmaerpProperties banmaerpProperties){
        BanmaerpSigningVO banmaerpSigningVO = banmaerpSigningVO(banmaerpProperties);
        return
        webClientBuilder.baseUrl(BanmaerpURL.banmaerp_gateway.concat(banmaerp_GetToken_GET)).build()
            .method(HttpMethod.GET)
                .accept(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8)
                .acceptCharset(Charset.defaultCharset())
                .headers(httpHeaders -> banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO))
        .retrieve()
        //.toEntity(new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
        .bodyToMono(new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
        .map(banmaErpResponseDTO -> banmaErpResponseDTO.getData());
    }

    public TokenResponseDTO getBanmaErpMasterToken(BanmaerpProperties banmaerpProperties){
        //todo retrieve from redis
        BanmaerpSigningVO banmaerpSigningVO = banmaerpSigningVO(banmaerpProperties);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders = banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        TokenResponseDTO tokenResponseDTO= httpClients.restTemplate()
                .exchange(BanmaerpURL.banmaerp_gateway.concat(banmaerp_GetToken_GET),HttpMethod.GET,
                        requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                .getBody().getData();


        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        String getTokenResponse = null;
        try {
            getTokenResponse = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(tokenResponseDTO);
            System.out.println(getTokenResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (tokenResponseDTO.getAccessTokenExpiryTime().isBeforeNow()){
            String refreshTokenUri =String.format(banmaerp_RefreshToken_GET,tokenResponseDTO.getRefreshToken());
            tokenResponseDTO= httpClients.restTemplate()
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(refreshTokenUri),HttpMethod.GET,
                            requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                    .getBody().getData();
        }
        //todo save it to redis
        return tokenResponseDTO;

    }
    public BanmaerpSigningVO banmaerpSigningVO(BanmaerpProperties banmaerpProperties){
        Long timestamp = System.currentTimeMillis()/1000L;
        BanmaerpSigningVO banmaerpSigningVO = new BanmaerpSigningVO();
        banmaerpSigningVO.setApp_id(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        banmaerpSigningVO.setApp_secret(banmaerpProperties.getX_BANMA_MASTER_APP_SECRET());
        banmaerpSigningVO.setSign_algorithm(banmaerpProperties.getX_BANMA_MASTER_SIGN_ALGORITHM());
        banmaerpSigningVO.setRequest_method(HttpMethod.GET);
        banmaerpSigningVO.setTimestamp(timestamp);
        banmaerpSigningVO.setRequest_path(banmaerp_GetToken_GET);
        return banmaerpSigningVO;
    }
    public HttpHeaders banmaerpCommonHeaders(HttpHeaders httpHeaders,BanmaerpProperties banmaerpProperties,BanmaerpSigningVO banmaerpSigningVO){
        httpHeaders.set(BANMA_HEADER_APPID,banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        httpHeaders.set(BANMA_HEADER_TIMESTAMP,String.valueOf(banmaerpSigningVO.getTimestamp()));
        if (banmaerpProperties.getX_BANMA_MASTER_SIGN_METHOD() != BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType()){
            httpHeaders.set(BANMA_HEADER_SIGNMETHOD,banmaerpProperties.getX_BANMA_MASTER_SIGN_ALGORITHM());
            httpHeaders.set(BANMA_HEADER_SIGN,encryptionUtils.banmaerpSigning(banmaerpSigningVO));
        }
        return httpHeaders;
    }
}
