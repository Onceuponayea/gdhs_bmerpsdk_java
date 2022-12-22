package com.hrghs.xycb.aops;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.*;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    public RestTemplateInterceptor(){}

    public RestTemplateInterceptor(ReactiveRedisOperations<String, TokenResponseDTO> arg
            , ReactiveRedisOperations<String, BanmaerpProperties> arg2,BanmaTokenUtils arg3){
        this.tokenRespReactiveRedisOperations = arg;
        this.bmerp_props_redisOps = arg2;
        this.banmaTokenUtils = arg3;
    }

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);


    private ReactiveRedisOperations<String, TokenResponseDTO> tokenRespReactiveRedisOperations;
    private ReactiveRedisOperations<String, BanmaerpProperties> bmerp_props_redisOps;
    private BanmaTokenUtils banmaTokenUtils;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        List<String> appIds = request.getHeaders().get(BanmaerpProperties.BANMA_HEADER_APPID);
        if (!CollectionUtils.isEmpty(appIds)&&appIds.size()==1){
            String appId = appIds.get(0);
            String token_redisKey = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                    .concat(appId);
            String bmerpProps_redisKey_pattern = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_APPINFO).concat(COLON)
                    .concat(STAR).concat(DASH).concat(appId);
            String redisKey = bmerp_props_redisOps.keys(bmerpProps_redisKey_pattern).blockFirst();
            /** 先正常执行过一遍拿到response再根据结果判断是否需要retry **/
            ClientHttpResponse response = execution.execute(request,body);
            if (!StringUtils.hasText(redisKey)){
                logger.error("redis key {} has been mysteriously taken away, couldn't find {} !",bmerpProps_redisKey_pattern,redisKey);
            }else {
            /** 所有的retry请求都需要appID,从redis获取到 **/
                switch (response.getStatusCode()){
                    case UNAUTHORIZED:
                        response = retryUnauthorized(request,body,execution,redisKey);
                        break;
                }
            }
            return response;
        }else {
            return execution.execute(request,body);
        }
    }
    private ClientHttpResponse retryUnauthorized(HttpRequest request, byte[] body, ClientHttpRequestExecution execution
            ,String redisKey){
        BanmaerpProperties banmaerpProperties = bmerp_props_redisOps.opsForValue().get(redisKey).block();
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(1));
        TokenResponseDTO tokenResponseDTO =banmaTokenUtils.getBanmaErpMasterTokenMono(banmaerpProperties).block();
        tokenRespReactiveRedisOperations.opsForValue().set(BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                        .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID()),tokenResponseDTO, Duration.ofMillis(
                        (tokenResponseDTO.getAccessTokenExpiryTime().getMillis() - DateTime.now().getMillis() - 1000)))
                .subscribe();
        String accessToken = tokenResponseDTO.getAccessToken();
        request.getHeaders().set(BanmaerpProperties.BANMA_HEADER_ACCESSTOKEN,accessToken);
        return retryTemplate.execute(context -> {
            try {
                logger.info("access_token are different from redis, retry the request again!");
                return execution.execute(request,body);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }
}
