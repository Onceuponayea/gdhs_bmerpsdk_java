package com.hrghs.xycb.aops;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
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
    public final static Integer REST_TEMPLATE_MAX_RETRIES=1;

    public RestTemplateInterceptor(ReactiveRedisOperations<String, TokenResponseDTO> arg
            , BanmaTokenUtils arg3, RedisTemplate<String,BanmaerpProperties> arg4){
        this.tokenRespReactiveRedisOperations = arg;
        this.banmaTokenUtils = arg3;
        this.redisTemplate =  arg4;
    }

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);


    private ReactiveRedisOperations<String, TokenResponseDTO> tokenRespReactiveRedisOperations;
    private BanmaTokenUtils banmaTokenUtils;
    private RedisTemplate<String,BanmaerpProperties> redisTemplate;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        List<String> appIds = request.getHeaders().get(BanmaerpProperties.BANMA_HEADER_APPID);
        if (!CollectionUtils.isEmpty(appIds)&&appIds.size()==1){
            String appId = appIds.get(0);
            String token_redisKey = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                    .concat(appId);
            String bmerpProps_redisKey_pattern = BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_APPINFO).concat(COLON)
                    .concat(STAR).concat(DASH).concat(appId);
            /** 先正常执行过一遍拿到response再根据结果判断是否需要retry
             * 1、unAuthorized
             * 2、java.net.UnknownHostException
             * 3、TOO_MANY_REQUESTS**/
            ClientHttpResponse response = execution.execute(request,body);
            logger.info("exception for request {}, either access_token are different from redis or too many request or remote host is offline" +
                    ", retry the request again!", request.getURI());
            String[] redisKeys_bmerpAppInfo = redisTemplate.keys(bmerpProps_redisKey_pattern).toArray(new String[0]);
            if (redisKeys_bmerpAppInfo.length==0){
                //未知原因导致redis取banma appInfo为空
                banmaTokenUtils.initBmerpAppInfos();
                redisKeys_bmerpAppInfo = redisTemplate.keys(bmerpProps_redisKey_pattern).toArray(new String[0]);
            }
            String redisKey_bmerpAppInfo = redisKeys_bmerpAppInfo[0];
            if (!StringUtils.hasText(redisKey_bmerpAppInfo)){
                logger.error("redis key for banmaerp property {} has been mysteriously taken away, couldn't find {} !",bmerpProps_redisKey_pattern,redisKey_bmerpAppInfo);
            }else {
            /** 所有的retry请求都需要appID,从redis获取到 **/
                switch (response.getStatusCode()){
                    //case FORBIDDEN:
                    case TOO_MANY_REQUESTS:
                    case UNAUTHORIZED:
                        response = retryUnauthorized(request,body,execution,redisKey_bmerpAppInfo);
                        break;
                }
            }
            return response;
        }else {
            return execution.execute(request,body);
        }
    }
    private ClientHttpResponse retryUnauthorized(HttpRequest request, byte[] body, ClientHttpRequestExecution execution
            ,String redisKey_bmerpAppInfo) throws IOException {
        BanmaerpProperties banmaerpProperties = redisTemplate.opsForValue().get(redisKey_bmerpAppInfo);
        RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(REST_TEMPLATE_MAX_RETRIES));
        retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());
        /** webclient,不走restTemplate, 如果还401, 则说明是要嘛ip要嘛账号真的不能登录 **/
        TokenResponseDTO tokenResponseDTO =banmaTokenUtils.getBanmaErpMasterTokenMono(banmaerpProperties).block();
        tokenRespReactiveRedisOperations.opsForValue().set(BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TOKEN).concat(COLON)
                        .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID()),tokenResponseDTO, Duration.ofMillis(
                        (tokenResponseDTO.getAccessTokenExpiryTime().getMillis() - DateTime.now().getMillis() - 1000)))
                .subscribe();
        String accessToken = tokenResponseDTO.getAccessToken();
        request.getHeaders().set(BanmaerpProperties.BANMA_HEADER_ACCESSTOKEN,accessToken);
        return retryTemplate.execute(context -> {
                logger.warn("exception for request {}, either access_token are different from redis or too many request or remote host is offline" +
                        ", retry the request again!", request.getURI());
                return execution.execute(request,body);
        });
    }
}
