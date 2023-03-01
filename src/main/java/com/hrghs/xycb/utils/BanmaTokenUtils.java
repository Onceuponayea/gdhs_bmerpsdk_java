package com.hrghs.xycb.utils;

import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.enums.BanmaerpAuthEnums;
import com.hrghs.xycb.services.BanmaerpPropertiesService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.TimeZone;

import static com.hrghs.xycb.domains.BanmaerpProperties.*;
import static com.hrghs.xycb.domains.BanmaerpURL.banmaerp_GetToken_GET;
import static com.hrghs.xycb.domains.BanmaerpURL.banmaerp_RefreshToken_GET;
import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.COLON;
import static jodd.util.StringPool.QUESTION_MARK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class BanmaTokenUtils {
    private final static Logger logger = LoggerFactory.getLogger(BanmaTokenUtils.class);
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;

    @Autowired
    @Qualifier(value = "bmerp_restTemplate")
    @Lazy
    private RestTemplate restTemplate;

    @Autowired
    @Lazy
    private ReactiveRedisOperations<String,TokenResponseDTO> tokenRespReactiveRedisOperations;

    @Autowired
    @Lazy
    private BanmaerpPropertiesService banmaerpPropertiesService;
    /**
     * @param banmaerpProperties
     * @return
     */
    @Deprecated
    @CheckBanmaerpProperties
    public  Mono<TokenResponseDTO> getBanmaErpMasterTokenMono(BanmaerpProperties banmaerpProperties){
        BanmaerpSigningVO banmaerpSigningVO = banmaerpSigningVO(banmaerpProperties, HttpMethod.GET,banmaerp_GetToken_GET,null);
        WebClient.Builder webClientBuilder = WebClient.builder();
        String redisKey = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        WebClient.RequestBodySpec requestBodySpec =
        webClientBuilder.baseUrl(BanmaerpURL.banmaerp_gateway.concat(banmaerp_GetToken_GET)).build()
                .method(HttpMethod.GET)
                .accept(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8)
                .acceptCharset(Charset.defaultCharset())
                .headers(httpHeaders -> banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO));
        return requestBodySpec.retrieve()
                //.toEntity(new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                .bodyToMono(new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                .map(bmerpResponse -> validateToken(bmerpResponse.getData(),banmaerpProperties,redisKey));
                //.map(banmaErpResponseDTO -> banmaErpResponseDTO.getData());
    }

    public void initBmerpAppInfos(){
        banmaerpPropertiesService.initBmerpAppInfos();
    }
    /**
     * get token from redis and check its validity when it's present in redis,
     * otherwise retrieve token from banmaerp via api
     * @param banmaerpProperties
     * @return
     */
    public Mono<TokenResponseDTO> getBanmaErpMasterToken(BanmaerpProperties banmaerpProperties){
        String redisKey = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        BanmaerpSigningVO banmaerpSigningVO = banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,banmaerp_GetToken_GET,null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders = banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        return
        tokenRespReactiveRedisOperations.opsForValue().get(redisKey)
            .map(tokenResponseDTO -> validateToken(tokenResponseDTO,banmaerpProperties,redisKey))
            .switchIfEmpty(Mono.defer(() -> {
                logger.info("token info for {} does not exist in redis, request token from banmaerp!",banmaerpProperties.getX_BANMA_MASTER_APP_ID());
                TokenResponseDTO tokenResponseDTO = restTemplate.exchange(BanmaerpURL.banmaerp_gateway.concat(banmaerp_GetToken_GET),HttpMethod.GET,
                                requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                        .getBody().getData();
                tokenResponseDTO = validateToken(tokenResponseDTO,banmaerpProperties,redisKey);
                return Mono.just(tokenResponseDTO);
            }));
    }
    private TokenResponseDTO validateToken(TokenResponseDTO _tokenResponseDTO,BanmaerpProperties banmaerpProperties,String redisKey){
        TokenResponseDTO tokenResponseDTO = _tokenResponseDTO;
        //if (tokenResponseDTO.getAccessTokenExpiryTime().toLocalDateTime().isBefore(LocalDateTime.now())){UTC
        if (tokenResponseDTO.getAccessTokenExpiryTime().isBefore(DateTime.now())){
            String refreshTokenUri =String.format(banmaerp_RefreshToken_GET,tokenResponseDTO.getRefreshToken());
            BanmaerpSigningVO banmaerpSigningVO = banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,refreshTokenUri,null);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders = banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            tokenResponseDTO = restTemplate.exchange(BanmaerpURL.banmaerp_gateway.concat(refreshTokenUri),HttpMethod.GET,
                            requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {})
                    .getBody().getData();
        }
        long timeDiff = tokenResponseDTO.getAccessTokenExpiryTime().getMillis() - DateTime.now().getMillis() - 60000;
        tokenRespReactiveRedisOperations.opsForValue()
                .set(redisKey,tokenResponseDTO, Duration.ofMillis(timeDiff))
                .subscribe();
        return tokenResponseDTO;
    }
    public  HttpHeaders banmaerpCommonHeaders(HttpHeaders httpHeaders,BanmaerpProperties banmaerpProperties,BanmaerpSigningVO banmaerpSigningVO){
        httpHeaders.set(BANMA_HEADER_APPID,banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        httpHeaders.set(BANMA_HEADER_TIMESTAMP,String.valueOf(banmaerpSigningVO.getTimestamp()));
        if (banmaerpProperties.getX_BANMA_MASTER_SIGN_METHOD() != BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType()){
            httpHeaders.set(BANMA_HEADER_SIGNMETHOD,banmaerpProperties.getX_BANMA_MASTER_SIGN_ALGORITHM());
            httpHeaders.set(BANMA_HEADER_SIGN,encryptionUtils.banmaerpSigning(banmaerpSigningVO));
        }
        return httpHeaders;
    }


    public BanmaerpSigningVO banmaerpSigningVO(BanmaerpProperties banmaerpProperties,HttpMethod httpMethod, String url,String requestBodyInJsonString) {
        Long timestamp = System.currentTimeMillis() / 1000L;
        BanmaerpSigningVO banmaerpSigningVO = new BanmaerpSigningVO();
        banmaerpSigningVO.setApp_id(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        banmaerpSigningVO.setApp_secret(banmaerpProperties.getX_BANMA_MASTER_APP_SECRET());
        banmaerpSigningVO.setSign_algorithm(banmaerpProperties.getX_BANMA_MASTER_SIGN_ALGORITHM());
        banmaerpSigningVO.setRequest_method(httpMethod);
        banmaerpSigningVO.setTimestamp(timestamp);
        if (StringUtils.hasText(url)) {
            if (url.contains(QUESTION_MARK)){
                String s[] = url.split("\\?");
                banmaerpSigningVO.setRequest_path(s[0]);
                banmaerpSigningVO.setRequest_query(s[1]);
            }
            else {
                banmaerpSigningVO.setRequest_path(url);
            }
        }
        if (StringUtils.hasText(requestBodyInJsonString)){
            banmaerpSigningVO.setRequest_body(requestBodyInJsonString);
        }
        return banmaerpSigningVO;
    }
}
