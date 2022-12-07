package com.hrghs.xycb.runtimeTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.SsoRegisterResponse;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.repositories.*;
import com.hrghs.xycb.services.*;
import com.hrghs.xycb.utils.WebHookUtils;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.*;

@Component
public class BanmaerpApiTests {
    @Autowired
    StoreService storeService;
    @Autowired
    OrderService orderService;
    @Autowired
    StorageService storageService;
    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SsoService ssoService;
    @Autowired
    @Lazy
    @Qualifier(value = "jacksonObjectMapper")
    private ObjectMapper objectMapper;
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private ProductTagsRepository productTagsRepository;
    @Autowired
    private ProductSuppliersRepository productSuppliersRepository;
    @Autowired
    private BanmaerpPropertiesRepository banmaerpPropertiesRepository;
    @Autowired
    private OrderFulfillmentRepository fulfillmentRepository;
    @Autowired
    private OrderTrackingRepository trackingRepository;
    @Autowired
    private DataAccessRepository dataAccessRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductSpuRepository productSpuRepository;
    @Autowired
    private WebHookUtils wechatUtil;
//    @Autowired
//    @Lazy
//
//    private ObjectMapper jackson;

    @EventListener(ApplicationReadyEvent.class)
    public void systemReady() throws JsonProcessingException {
        System.out.println("app is starting");
        //IP白名单可过，签名的方式不可过
//       httpClients.webClientWithBanmaMasterToken()
//               .flatMap(webClient -> webClient.method(HttpMethod.GET).retrieve().bodyToMono(String.class))
//               .subscribe(s -> System.out.println(s));
        //getStoreListMono();
        //List<StoreDTO> storeDTOList = getStoreList();     //working
        //saveStoreList(storeDTOList);    //working
//        saveAccountList();
//        saveAccount();
//        uploadByBase64();   //working
        //saveProductList(); //working
//        saveOrderList();
//        getAccountList();
//        getAccountById();
//        getCategoryById();
//        getCategoryList();
//        getStoretList();
//        getStoreById();
//        getOrderById();
//        getOrderList();
//        getProductById();
//        getProductList();
//        getProductSkuList();
//        getProductSkuById();
//        getProductTagsList();
//        getProductSuppliersList();
//        addAccount();
//        register();
//        SSOlogin();
//        logout();
//        addProduct();
//        updateProduct();
//        getStorageById();
//        getFulfillments();
//        getTrackings();
//        getDataAccess();
//        getStorages();
//        wechatUtil.sendText("手机号已被注册","15000000100","1052@qq.com");
//        wechatUtil.qywxSendText("手机号已被注册","15000000100","1052@qq.com");
//        wechatUtil.ddSendText("手机号已被注册","15000000100","1052@qq.com");
//        saveProductList2();
//        saveProduct();
    }

    // 查询用户列表
    private void getAccountList() {
        List<AccountDTO> accountDTOList = accountService.getAccountList(null, null, null, null, 1
                , 100, null, null, null, null, null, true,null);
        if (accountDTOList.size() > 0) {
            List<AccountDTO> accountDTOList1 = accountService.saveAccountList(accountDTOList,null);
            accountDTOList1.forEach(accountDTO -> System.out.println("account-all:" + accountDTO.getID()));
        }
    }

    // 查询单个用户
    private void getAccountById() {
        AccountDTO accountDTO = accountService.getAccountById(26455, true,null);
        if (accountDTO != null) {
            AccountDTO accountDTO1 = accountService.saveAccount(accountDTO,null);
            System.out.println("account-all:" + accountDTO1.getID());
        }
    }


    // 查询分类列表
    private void getCategoryList() {
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList(null, null, null, 1
                , 100, null, null, null, null, null, true,null);
        if (categoryDTOList.size() > 0) {
            List<CategoryDTO> categoryDTOList1 = categoryService.saveCategoryList(categoryDTOList);
            categoryDTOList1.forEach(categoryDTO -> System.out.println("categoryDTO-all:" + categoryDTO.getID()));
        }
    }

    // 查询单个分类
    private void getCategoryById() {
        CategoryDTO categoryDTO = categoryService.getCategoryById("32781a0f-50bd-4fd5-b15a-af5500e69a33", true,null);
        if (categoryDTO != null) {
            CategoryDTO categoryDTO1 = categoryService.saveCategory(categoryDTO);
            System.out.println("categoryDTO-all:" + categoryDTO1.getID());
        }
    }

    // 查询店铺列表
    private void getStoretList() {
        List<StoreDTO> storeDTOList = storeService.getStoretList(null, null, null, 1
                , 3, null, null, null, null, null,true, null);
        if (storeDTOList.size() > 0) {
            List<StoreDTO> storeDTOList1 = storeService.saveStoreList(storeDTOList);
            storeDTOList1.forEach(storeDTO -> System.out.println("storeDTO-all:" + storeDTO.getID()));
        }
    }

    // 查询单个店铺
    private void getStoreById() {
        StoreDTO storeDTO = storeService.getStoreById("1492814222537527296",true, null);
        if (storeDTO != null) {
            StoreDTO storeDTO1 = storeService.saveStore(storeDTO);
            System.out.println("storeDTO-all:" + storeDTO1.getID());
        }
    }

    // 查询订单列表
    private void getOrderList() {
        List<OrderDTO> orderDTOList = orderService.getOrderList(null, null, null, null, null, null, null, null, null, 1
                , null, null, null, null, null, null,true, null);
        if (orderDTOList.size() > 0) {
            List<OrderDTO> orderDTOList1 = orderService.saveAll(orderDTOList);
            orderDTOList1.forEach(orderDTO -> System.out.println("orderDTO-all:" + orderDTO.getOrderUUID()));
        }
    }

    // 查询单个订单
    private void getOrderById() {
        OrderDTO orderDTO = orderService.getOrderById("1497122489925373952", false,null);
        if (orderDTO != null) {
            OrderDTO orderDTO1 = orderService.save(orderDTO);
            System.out.println("orderDTO-all:" + orderDTO1.getMaster().getID());
        }
    }

    // 查询产品列表
    private void getProductList() {
        List<ProductDTO> productDTOList = productService.getProductList(null, null, null, null, null, null, null, null, 1
                , 100, null, null, null, null, null,true, null);
        if (productDTOList.size() > 0) {
            List<ProductDTO> productDTOList1 = productService.saveALL(productDTOList,null);
            productDTOList1.forEach(productDTO -> System.out.println("productDTO-all:" + productDTO.getSPU().getSPUID()));
        }
    }

    // 查询单个产品
    private void getProductById() {
        ProductDTO productDTO = productService.getProductBySpuId(1497121263854817280l, true,null);
        if (productDTO != null) {
            ProductDTO productDTO1 = productService.save(productDTO,null);
            System.out.println("productDTO1-all:" + productDTO1.getSPU().getSPUID());
        }
    }

    // 查询sku列表
    private void getProductSkuList() {
        List<ProductSkusDTO> productSkusDTOList = productService.getProductSkuList(null, null, null, null, null, 1
                , 100, null, null, null, null, null, true,null);
        if (productSkusDTOList.size() > 0) {
            List<ProductSkusDTO> productDTOList1 = productSkusRepository.saveAllAndFlush(productSkusDTOList);
            productDTOList1.forEach(productSkusDTO -> System.out.println("productSkusDTO-all:" + productSkusDTO.getSKUID()));
        }
    }

    // 查询单个sku
    private void getProductSkuById() {
        ProductSkusDTO productSkusDTO = productService.getProductSkuById("1497490072574889984", true,null);
        if (productSkusDTO != null) {
            ProductSkusDTO productDTO1 = productSkusRepository.saveAndFlush(productSkusDTO);
            System.out.println("productSkusDTO-all:" + productDTO1.getSKUID());
        }
    }

    // 查询tag列表
    private void getProductTagsList() {
        List<ProductTagsDTO> productTagsDTOList = productService.getProductTagsList(null, 1
                , 100, null, null, null, null, null, true,null);
        if (productTagsDTOList.size() > 0) {
            List<ProductTagsDTO> productTagsDTOList1 = productTagsRepository.saveAllAndFlush(productTagsDTOList);
            productTagsDTOList1.forEach(productTagsDTO -> System.out.println("productTagsDTOList-all:" + productTagsDTO.getName()));
        }
    }

    // 查询供应商列表
    private void getProductSuppliersList() {
        List<ProductSuppliersInfoDTO> productSuppliersInfoDTOList = productService.getProductSuppliersList(null, 1
                , 100, null, null, null, null, null, true,null);
        if (productSuppliersInfoDTOList.size() > 0) {
            List<ProductSuppliersInfoDTO> productSuppliersInfoDTOList1 = productSuppliersRepository.saveAllAndFlush(productSuppliersInfoDTOList);
            productSuppliersInfoDTOList1.forEach(productSuppliersInfoDTO -> System.out.println("productSuppliersInfoDTO-all:" + productSuppliersInfoDTO.getName()));
        }
    }

    // 添加产品
    private void addProduct() throws JsonProcessingException {

        String insertProductReq = "{\n" +
                "    \"SPU\": {\n" +
                "        \"Code\": \"100500203449635822222\",\n" +
                "        \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫11111\",\n" +
                "        \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "        \"SourceUrl\": \"\",\n" +
                "        \"TagIDs\": \"\",\n" +
                "        \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "        \"Source\": \"平台同步\",\n" +
                "        \"Remark\": \"\",\n" +
                "        \"IsExemptQuality\": false\n" +
                "    },\n" +
                "    \"SKUs\": [\n" +
                "        {\n" +
                "            \"Code\": \"14:10#Red;5:2000009911111\",\n" +
                "            \"Specification\": \"款式1;4XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"4XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Code\": \"14:10#Red;5:2000009922221\",\n" +
                "            \"Specification\": \"款式1;5XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-af9640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 1,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"5XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Options\": [\n" +
                "        {\n" +
                "            \"Name\": \"款式1\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"款式1\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\": \"4XL\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"4XL\",\n" +
                "                \"5XL\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Images\": [\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "            \"Sort\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/ff600237-574e-4a93-a091-800.jpg\",\n" +
                "            \"Sort\": 1\n" +
                "        },\n" +
                "    ]，\n" +
                "    \"Tags\": [\n" +
                "        {\n" +
                "            \"Name\": \"热卖\"\n" +
                "        }，\n" +
                "        {\n" +
                "            \"Name\": \"热销\"\n" +
                "        }，\n" +
                "    ],\n" +
                "    \"Suppliers\": [\n" +
                "        {\n" +
                "            \"ID\": \"48F8184E-6A2B-443F-8FB4-EB777F7618D9\",\n" +
                "            \"Remark\": \"test\",\n" +
                "            \"Sort\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"ID\": \"B1A86AA7-665C-4196-8509-CFEDF366AA82\",\n" +
                "            \"Remark\": \"test1\",\n" +
                "            \"Sort\": 2\n" +
                "        },\n" +
                "    ]\n" +
                "}";

        ProductDTO productDTO = objectMapper.readValue(insertProductReq, new TypeReference<ProductDTO>() {});

        BanmaErpResponseDTO<ProductDTO> productDTOBanmaErpResponseDTO = productService.insertProduct(productDTO, null);
        if (productDTOBanmaErpResponseDTO != null) {
            ProductDTO save = productService.save(productDTOBanmaErpResponseDTO.getData(),null);
            System.out.println(save.getSPU().getSPUID());
        }
    }

    // 更新产品
    private void updateProduct() throws JsonProcessingException {

        String insertProductReq = "{\"Product\":{\"SPU\":{\"SPUID\":\"1499586936296198144\"," +
                "\"Code\":\"22112200212\",\"Title\":\"重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套潮牌宽松男装\"," +
                "\"LeiMuID\":\"32781a0f-50bd-4fd5-b15a-af5500e69a35\"," +
                "\"Image\":\"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\"" +
                ",\"Source\":\"手工创建\",\"DefaultSupplierID\":\"b4643438-15b5-4fee-9071-af5600fdece6\"" +
                ",\"Remark\":\"\",\"IsExemptQuality\":true,\"Keywords\":\"爆款\",\"CreateTime\":\"2022-11-22T14:25:24\"," +
                "\"UpdateTime\":\"2022-11-23T15:26:46\"}," +
                "\"Descriptions\":{\"Html\":\"<p><span style=\\\"font-family: &quot;PingFang SC&quot;; " +
                "font-size: 20px; font-weight: 900; background-color: rgb(255, 255, 255);\\\">重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装</span></p>\",\"Text\":\"\",\"Short\":\"\"}," +
                "\"SKUs\":[{\"SKUID\":\"1497490072578881985\",\"Code\":\"221122012r01-White-One-Size\",\"Specification\":\"White;One Size\",\"CostPrice\":112.0,\"Image\":\"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\"Weight\":60,\"Length\":60,\"Width\":60,\"Height\":60,\"IsValid\":true,\"Remark\":\"\",\"Sort\":0,\"Type\":\"普通\",\"CombineData\":[],\"Options\":[{\"Name\":\"Color\",\"Value\":\"White\"},{\"Name\":\"Size\",\"Value\":\"One Size\"}]}],\"Suppliers\":[{\"ID\":\"b4643438-15b5-4fee-9071-af5600fdece6\",\"Remark\":\"\",\"Sort\":0,\"Info\":{\"ID\":\"b4643438-15b5-4fee-9071-af5600fdece6\",\"Name\":\"zzz\",\"Contact\":\"将之心\",\"Phone\":\"13799820202\",\"Address\":\"福建省厦门市\",\"Remark\":\"\",\"Url\":\"\",\"QQ\":\"\",\"WeChat\":\"\",\"WangWang\":\"\",\"SettlementType\":\"货到付款\"}}]," +
                "\"Options\":[{\"Name\":\"Color\",\"Sort\":0,\"Values\":[\"White\"]}," +
                "{\"Name\":\"Size\",\"Sort\":0,\"Values\":[\"One Size\"]}],\"Requirements\":[],\"Sources\":[]," +
                "\"Images\":[{\"Src\":\"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\"," +
                "\"Sort\":0}],\"Tags\":[{\"Name\":\"休闲\"}],\"PackMaterials\":[{\"ID\":\"1497488256156045313\"," +
                "\"Name\":\"羊羔绒\",\"Remark\":\"\",\"Quantity\":1}]}}";

        ProductDTO productDTO = objectMapper.readValue(insertProductReq, new TypeReference<ProductDTO>() {
        });

        BanmaErpResponseDTO<ProductDTO> productDTOBanmaErpResponseDTO = productService.updateProductById(productDTO, null);
        if (productDTOBanmaErpResponseDTO != null) {
            ProductDTO save = productService.save(productDTOBanmaErpResponseDTO.getData(),null);
            System.out.println(save.getSPU().getSPUID());
        }
    }

    // 添加子账号
    private void addAccount() {
        AccountDTO accountDTO = accountService.addAccount(
                "13111919606",
                "13111919606@qq.com",
                "小蒋2",
                "采购部",
                false,
                null
        );
        System.out.println(accountDTO.getID() + "\t" + accountDTO.getPhone());
    }

    // 注销账号
    private void logout() {
        BanmaErpResponseDTO<Boolean> banmaErpResponseDTO = accountService.logoutAccount(26975, null);
        System.out.println(banmaErpResponseDTO.getSuccess() + "\t" + banmaErpResponseDTO.getMessage());
    }

    // SSO认证登录
    private void SSOlogin() {
        GetSsoPassportResponse ssoPassportResponse = ssoService.ssoPassport("15060715230", "120.41.226.177", 0, 0, null);
        System.out.println(ssoPassportResponse.getAccount() + "\t" + ssoPassportResponse.getClientIP());
    }

    // SSO注册账号
    private void register() throws JsonProcessingException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRealName("测试");
//        accountDTO.setPhone("18801013417");
        accountDTO.setPhone("13116911600");
        accountDTO.setDepartment("采购部");
        accountDTO.setEmail("13116911600@qq.com");
        AppsInfoDTO appsInfoDTO = new AppsInfoDTO();
        appsInfoDTO.setName("选品中心11");
        BanmaErpResponseDTO<SsoRegisterResponse> register = ssoService.register(accountDTO, appsInfoDTO, null);
    }

    // 查询文件列表
    private void getStorages() throws JsonProcessingException {
        String json = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12T15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storages\": [\n" +
                "            {\n" +
                "                \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "                \"Name\": \"61798bf2bb5a1.jpg\",\n" +
                "                \"Url\": \"https://storage.banmaerp.com/s/0/61798bf2bb5a1.jpg\",\n" +
                "                \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "                \"FileType\": \"Image\",\n" +
                "                \"Size\": \"108159\",\n" +
                "                \"CreateTime\": \"2019-02-16T17:32:37\",\n" +
                "                \"UpdateTime\": \"2020-04-03T22:31:37\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601234544\",\n" +
                "                \"Name\": \"8bf2bb5a1.jpg\",\n" +
                "                \"Url\": \"https://storage.banmaerp.com/s/0/8bf2bb5a1.jpg\",\n" +
                "                \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "                \"FileType\": \"Image\",\n" +
                "                \"Size\": \"108159\",\n" +
                "                \"CreateTime\": \"2019-02-16T17:32:37\",\n" +
                "                \"UpdateTime\": \"2020-04-03T22:31:37\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 2,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(json, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_STORAGES);
        List<StorageDTO> storageDTOList =
                Arrays.stream(objects).map(o -> (StorageDTO) o)
                        .collect(Collectors.toList());
        storageDTOList.forEach(storageDTO -> System.out.println("storageDTO:" + storageDTO.toString()));
        List<StorageDTO> storageDTOList1 = storageService.saveAll(storageDTOList);
        storageDTOList1.forEach(storageDTO -> System.out.println(storageDTO.toString()));

    }

    // 查询单个文件
    private void getStorageById() throws JsonProcessingException {
        StorageDTO storageById = storageService.getStorageById("fe68d741-55e4-49b3-8bbc-af5c00a800c4", true,null);
        System.out.println(objectMapper.writeValueAsString(storageById));
        if (storageById != null) {
            StorageDTO save = storageService.save(storageById);
            System.out.println("save:" + objectMapper.writeValueAsString(save));
        }

    }

    // 查询物流履约
    private void getFulfillments() throws JsonProcessingException {
        String json = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12T15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Fulfillments\": [{\n" +
                "            \"ExpressCompany\":\"YUNTU\",\n" +
                "            \"ExpressNo\":\"YT21134212220167\",\n" +
                "            \"DeliveryTime\":\"2021-08-24T14:36:43\"\n" +
                "        }]\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";
//        List<OrderFulfillmentDTO> fulfillments = orderService.getFulfillments("1497122489925373952", null);
//
//            System.out.println(objectMapper.writeValueAsString(fulfillments));

        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(json, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_FULFILLMENTS);
        List<OrderFulfillmentDTO> fulfillments =
                Arrays.stream(objects).map(o -> (OrderFulfillmentDTO) o)
                        .collect(Collectors.toList());
        fulfillments.forEach(orderFulfillmentDTO -> System.out.println("orderFulfillmentDTO:" + orderFulfillmentDTO.toString()));
        List<OrderFulfillmentDTO> orderFulfillmentDTOList = fulfillmentRepository.saveAllAndFlush(fulfillments);
        orderFulfillmentDTOList.forEach(orderFulfillmentDTO -> System.out.println(orderFulfillmentDTO.toString()));

    }

    // 查询物流追踪
    private void getTrackings() throws JsonProcessingException {
//        List<OrderTrackingDTO> trackingDTOList = orderService.getTrackings("1497122489925373952", null);
        String json = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": null,\n" +
                "    \"Time\": \"2022-11-29T11:59:25\",\n" +
                "    \"Data\": {\n" +
                "        \"Trackings\": [{\n" +
                "            \"ExpressNo\": \"YT21134212220167\",\n" +
                "            \"Status\": \"运输中\",\n" +
                "            \"ReceiptedTime\": null,\n" +
                "            \"TrackInfo\": [{\n" +
                "                \"Date\": \"2021-08-24T14:36:43\",\n" +
                "                \"Description\": \"货物电子信息已经收到\",\n" +
                "                \"Location\": \"\",\n" +
                "                \"Status\": null\n" +
                "            }],\n" +
                "            \"UpdateTime\": \"2021-12-22T16:52:29\"\n" +
                "        }]\n" +
                "    }\n" +
                "} \n";
        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(json, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_TRACKINGS);
        List<OrderTrackingDTO> trackingDTOList =
                Arrays.stream(objects).map(o -> (OrderTrackingDTO) o)
                        .collect(Collectors.toList());
        trackingDTOList.forEach(trackingDTO -> System.out.println("trackingDTO:" + trackingDTO.toString()));
        List<OrderTrackingDTO> orderTrackingDTOList = trackingRepository.saveAllAndFlush(trackingDTOList);
        orderTrackingDTOList.forEach(orderTrackingDTO -> System.out.println(orderTrackingDTO.toString()));

    }

    // 查询用户店铺权限
    private void getDataAccess() throws JsonProcessingException {
        String json = "{\n" +
                "        \"DataAccess\": {\n" +
                "            \"DataAccessMode\": \"指定权限\",\n" +
                "            \"Data\": [\n" +
                "                1317388665567055872,\n" +
                "                1328195640257159168,\n" +
                "                1335142862190288896\n" +
                "            ]\n" +
                "        }\n" +
                "}\n";
        DataAccessDTO dataAccessDTO = objectMapper.readValue(json, new TypeReference<DataAccessDTO>() {
        });
        System.out.println("dataAccessDTO:" + dataAccessDTO.toString());
        DataAccessDTO dataAccessDTO1 = dataAccessRepository.saveAndFlush(dataAccessDTO);
        System.out.println("dataAccessDTO1:" + dataAccessDTO1.toString());
    }

    private void getStoreListMono() {
        Mono<BanmaErpResponseDTO<List<StoreDTO>>> storeList = storeService.getStoretListMono(null, null, null, 1,
                100, new DateTime("2020-12-01T00:00:00"), new DateTime("2021-01-01"), "CreateTime",
                null, null, true,null);
        System.out.println("getStoreList");
        storeList.subscribe(resp -> System.out.println(resp.getData().size()));
    }

    private List<StoreDTO> getStoreList() {
        List<StoreDTO> storeList = storeService.getStoretList(null, null, BanmaerpPlatformEnums.Platform.Lazada, 1,
                100, null, null, "CreateTime",
                null, null,true, null);
        System.out.println("storeList size: " + storeList.size());
        if (storeList.size() > 0) {
            storeList.forEach(storeDTO -> System.out.println(storeDTO.getName()));
        }
        return storeList;
    }

    private void saveStoreList(List<StoreDTO> storeDTOList) throws JsonProcessingException {
        System.out.println("saving storeDTOList........");
        storeService.saveStoreList(storeDTOList);
//        storeRepository.findAll().forEach(storeDTO -> System.out.println("findAll- ID:\t " + storeDTO.getID()));
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
        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(accounts, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        JsonNode storesNode = storeListRaw.getData();
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_ACCOUNTS);
        List<AccountDTO> accountDTOS =
                Arrays.stream(objects).map(o -> (AccountDTO) o)
                        .collect(Collectors.toList());

        accountService.saveAccountList(accountDTOS,null);
    }
    private void saveAccount() throws JsonProcessingException {
        String account ="{\n" +
                "  \"Code\": 200,\n" +
                "  \"Time\": \"2021-12-12T15:23:34\",\n" +
                "  \"Data\": {\n" +
                "    \"Account\": {\n" +
                "      \"ID\": \"400024\",\n" +
                "      \"RealName\": \"test\",\n" +
                "      \"Email\": \"a@b.com\",\n" +
                "      \"Phone\": \"15000000000\",\n" +
                "      \"Department\": \"采购部\",\n" +
                "      \"CreateTime\": \"2019-01-10T16:57:58\",\n" +
                "      \"UpdateTime\": \"2020-06-13T09:59:25\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"Success\": true,\n" +
                "  \"Message\": \"成功\"\n" +
                "}\n";
        BanmaErpResponseDTO<AccountDTO> accountDTOBanmaErpResponseDTO = objectMapper.readValue(account, new TypeReference<BanmaErpResponseDTO<AccountDTO>>() {
        });
        System.out.println(accountDTOBanmaErpResponseDTO.getData().getID());
        System.out.println("product saveing.............");
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
        BanmaErpResponseDTO<JsonNode> storeListRaw = objectMapper.readValue(orders, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_ORDERS);
        List<OrderDTO> orderDTOS =
                Arrays.stream(objects).map(o -> (OrderDTO) o)
                        .collect(Collectors.toList());
        System.out.println(orderDTOS.size());
        orderService.saveAll(orderDTOS);
        System.out.println("orders has been saved...");
    }

    private void uploadByBase64() throws JsonProcessingException {
        String imgBase64 = "data:image/png;base64,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";
        BanmaErpResponseDTO<StorageDTO> banmaErpResponseDTO = storageService.uploadTheFileToBase64("keycloak.jpg", imgBase64, null);
        String result = objectMapper.writer().writeValueAsString(banmaErpResponseDTO);
        System.out.println(result);
    }

    private void saveProductList() throws JsonProcessingException {
        System.out.println("saving productlist to db.");
        String productLists = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Products\": [\n" +
                "            {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 1,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

//       BanmaErpResponseDTO<JsonNode> projectListRaw =objectMapper.readValue(productLists, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
//       Object[] objects = projectListRaw.toDataList(BANMAERP_FIELD_PRODUCTS);
//       List<ProductDTO> productDTOS=
//               Arrays.stream(objects).map(o -> (ProductDTO)o)
//                       .collect(Collectors.toList());
//       List<ProductDTO> result = productService.saveALL(productDTOS);
//       System.out.println("result: \t" +result.size());


        String product = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Suppliers\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"款式1\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"4XL\",\n" +
                "                        \"5XL\",\n" +
                "                        \"L\",\n" +
                "                        \"M\",\n" +
                "                        \"S\",\n" +
                "                        \"XL\",\n" +
                "                        \"XXL\",\n" +
                "                        \"3XL\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Requirements\": [],\n" +
                "            \"Sources\": [],\n" +
                "            \"Images\": [\n" +
                "                {\n" +
                "                    \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Sort\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Tags\": [],\n" +
                "            \"PackMaterials\": []\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        BanmaErpResponseDTO<ProductDTO> productDTOBanmaErpResponseDTO = objectMapper.readValue(product, new TypeReference<BanmaErpResponseDTO<ProductDTO>>() {
        });
        System.out.println(productDTOBanmaErpResponseDTO.getData().getSPU().getTitle());
        System.out.println("product saveing.............");

        //productService.save(productDTOBanmaErpResponseDTO.getData());
        System.out.println("product saved.............");


    }
    @SneakyThrows
    public void saveProductList2(){
        String products = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",\n" +
                "    \"Data\": {\n" +
                "        \"Products\": [\n" +
                "            {\n" +
                "                \"SPU\": {\n" +
                "                    \"SPUID\": \"1351482672542\",\n" +
                "                    \"Code\": \"100500203\",\n" +
                "                    \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                    \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Source\": \"平台同步\",\n" +
                "                    \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"IsExemptQuality\": false,\n" +
                "                    \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                    \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "                },\n" +
                "                \"Descriptions\": {\n" +
                "                    \"Html\": \"testlhtml\",\n" +
                "                    \"Text\": \"testText\",\n" +
                "                    \"Short\": \"testShort\"\n" +
                "                },\n" +
                "                \"SKUs\": [\n" +
                "                    {\n" +
                "                        \"SKUID\": \"1351482672974663680\",\n" +
                "                        \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                        \"Specification\": \"款式1;4XL\",\n" +
                "                        \"CostPrice\": 77.28,\n" +
                "                        \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                        \"Weight\": 0,\n" +
                "                        \"Length\": 0,\n" +
                "                        \"Width\": 0,\n" +
                "                        \"Height\": 0,\n" +
                "                        \"IsValid\": true,\n" +
                "                        \"Status\": \"正常\",\n" +
                "                        \"Remark\": \"\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"CombineData\": [],\n" +
                "                        \"Options\": [\n" +
                "                            {\n" +
                "                                \"Name\": \"款式1\",\n" +
                "                                \"Value\": \"款式1\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\": \"4XL\",\n" +
                "                                \"Value\": \"4XL\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Options\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"款式1\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Values\": [\n" +
                "                            \"款式1\"\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"4XL\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Values\": [\n" +
                "                            \"4XL\",\n" +
                "                            \"5XL\",\n" +
                "                            \"L\",\n" +
                "                            \"M\",\n" +
                "                            \"S\",\n" +
                "                            \"XL\",\n" +
                "                            \"XXL\",\n" +
                "                            \"3XL\"\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Images\": [\n" +
                "                    {\n" +
                "                        \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                        \"Sort\": 0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Suppliers\": [\n" +
                "                    {\n" +
                "                        \"ID\": \"48F8184E-6A2B-443F-8FB4-EB777F7618D9\",\n" +
                "                        \"Remark\": \"test\",\n" +
                "                        \"Sort\": 1\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"ID\": \"B1A86AA7-665C-4196-8509-CFEDF366AA82\",\n" +
                "                        \"Remark\": \"test1\",\n" +
                "                        \"Sort\": 2\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Tags\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"热卖\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"热销\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Requirements\": [],\n" +
                "                \"Sources\": [],\n" +
                "                \"PackMaterials\": []\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 1,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

        BanmaErpResponseDTO<JsonNode> projectListRaw = objectMapper.readValue(products, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {
        });
        Object[] objects = projectListRaw.toDataList(BANMAERP_FIELD_PRODUCTS);
        List<ProductDTO> productDTOS =
                Arrays.stream(objects).map(o -> (ProductDTO) o)
                        .collect(Collectors.toList());
        List<ProductDTO> result = productService.saveALL(productDTOS,null);
        System.out.println("saving product result...." + result.size());
    }
    @SneakyThrows
    public void saveProduct(){
        String productResp="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12T15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19T15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16T14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Suppliers\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"款式1\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"4XL\",\n" +
                "                        \"5XL\",\n" +
                "                        \"L\",\n" +
                "                        \"M\",\n" +
                "                        \"S\",\n" +
                "                        \"XL\",\n" +
                "                        \"XXL\",\n" +
                "                        \"3XL\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Requirements\": [],\n" +
                "            \"Sources\": [],\n" +
                "            \"Images\": [\n" +
                "                {\n" +
                "                    \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Sort\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Tags\": [],\n" +
                "            \"PackMaterials\": []\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        BanmaErpResponseDTO<ProductDTO> responseDTO = objectMapper.readValue(productResp, new TypeReference<BanmaErpResponseDTO<ProductDTO>>() {});
        productService.save(responseDTO.getData(),null);
        System.out.println("saving product result....");
    }
}
