package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.*;
import com.hrghs.xycb.services.ProductService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.COLON;
import static jodd.util.StringPool.UNDERSCORE;


public class ProductServiceImpl implements ProductService {

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    @Lazy
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSpuRepository productSpuRepository;
    @Autowired
    private ProductSuppliersRepository suppliersRepository;
    @Autowired
    private ProductTagsRepository productTagsRepository;
    @Autowired
    private ProductSkusRepository skusRepository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 查询产品列表
     *
     * @param spuIds          spuID，用逗号分隔
     * @param source          店铺ID
     * @param spu             spu code
     * @param categoryId      erp类目ID
     * @param title           标题
     * @param supplier        供应商名称
     * @param costPriceStart  成本价起始
     * @param costPriceEnd    成本价结束
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getProductList(String spuIds, String source, String spu, String categoryId, String title
            , String supplier, String costPriceStart, String costPriceEnd, Integer pageNumber, Integer pageSize
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy
            ,Boolean remote, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_productlist_GET, pageNumber, pageSize,
                searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<ProductDTO> productDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
        .getBody()
        .toDataList(BANMAERP_FIELD_PRODUCTS))
        .map(o -> (ProductDTO) o)
        .collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getProductList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getProductList(null,null,null,null,null,null,null,null
                ,pageNumber,null,null,null,null,null,
                null,remote,null);
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getProductList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getProductList(null,null,null,null,null,null,null,null
                ,pageNumber,pageSize,null,null,null,null,
                null,remote,null);
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getAndSaveProductList(Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        //todo getAndSaveProductList
        System.out.println("getAndSaveProductList.............in");
        List<ProductDTO> productDTOS =
        getProductList(null,null,null,null,null,null,null,null
                ,pageNumber,pageSize,null,null,null,null,
                null,true,banmaerpProperties);
        //save pageNumber,pageSize to Redis
        String redisKey =BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TASK).concat(COLON)
                .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        String value = BANMAERP_FIELD_PRODUCTS.concat(UNDERSCORE).concat(pageNumber.toString())
                .concat(UNDERSCORE).concat(pageSize.toString()).concat(UNDERSCORE).concat(LocalDateTime.now().toString());
        Set<String> bmerp_tasks_state = redisTemplate.opsForSet().members(redisKey);
        List<String> bmerp_tasks_newstate = bmerp_tasks_state.stream().filter(taskState -> !taskState.split(UNDERSCORE)[0].equalsIgnoreCase(BANMAERP_FIELD_PRODUCTS))
                .collect(Collectors.toList());
        bmerp_tasks_newstate.add(value);
        redisTemplate.opsForSet().add(redisKey,bmerp_tasks_newstate.toArray(new String[0]));
        saveALL(productDTOS,banmaerpProperties);
        return productDTOS;
    }


    /**
     * 查询单个产品
     *
     * @param spuId SPUID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public ProductDTO getProductBySpuId(Long spuId,Boolean remote, BanmaerpProperties banmaerpProperties) {
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_product_GET, spuId);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {})
                    .getBody().getData();
        }else {
            return productSpuRepository.findById(spuId).get().getProductDTO();
        }

    }

    @Override
    public ProductDTO getProductByProductUUID(String productUUID) {
        return productRepository.findById(UUID.fromString(productUUID)).get();
    }

    /**
     * 添加产品
     *
     * @param productDto 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<ProductDTO> insertProduct(ProductDTO productDto, BanmaerpProperties banmaerpProperties) {
        String apiUrl = BanmaerpURL.banmaerp_product_POST;
        BanmaErpResponseDTO<ProductDTO> erpResponseDTO = sendProductRequest(apiUrl, HttpMethod.POST, productDto, banmaerpProperties);
        if (erpResponseDTO.getSuccess()){
            save(erpResponseDTO.getData(),banmaerpProperties);
        }
        return erpResponseDTO;
    }

    /**
     * 更新产品
     *
     * @param productDto 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<ProductDTO> updateProductById(ProductDTO productDto,
                                                             BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_product_PUT, productDto.getSPU().getSPUID());
        BanmaErpResponseDTO<ProductDTO> erpResponseDTO = sendProductRequest(apiUrl, HttpMethod.PUT, productDto, banmaerpProperties);
        if (erpResponseDTO.getSuccess()){
            save(erpResponseDTO.getData(),banmaerpProperties);
        }
        return erpResponseDTO;
    }

    private BanmaErpResponseDTO<ProductDTO> sendProductRequest(String apiUrl, HttpMethod method, ProductDTO productDto,
                                                         BanmaerpProperties banmaerpProperties) {
        String requestBodyJson = null;
        try {
            requestBodyJson = objectMapper.writeValueAsString(productDto);
            JsonNode jsonNode = objectMapper.readTree(requestBodyJson);
            requestBodyJson = jsonNode.get(BANMAERP_FIELD_PRODUCT).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,method,apiUrl);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestBody = new HttpEntity(requestBodyJson, httpHeaders);
        return
        httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), method, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {})
                .getBody();
    }

    /**
     * 查询SKU列表
     *
     * @param skuIds          SKUID，用逗号分隔
     * @param code            sku code
     * @param spuId           SPUID
     * @param costPriceStart  成本价起始
     * @param costPriceEnd    成本价结束
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<ProductSkusDTO> getProductSkuList(String skuIds, String code, String spuId, String costPriceStart, String costPriceEnd
            , Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField
            , String sortField, String sortBy,Boolean remote, BanmaerpProperties banmaerpProperties) {
        List<ProductSkusDTO> productSkusDTOList;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.Banmaerp_product_skulist_GET, skuIds, spuId);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productSkusDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
            .getBody()
            .toDataList(BANMAERP_FIELD_SKUS))
            .map(o -> (ProductSkusDTO) o)
            .collect(Collectors.toList());
        }else {
            //todo 需要增加多条件查询
            productSkusDTOList=skusRepository.findAll(PageRequest.of(pageNumber,pageSize)).toList();
        }

        return productSkusDTOList;
    }

    /**
     * 查询单个SKU
     *
     * @param skuid SkuID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public ProductSkusDTO getProductSkuById(String skuid,Boolean remote, BanmaerpProperties banmaerpProperties) {
        ProductSkusDTO productSkusDTO;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.Banmaerp_product_sku_GET, skuid);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productSkusDTO =
            httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductSkusDTO>>() {})
                .getBody().getData();
        }else{
            productSkusDTO = skusRepository.findById(skuid).get();
        }
        return productSkusDTO;
    }

    /**
     * 查询Tag列表
     *
     * @param name            tag词名称
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<ProductTagsDTO> getProductTagsList(String name, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd,
        String searchTimeField, String sortField, String sortBy,Boolean remote, BanmaerpProperties banmaerpProperties) {
        List<ProductTagsDTO> productTagsDTOList = null;
        if (remote) {
            String apiUrl = String.format(BanmaerpURL.Banmaerp_taglist_GET, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productTagsDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                    .getBody()
                    .toDataList(BANMAERP_FIELD_TAGS))
                    .map(o -> (ProductTagsDTO) o)
                    .collect(Collectors.toList());
        }else{
            productTagsDTOList = productTagsRepository.findAll(PageRequest.of(pageNumber,pageSize)).toList();
        }
        return productTagsDTOList;
    }

    /**
     * 查询供应商列表
     *
     * @param name            供应商名称
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<ProductSuppliersInfoDTO> getProductSuppliersList(String name, Integer pageNumber, Integer pageSize, DateTime searchTimeStart,
        DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,Boolean remote,
        BanmaerpProperties banmaerpProperties) {
        List<ProductSuppliersInfoDTO> productSuppliersInfoDTOList = null;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.Banmaerp_supplierlist_GET, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productSuppliersInfoDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                .getBody()
                .toDataList(BANMAERP_FIELD_SUPPLIERS))
                .map(o -> (ProductSuppliersInfoDTO) o)
                .collect(Collectors.toList());
        }else{
            productSuppliersInfoDTOList =  suppliersRepository.findAll(PageRequest.of(pageNumber,pageSize)).toList();
        }

        return productSuppliersInfoDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> saveALL(List<ProductDTO> products,BanmaerpProperties banmaerpProperties) {
        List<ProductDTO> saveOrUpdateProducts = products ;
        List<Long> spuIds = products.parallelStream().map(productDTO -> productDTO.getSPU().getSPUID())
                .distinct().collect(Collectors.toList());
        List<ProductSpuDTO> existingProducts = productSpuRepository.findProductSpuDTOSBySPUIDIn(spuIds);
        for (int i=0; i < products.size(); i++){
            ProductDTO productDTO = products.get(i);
            for (ProductSpuDTO existingProduct : existingProducts){
                if (productDTO.getSPU().getSPUID().toString().equalsIgnoreCase(existingProduct.getSPUID().toString())){
                    productDTO.setProductUUId(existingProduct.getProductDTO().getProductUUId());
                }
            }
            saveOrUpdateProducts.set(i,productDTO);
        }
        saveOrUpdateProducts = productRepository.saveAll(products);
        saveOrUpdateProducts.forEach(productDTO -> {
            productDTO.getSPU().setProductDTO(productDTO);
            if (productDTO.getDescriptions()!=null){
                productDTO.getDescriptions().setProductDTO(productDTO);
            }
        });
        saveOrUpdateProducts = productRepository.saveAllAndFlush(saveOrUpdateProducts);
        return saveOrUpdateProducts;
    }

    @Transactional
    @Override
    @CheckBanmaerpProperties
    public ProductDTO save(ProductDTO productDTO,BanmaerpProperties banmaerpProperties) {
        Optional<ProductSpuDTO> optionalProductSpuDTO = productSpuRepository.findById(productDTO.getSPU().getSPUID());
        optionalProductSpuDTO.ifPresent(productSpuDTO -> productDTO.setProductUUId(productSpuDTO.getProductDTO().getProductUUId()));
        ProductDTO result =  productRepository.saveAndFlush(productDTO);
        //productRepository.saveAndFlush(result);
        return result;
    }

}
