package com.hrghs.xycb.runtimeTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.repositories.StoreRepository;
import com.hrghs.xycb.services.StoreService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;

@Component
public class BanmaerpApiTests  {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;

   @EventListener(ApplicationReadyEvent.class)
   public void systemReady() throws JsonProcessingException {
        System.out.println("app is starting");
        //IP白名单可过，签名的方式不可过
//       httpClients.webClientWithBanmaMasterToken()
//               .flatMap(webClient -> webClient.method(HttpMethod.GET).retrieve().bodyToMono(String.class))
//               .subscribe(s -> System.out.println(s));
       //getStoreListMono();
       //getStoreList();
       saveStoreList();
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

   private void saveStoreList() throws JsonProcessingException {
       String getStoretList = "{\n" +
               "    \"Code\": 200,\n" +
               "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
               "    \"Data\": {\n" +
               "        \"Stores\": [\n" +
               "            {\n" +
               "                \"ID\": \"400000333053000132\",\n" +
               "                \"Name\": \"test1\",\n" +
               "                \"Platform\": \"Wish\",\n" +
               "                \"CreateTime\": \"2019-01-10 16:57:58\",\n" +
               "                \"UpdateTime\": \"2020-06-13 09:59:25\"\n" +
               "            },\n" +
               "            {\n" +
               "                \"ID\": \"400025423071000141\",\n" +
               "                \"Name\": \"test2\",\n" +
               "                \"Platform\": \"Wish\",\n" +
               "                \"CreateTime\": \"2019-03-18 14:16:04\",\n" +
               "                \"UpdateTime\": \"2020-08-19 16:58:07\"\n" +
               "            }\n" +
               "        ],\n" +
               "        \"Page\": {\n" +
               "            \"PageNumber\": 1,\n" +
               "            \"PageCount\": 1,\n" +
               "            \"PageSize\": 20,\n" +
               "            \"TotalCount\": 6,\n" +
               "            \"HasMore\": false\n" +
               "        }\n" +
               "    },\n" +
               "    \"Success\": true,\n" +
               "    \"Message\": \"成功\"\n" +
               "}\n";
       ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
       BanmaErpResponseDTO<JsonNode> storeListRaw =objectMapper.readValue(getStoretList, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
       Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_STORES);
       List<StoreDTO> storeDTOList=
       Arrays.stream(objects).map(o -> (StoreDTO)o)
               .collect(Collectors.toList());
       System.out.println("saving result........");
       //storeRepository.saveAllAndFlush(storeDTOList);
       //storeRepository.saveAll(storeDTOList);
       storeService.saveStoreList(storeDTOList);
       storeRepository.findAll().forEach(storeDTO -> System.out.println("findAll- ID:\t "+ storeDTO.getID()));
   }
}
