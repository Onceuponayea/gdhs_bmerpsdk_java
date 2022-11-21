package com.hrghs.xycb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.hrghs.xycb.config.BanmaerpProperties.*;
import static com.hrghs.xycb.config.BanmaerpProperties.BANMA_HEADER_SIGNMETHOD;

@Component
@Lazy
public class HttpClientsUtils {
    @Autowired
    private ApplicationContext applicationContext;
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
    @Primary
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
    /**
     * 斑马erp主账号的access token
     * 一个选品中心的供应商对应斑马erp一个主账号，主账号通过appid, appsecret创建后能得到BanmaerpProperties对象
     * @param banmaerpProperties
     * @return
     */
    public Mono<WebClient> webClientWithBanmaMasterToken(BanmaerpProperties banmaerpProperties){
        BanmaTokenUtils tokenUtils = applicationContext.getBean(BanmaTokenUtils.class);
        return
                tokenUtils.getBanmaErpMasterTokenMono(banmaerpProperties).map(getTokenResponse -> webClientBuilder()
                        .defaultHeader(BANMA_HEADER_APPID,banmaerpProperties.getX_BANMA_MASTER_APP_ID())
                        .defaultHeader(BANMA_HEADER_TIMESTAMP,String.valueOf(System.currentTimeMillis()/1000L))
                        .defaultHeader(BANMA_HEADER_ACCESSTOKEN, getTokenResponse.getAccessToken())
                        .defaultHeader(BANMA_HEADER_SIGNMETHOD,banmaerpProperties.getX_BANMA_MASTER_SIGN_ALGORITHM())
                        .build());
    }
    /**
     * 默认选品中心平台的主账号
     * @return
     */
    public Mono<WebClient> webClientWithBanmaMasterToken(){
        BanmaerpProperties banmaerpProperties = applicationContext.getBean(BanmaerpProperties.class);
        return webClientWithBanmaMasterToken(banmaerpProperties);
    }
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(){
        restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }
    public RestTemplate restTemplateWithBanmaMasterToken(BanmaerpProperties banmaerpProperties){
        BanmaTokenUtils tokenUtils = applicationContext.getBean(BanmaTokenUtils.class);
        TokenResponseDTO tokenResponse = tokenUtils.getBanmaErpMasterToken(banmaerpProperties);
        String access_token = tokenResponse.getAccessToken();

        String getTokenResponse = null;
        try {
            getTokenResponse = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(tokenResponse);
            System.out.println(getTokenResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        restTemplate.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add(BANMA_HEADER_ACCESSTOKEN, access_token);
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }));
        return restTemplate;
    }
    //todo probably using client_credentials grant type
    public WebClient webClientWithBanmaEmployeeTokenViaClient_credentials(String client_id,String client_secret){
        return webClientBuilder()

                .build();
    }
    //todo probably using password grant type
    public WebClient webClientWithBanmaEmployeeTokenViaPassword(String username,String password){
        return webClientBuilder()

                .build();
    }
    //todo probably using authorization code grant type
    public WebClient webClientWithBanmaEmployeeTokenViaAuthCode(String username,String password){
        return webClientBuilder()

                .build();
    }
}
