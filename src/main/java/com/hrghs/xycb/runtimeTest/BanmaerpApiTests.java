package com.hrghs.xycb.runtimeTest;

import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.services.StoreService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BanmaerpApiTests  {
    @Autowired
    StoreService storeService;

   @EventListener(ApplicationReadyEvent.class)
   public void systemReady(){
        System.out.println("app is starting");
        //IP白名单可过，签名的方式不可过
//       httpClients.webClientWithBanmaMasterToken()
//               .flatMap(webClient -> webClient.method(HttpMethod.GET).retrieve().bodyToMono(String.class))
//               .subscribe(s -> System.out.println(s));
       //getStoreListMono();
       getStoreList();
   }
   private void getStoreListMono(){
       Mono<BanmaErpResponseDTO<List<StoreDTO>>> storeList= storeService.getStoretListMono(null,null,null,1,
               100,new DateTime("2020-12-01T00:00:00"),new DateTime("2021-01-01"),"CreateTime",
               null,null,null);
       System.out.println("getStoreList");
       storeList.subscribe(resp -> System.out.println(resp.getData().size()));
   }
   private void getStoreList(){
//       List<StoreDTO> storeList =
//       storeService.getStoretList(null,null, BanmaerpPlatformEnums.Platform.Lazada,1,
//               100,new DateTime("2020-12-01T00:00:00"),new DateTime("2021-01-01"),"CreateTime",
//               null,null,null);
       List<StoreDTO> storeList = storeService.getStoretList(null,null, BanmaerpPlatformEnums.Platform.Lazada,1,
               100,null,null,"CreateTime",
               null,null,null);
       System.out.println("storeList size: " + storeList.size());
       if (storeList.size()>0){
           storeList.forEach(storeDTO -> System.out.println(storeDTO.getName()));
       }
   }
}
