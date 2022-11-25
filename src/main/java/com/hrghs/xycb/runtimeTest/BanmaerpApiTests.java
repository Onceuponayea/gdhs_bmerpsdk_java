package com.hrghs.xycb.runtimeTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.OrderDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.repositories.AccountRepository;
import com.hrghs.xycb.repositories.OrderRepository;
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

import static com.hrghs.xycb.domains.Constants.*;

@Component
public class BanmaerpApiTests  {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrderRepository orderRepository;

   @EventListener(ApplicationReadyEvent.class)
   public void systemReady() throws JsonProcessingException {
        System.out.println("app is starting");
        //IP白名单可过，签名的方式不可过
//       httpClients.webClientWithBanmaMasterToken()
//               .flatMap(webClient -> webClient.method(HttpMethod.GET).retrieve().bodyToMono(String.class))
//               .subscribe(s -> System.out.println(s));
       //getStoreListMono();
       //getStoreList();
      // saveStoreList();
      // saveAccountList();

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

   private void saveAccountList() throws JsonProcessingException {
       String accounts = "{\n" +
               "  \"Code\": 200,\n" +
               "  \"Time\": \"2021-12-12 15:23:34\",\n" +
               "  \"Data\": {\n" +
               "    \"Accounts\": [\n" +
               "      {\n" +
               "        \"ID\": \"400024\",\n" +
               "        \"RealName\": \"test1\",\n" +
               "        \"Email\": \"a@b.com\",\n" +
               "        \"Phone\": \"15000000000\",\n" +
               "        \"Department\": \"采购部\",\n" +
               "        \"CreateTime\": \"2019-01-10 16:57:58\",\n" +
               "        \"UpdateTime\": \"2020-06-13 09:59:25\"\n" +
               "      },\n" +
               "      {\n" +
               "        \"ID\": \"400025\",\n" +
               "        \"RealName\": \"test2\",\n" +
               "        \"Email\": \"a@b.com\",\n" +
               "        \"Phone\": \"15000000000\",\n" +
               "        \"Department\": \"采购部11\",\n" +
               "        \"CreateTime\": \"2019-03-18 14:16:04\",\n" +
               "        \"UpdateTime\": \"2020-08-19 16:58:07\"\n" +
               "      }\n" +
               "    ],\n" +
               "    \"Page\": {\n" +
               "      \"PageNumber\": 1,\n" +
               "      \"PageCount\": 1,\n" +
               "      \"PageSize\": 20,\n" +
               "      \"TotalCount\": 6,\n" +
               "      \"HasMore\": false\n" +
               "    }\n" +
               "  },\n" +
               "  \"Success\": true,\n" +
               "  \"Message\": \"成功\"\n" +
               "}";
       ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
       BanmaErpResponseDTO<JsonNode> storeListRaw =objectMapper.readValue(accounts, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
       JsonNode storesNode = storeListRaw.getData();
       Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_ACCOUNTS);
       List<AccountDTO> accountDTOS=
               Arrays.stream(objects).map(o -> (AccountDTO)o)
                       .collect(Collectors.toList());

       accountRepository.saveAll(accountDTOS);
   }
   private void saveOrderList() throws JsonProcessingException {
        String orders = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Orders\": [\n" +
                "            {\n" +
                "                \"Master\": {\n" +
                "                    \"ID\": \"123455\",\n" +
                "                    \"StoreID\": \"12345\",\n" +
                "                    \"Platform\": \"Shoplazza\",\n" +
                "                    \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-69\",\n" +
                "                    \"DisplayOrderID\": \"#1232\",\n" +
                "                    \"PayTime\": \"2020-06-19 12:13:43\",\n" +
                "                    \"PayChannel\": \"paypal\",\n" +
                "                    \"PayType\": 0,\n" +
                "                    \"PayAmount\": 278.64,\n" +
                "                    \"PayCurrency\": \"USD\",\n" +
                "                    \"PayAmountUSD\": 278.64,\n" +
                "                    \"PayAmountCNY\": 1974.42,\n" +
                "                    \"PayFreight\": 12.00,\n" +
                "                    \"PayFreightCurrency\": \"USD\",\n" +
                "                    \"PayFreightUSD\": 12.00,\n" +
                "                    \"PayFreightCNY\": 85.03,\n" +
                "                    \"RefundAmount\": 0.00,\n" +
                "                    \"RefundCurrency\": \"USD\",\n" +
                "                    \"RefundAmountUSD\": 0.00,\n" +
                "                    \"RefundAmountCNY\": 0.00,\n" +
                "                    \"DiscountAmount\": 0.00,\n" +
                "                    \"DiscountCurrency\": \"USD\",\n" +
                "                    \"Status\": \"待发货\",\n" +
                "                    \"PayStatus\": \"未付款\",\n" +
                "                    \"ShippingType\": \"yunfei1\",\n" +
                "                    \"CountryCode\": \"CN\",\n" +
                "                    \"HoldStatus\": \"未暂停\",\n" +
                "                    \"RefundStatus\": \"无退款\",\n" +
                "                    \"Quantity\": 3,\n" +
                "                    \"PurchaseFreight\": 0.00,\n" +
                "                    \"UserIndentity\": \"111@qq.com\",\n" +
                "                    \"OrderTime\": \"2020-06-19 12:09:34\",\n" +
                "                    \"Tags\": [],\n" +
                "                    \"OriginalOrderStatus\": \"waiting\",\n" +
                "                    \"OriginalOrderTime\": \"2020-06-19 04:09:34\",\n" +
                "                    \"OriginalPayTime\": \"2020-06-19 04:13:43\",\n" +
                "                    \"HaveMessage\": false,\n" +
                "                    \"HaveRemark\": false,\n" +
                "                    \"LatestDeliveryTime\": null,\n" +
                "                    \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                    \"UpdateTime\": \"2021-10-16 14:32:46\",\n" +
                "                    \"IsCOD\": true,\n" +
                "                    \"Flags\": [\n" +
                "                        \"货到付款\"\n" +
                "                    ],\n" +
                "                    \"Email\": \"111@qq.com\",\n" +
                "                    \"InventoryStatus\": \"有货\",\n" +
                "                    \"InventoryMode\": \"本地仓库存\",\n" +
                "                    \"TxNo\": \"\",\n" +
                "                    \"BuyerName\": \"xxx\",\n" +
                "                    \"Message\": \"\",\n" +
                "                    \"ShippingTime\": null,\n" +
                "                    \"OriginalShippingTime\": null,\n" +
                "                    \"OriginalTags\": [],\n" +
                "                    \"BuyerPhone\": \"\"\n" +
                "                },\n" +
                "                \"Details\": [\n" +
                "                    {\n" +
                "                        \"ID\": \"51012f7d-00be-4773-83a4-abdf\",\n" +
                "                        \"PackageID\": \"135144319\",\n" +
                "                        \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-691c\",\n" +
                "                        \"OriginalDetailID\": \"297da93a-cf7a-4d67-9f7f-16b0\",\n" +
                "                        \"OriginalSKU\": \"12334\",\n" +
                "                        \"SPUID\": \"12334\",\n" +
                "                        \"SKUID\": \"12334\",\n" +
                "                        \"SKUType\": \"普通\",\n" +
                "                        \"SKUCode\": \"black\",\n" +
                "                        \"SKUSpecification\": \"black\",\n" +
                "                        \"Price\": 88.88,\n" +
                "                        \"Title\": \"Title\",\n" +
                "                        \"OriginalImage\": \"//cdn.xxx.com/7ace6e4281b267e0.jpg\",\n" +
                "                        \"Quantity\": 1,\n" +
                "                        \"Amount\": 177.76,\n" +
                "                        \"Status\": \"已取消\",\n" +
                "                        \"PriceCurrency\": \"USD\",\n" +
                "                        \"Properties\": [],\n" +
                "                        \"OriginalProductID\": \"BE0D575F-23E6-4045-91\",\n" +
                "                        \"OriginalSKUID\": \"F54570AA-207E-4B29-B6B\",\n" +
                "                        \"WarehouseID\": \"af3de541-8bdb-4754-99af-f89\",\n" +
                "                        \"InventoryStatus\": \"未知\",\n" +
                "                        \"InventoryLockQuantity\": 0,\n" +
                "                        \"SkuChangeStatus\": \"未变更\",\n" +
                "                        \"OriginalSpecification\": \"White\",\n" +
                "                        \"SKUImage\": \"https://www.xxx.com/0456ebc048fc.jpg\",\n" +
                "                        \"InventoryData\": [\n" +
                "                            {\n" +
                "                                \"SKUID\": \"1234\",\n" +
                "                                \"SKUCode\": \"black\",\n" +
                "                                \"ShortageQuantity\": 0,\n" +
                "                                \"LockQuantity\": 1,\n" +
                "                                \"SPUID\": \"12345\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"Sort\": 1,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"ProductUrl\": \"\",\n" +
                "                        \"QuantityStatus\": \"已调整\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 10,\n" +
                "            \"PageSize\": 1,\n" +
                "            \"TotalCount\": 10,\n" +
                "            \"HasMore\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";
       ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
       BanmaErpResponseDTO<JsonNode> storeListRaw =objectMapper.readValue(orders, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
       Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_ORDERS);
       List<OrderDTO> orderDTOS=
               Arrays.stream(objects).map(o -> (OrderDTO)o)
                       .collect(Collectors.toList());
       System.out.println(orderDTOS.size());
       orderRepository.saveAll(orderDTOS);
   }
}
