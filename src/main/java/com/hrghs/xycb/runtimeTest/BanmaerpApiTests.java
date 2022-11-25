package com.hrghs.xycb.runtimeTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.repositories.*;
import com.hrghs.xycb.services.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;

@Component
public class BanmaerpApiTests {
    @Autowired
    StoreService storeService;
    @Autowired
    AccountService accountService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    StorageService storageService;
    @Autowired
    SsoService ssoService;

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    AccountRepository accountRepository;
//    @Autowired
//    CategoryRepository categoryRepository;
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    StorageRepository storageRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void systemReady() throws JsonProcessingException {
        System.out.println("app is starting");
        //IP白名单可过，签名的方式不可过
//       httpClients.webClientWithBanmaMasterToken()
//               .flatMap(webClient -> webClient.method(HttpMethod.GET).retrieve().bodyToMono(String.class))
//               .subscribe(s -> System.out.println(s));
        //getStoreListMono();
//       getStoreList();
        saveStoreList();
//       getAccountList();
//       getCategoryList();
//       getOrderList();
//       getProductList();
//       getProductSkuList();
//       getProductTagsList();
//       getProductSuppliersList();
//       getStoragetList();
//        getStoreById();
//        getAccountById();
//        getCategoryById();
//        getOrderById();
//        getProductById();
//        getProductSkuById();
//        ssoPassport();
//        getDataAccess();
//        addAccount();
//        logoutAccount();
//        addProduct();
    }

    private void getStoreListMono() {
        Mono<BanmaErpResponseDTO<List<StoreDTO>>> storeList = storeService.getStoretListMono(null, null, null, 1,
                100, new DateTime("2020-12-01T00:00:00"), new DateTime("2021-01-01"), "CreateTime",
                null, null, null);
        System.out.println("getStoreList");
        storeList.subscribe(resp -> System.out.println(resp.getData().size()));
    }

    // 获取店铺列表
    private void getStoreList() {
        List<StoreDTO> storeList = storeService.getStoretList(null, null, BanmaerpPlatformEnums.Platform.Lazada, 1,
                100, null, null, "CreateTime",
                null, null, null);
        System.out.println("storeList size: " + storeList.size());
        if (storeList.size() > 0) {
            storeList.forEach(storeDTO -> System.out.println(storeDTO.getName()+storeDTO.getCreateTime()));
        }
        System.out.println("saving result........");
//        storeRepository.saveAll(storeList);
    }

    // 获取单个店铺
    private void getStoreById() {
        StoreDTO storeDTO = storeService.getStoreById("1492814222537527296", null);
        if (storeDTO != null) {
            System.out.println("storeDTO: " + storeDTO.getID());
        }
    }

    // 获取用户列表
    private void getAccountList() {
        List<AccountDTO> accountDTOList = accountService.getAccountList(null, null, null, null,
                1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("accountDTOList size: " + accountDTOList.size());
        if (accountDTOList.size() > 0) {
            accountDTOList.forEach(accountDTO -> System.out.println(accountDTO.getRealName() + "\tcreateTime:" + accountDTO.getCreateTime()));
//            accountRepository.saveAll(accountDTOList);
        }
    }

    // 获取单个用户

    private void getAccountById() {
        AccountDTO accountDTO = accountService.getAccountById(26455, null);
        if (accountDTO != null) {
            System.out.println("accountDTO: " + accountDTO.getRealName() + "\tcreateTime:" + accountDTO.getCreateTime());
            AccountDTO result = accountRepository.save(accountDTO);
            System.out.println(result.getPhone());
        }
    }

    // 添加子帐户
    private void addAccount() {
        BanmaErpResponseDTO<Boolean> banmaErpResponseDTO = accountService.addAccount(
                "13799812120", "1052712355@qq.com","蒋志鑫","采购部",false,null);
        if (banmaErpResponseDTO != null) {
            System.out.println("banmaErpResponseDTO: " + banmaErpResponseDTO.getData());
        }
    }

    // 注销帐户
    private void logoutAccount() {
        BanmaErpResponseDTO<Boolean> banmaErpResponseDTO = accountService.logoutAccount(
                111,null);
        if (banmaErpResponseDTO != null) {
            System.out.println("banmaErpResponseDTO: " + banmaErpResponseDTO.getData());
        }
    }

    // 查询用户店铺权限
    private void getDataAccess() {
        DataAccessDTO dataAccessDTO = accountService.getDataAccess(26455, null);
        if (dataAccessDTO != null) {
            System.out.println("dataAccessDTO: " + dataAccessDTO.getDataAccessMode());
        }
    }
    // 获取类目列表
    private void getCategoryList() {
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList(null, null, null,
                1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("categoryDTOList size: " + categoryDTOList.size());
        if (categoryDTOList.size() > 0) {
            categoryDTOList.forEach(categoryDTO -> System.out.println(categoryDTO.getID()));
        }
    }

    // 获取单个类目
    private void getCategoryById() {
        CategoryDTO categoryDTO = categoryService.getCategoryById("bd755fca-0c6f-4727-a39c-af4801414ca2", null);
        if (categoryDTO != null) {
            System.out.println("categoryDTO: " + categoryDTO.getID() + "\tcreateTime:" + categoryDTO.getCreateTime());
        }
    }

    // 获取订单列表
    private void getOrderList() {
        List<OrderDTO> orderDTOList = orderService.getOrderList(null, null, null, null, null, null,
                null, null, null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("orderDTOList size: " + orderDTOList.size());
        if (orderDTOList.size() > 0) {
            orderDTOList.forEach(orderDTO -> System.out.println(orderDTO.getMaster().getStoreID()));
        }
    }

    // 获取单个订单
    private void getOrderById() {
        OrderDTO orderDTO = orderService.getOrderById("1497122489925373952", null);
        if (orderDTO != null) {
            System.out.println("orderDTO: " + orderDTO.getMaster().getStoreID());
        }
    }


    // 获取文件列表
    private void getStoragetList() {
        List<StorageDTO> storageDTOList = storageService.getStoragetList(null, null, null,
                null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("storageDTOList size: " + storageDTOList.size());
        if (storageDTOList.size() > 0) {
            storageDTOList.forEach(storageDTO -> System.out.println(storageDTO.getID()));
        }
    }

    // 获取单个文件  404 NotFound
    private void getStorageById() {
        StorageDTO storageDTO = storageService.getStorageById("bd755fca-0c6f-4727-a39c-af4801414ca2", null);
        if (storageDTO != null) {
            System.out.println("storageDTO: " + storageDTO.getID() + "\tcreateTime:" + storageDTO.getCreateTime());
        }
    }

    // 获取产品列表
    private void getProductList() {
        List<ProductDTO> productDTOList = productService.getProductList(null, null, null,
                null, null, null, null, null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("productDTOList size: " + productDTOList.size());
        if (productDTOList.size() > 0) {
            productDTOList.forEach(productDTO -> System.out.println(productDTO.getSPU().getSPUID()));
        }
    }


    // 获取单个产品
    private void getProductById() {
        ProductDTO productDTO = productService.getProductById("1497121263854817280", null);
        if (productDTO != null) {
            System.out.println("productDTO: " + productDTO.getSPU().getSPUID());
        }
    }

    // 添加产品
    private void addProduct() {

        ProductDTO productDTO1 = new ProductDTO();
        ProductDTO productDTO = productService.addProduct(productDTO1, null);
        if (productDTO != null) {
            System.out.println("productDTO: " + productDTO.getSPU().getSPUID());
        }
    }


    // 获取sku列表
    private void getProductSkuList() {
        List<ProductSkusDTO> productSkusDTOList = productService.getProductSkuList(null, null, null,
                null, null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("productSkusDTOList size: " + productSkusDTOList.size());
        if (productSkusDTOList.size() > 0) {
            productSkusDTOList.forEach(productSkusDTO -> System.out.println(productSkusDTO.getSKUID()));
        }
    }

    // 获取单个sku
    private void getProductSkuById() {
        ProductSkusDTO productSkusDTO = productService.getProductSkuById("1497490072574889984", null);
        if (productSkusDTO != null) {
            System.out.println("productSkusDTO: " + productSkusDTO.getSKUID());
        }
    }

    // 获取供应商列表
    private void getProductSuppliersList() {
        List<ProductSuppliersInfoDTO> productSuppliersInfoDTOList = productService.getProductSuppliersList(null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("productSuppliersInfoDTOList size: " + productSuppliersInfoDTOList.size());
        if (productSuppliersInfoDTOList.size() > 0) {
            productSuppliersInfoDTOList.forEach(productSuppliersInfoDTO -> System.out.println(productSuppliersInfoDTO.getName()));
        }
    }

    // 获取tag列表
    private void getProductTagsList() {
        List<ProductTagsDTO> productTagsDTOList = productService.getProductTagsList(null, 1, 100, null, null,
                "CreateTime", null, null, null);
        System.out.println("productTagsDTOList size: " + productTagsDTOList.size());
        if (productTagsDTOList.size() > 0) {
            productTagsDTOList.forEach(productTagsDTO -> System.out.println(productTagsDTO.getName()));
        }
    }

    // 认证登录
    private void ssoPassport() {
        GetSsoPassportResponse ssoPassport = ssoService.ssoPassport("15060715230","110.87.105.142",0,0,null);
        if (ssoPassport != null) {
            System.out.println("ssoPassport: " + ssoPassport.getAccount());
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
        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(getStoretList, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_STORES);
        List<StoreDTO> storeDTOList =
                Arrays.stream(objects).map(o -> (StoreDTO) o)
                        .collect(Collectors.toList());
        System.out.println("saving result........");
        storeRepository.saveAll(storeDTOList);
        storeRepository.findAll().forEach(storeDTO -> System.out.println(storeDTO.getID()+"\t"+storeDTO.getName() ));
    }
}