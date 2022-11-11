package com.hrghs.xycb.config;

import com.hrghs.xycb.utils.BanmaTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.hrghs.xycb.config.BanmaerpProperties.*;
import static com.hrghs.xycb.config.BanmaerpProperties.BANMA_HEADER_SIGNMETHOD;

@Configuration
@Lazy
@Import({BanmaTokenUtils.class,BanmaerpProperties.class})
public class HttpClientConfigs {
    @Autowired
    private BanmaTokenUtils tokenUtils;
    @Autowired
    private BanmaerpProperties banmaerpProperties;
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
    @Bean
    @Qualifier("webClientWithBanmaMasterToken")
    public Mono<WebClient> webClientWithBanmaMasterToken(){
        return
                tokenUtils.getBanmaErpMasterToken(banmaerpProperties).map(getTokenResponse -> webClientBuilder()
                        .defaultHeader(BANMA_HEADER_APPID,banmaerpProperties.getX_BANMA_MASTER_APP_ID())
                        .defaultHeader(BANMA_HEADER_TIMESTAMP,String.valueOf(System.currentTimeMillis()/1000L))
                        .defaultHeader(BANMA_HEADER_ACCESSTOKEN, getTokenResponse.getAccessToken())
                        .defaultHeader(BANMA_HEADER_SIGNMETHOD,banmaerpProperties.getX_BANMA_MASTER_SIGN_METHOD())
                        .build());

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

    @Primary
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
