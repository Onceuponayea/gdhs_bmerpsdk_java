package com.hrghs.xycb.utils;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.GetTokenResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;

import static com.hrghs.xycb.config.BanmaerpProperties.*;
import static com.hrghs.xycb.domains.BanmaerpURL.banmaerp_GetToken_GET;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class BanmaTokenUtils {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private EncryptionUtils encryptionUtils;
    public Mono<GetTokenResponse> getBanmaErpMasterToken(BanmaerpProperties banmaerpProperties){
        Long timestamp = System.currentTimeMillis()/1000L;
        BanmaerpSigningVO banmaerpSigningVO = new BanmaerpSigningVO();
        banmaerpSigningVO.setApp_id(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        banmaerpSigningVO.setApp_secret(banmaerpProperties.getX_BANMA_MASTER_APP_SECRET());
        banmaerpSigningVO.setSign_method(banmaerpProperties.getX_BANMA_MASTER_SIGN_METHOD());
        banmaerpSigningVO.setRequest_method(HttpMethod.GET);
        banmaerpSigningVO.setTimestamp(timestamp);
        banmaerpSigningVO.setRequest_path(banmaerp_GetToken_GET);

        return
        webClientBuilder.baseUrl(BanmaerpURL.banmaerp_gateway.concat(banmaerp_GetToken_GET)).build()
            .method(HttpMethod.GET)
            .headers(httpHeaders -> {
                httpHeaders.set(BANMA_HEADER_APPID,banmaerpProperties.getX_BANMA_MASTER_APP_ID());
                httpHeaders.set(BANMA_HEADER_TIMESTAMP,String.valueOf(timestamp));
                httpHeaders.set(BANMA_HEADER_SIGNMETHOD,banmaerpProperties.getX_BANMA_MASTER_SIGN_METHOD());
            try {
                httpHeaders.set(BANMA_HEADER_SIGN,encryptionUtils.banmaerpSigning(banmaerpSigningVO));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        })
        .retrieve()
        .bodyToMono(BanmaErpResponseDTO.class)
        .map(banmaErpResponseDTO -> (GetTokenResponse)banmaErpResponseDTO.getData());
    }
}
