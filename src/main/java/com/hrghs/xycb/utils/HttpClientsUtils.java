package com.hrghs.xycb.utils;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static com.hrghs.xycb.domains.BanmaerpProperties.*;
import static com.hrghs.xycb.domains.BanmaerpProperties.BANMA_HEADER_SIGNMETHOD;
import static com.hrghs.xycb.domains.Constants.IpAquireUrls;


public class HttpClientsUtils {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RestTemplate restTemplate;


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

    public RestTemplate restTemplateWithBanmaMasterToken(BanmaerpProperties banmaerpProperties){
        BanmaTokenUtils tokenUtils = applicationContext.getBean(BanmaTokenUtils.class);
        TokenResponseDTO tokenResponse = tokenUtils.getBanmaErpMasterToken(banmaerpProperties).block();
        String access_token = tokenResponse.getAccessToken();
        this.restTemplate.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add(BANMA_HEADER_ACCESSTOKEN, access_token);
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }));
        return restTemplate;
    }
    public String getLocalIp(){
        String localIp="";
        for (String ipAquireUrl: IpAquireUrls){
            try {
                URL url = new URL(ipAquireUrl);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                localIp = br.readLine();
                break;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return localIp;
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
