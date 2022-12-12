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
import com.hrghs.xycb.utils.BanmaParamsUtils;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
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
    private BanmaEncryptionUtils encryptionUtils;
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
    private ProductSupplierInfoRepository supplierInfoRepository;
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
        List<ProductDTO> productDTOList;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        pageNumber = BanmaParamsUtils.checkPageNum(pageNumber);
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_productlist_GET, spuIds,source,spu,categoryId,title,supplier,costPriceStart,costPriceEnd,pageNumber, pageSize,
                    searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(BANMAERP_FIELD_PRODUCTS))
                    .map(o -> (ProductDTO) o)
                    .collect(Collectors.toList());
            productDTOList.forEach(productDTO -> productDTO.setBanmaerpProperties(banmaerpProperties));
        }else{
            Specification<ProductDTO> specification = createSpecification(spuIds, source, spu, categoryId, title, supplier, costPriceStart, costPriceEnd, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            productDTOList = productRepository.findAll(specification,PageRequest.of(pageNumber,pageSize)).toList();
        }
        return productDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getProductList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getProductList(null,null,null,null,null,null,null,null
                ,pageNumber,null,null,null,null,null,
                null,remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getProductList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getProductList(null,null,null,null,null,null,null,null
                ,pageNumber,pageSize,null,null,null,null,
                null,remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> getAndSaveProductList(Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        pageNumber = BanmaParamsUtils.checkPageNum(pageNumber);
        List<ProductDTO> productDTOS =
                getProductList(null,null,null,null,null,null,null,null
                        ,pageNumber,pageSize,null,null,null,null,
                        null,true,banmaerpProperties);
        String redisKey =BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TASK).concat(COLON)
                .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        String value = BANMAERP_FIELD_PRODUCTS.concat(UNDERSCORE).concat(pageNumber.toString())
                .concat(UNDERSCORE).concat(pageSize.toString()).concat(UNDERSCORE).concat(LocalDateTime.now().toString());
        Set<String> bmerp_tasks_state = redisTemplate.opsForSet().members(redisKey);
        List<String> bmerp_tasks_newstate = bmerp_tasks_state.stream().filter(taskState -> !taskState.split(UNDERSCORE)[0].equalsIgnoreCase(BANMAERP_FIELD_PRODUCTS))
                .collect(Collectors.toList());
        bmerp_tasks_newstate.add(value);
        redisTemplate.opsForSet().add(redisKey,bmerp_tasks_newstate.toArray(new String[0]));
        saveProducts(productDTOS,banmaerpProperties);
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
            ProductDTO productDTO= httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {})
                    .getBody().getData();
            productDTO.setBanmaerpProperties(banmaerpProperties);
            return productDTO;
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
            saveProduct(erpResponseDTO.getData(),banmaerpProperties);
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
            saveProduct(erpResponseDTO.getData(),banmaerpProperties);
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
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        pageNumber = BanmaParamsUtils.checkPageNum(pageNumber);
        if (remote){
            String apiUrl = String.format(BanmaerpURL.Banmaerp_product_skulist_GET, skuIds,code, spuId,costPriceStart,costPriceEnd,pageNumber,pageSize,searchTimeStart,searchTimeEnd,searchTimeField,sortField,sortBy);
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
            productSkusDTOList.forEach(productSkusDto -> productSkusDto.setBanmaerpProperties(banmaerpProperties));
        }else {
            Specification<ProductSkusDTO> specification = createSpecification(skuIds, code, spuId, costPriceStart, costPriceEnd, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            productSkusDTOList=skusRepository.findAll(specification,PageRequest.of(pageNumber,pageSize)).toList();
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
            productSkusDTO.setBanmaerpProperties(banmaerpProperties);
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
            String apiUrl = String.format(BanmaerpURL.Banmaerp_taglist_GET, name,pageNumber,pageSize,searchTimeStart,searchTimeEnd,searchTimeField,sortField,sortBy);
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
            Specification<ProductTagsDTO> specification = createSpecification(name, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            productTagsDTOList = productTagsRepository.findAll(specification,PageRequest.of(pageNumber,pageSize)).toList();
        }
        return productTagsDTOList;
    }

    /**
     * todo 需要根据产品ID来查看供应商，这样才能把供应商和产品绑定起来
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
    public List<ProductSuppliersDTO> getProductSuppliersList(String name, Integer pageNumber, Integer pageSize, DateTime searchTimeStart,
                                                             DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,Boolean remote,
                                                             BanmaerpProperties banmaerpProperties) {
        List<ProductSuppliersDTO> productSuppliersDTOList = null;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        pageNumber = BanmaParamsUtils.checkPageNum(pageNumber);
        if (remote){
            String apiUrl = String.format(BanmaerpURL.Banmaerp_supplierlist_GET, name,pageNumber,pageSize,searchTimeStart,searchTimeEnd,searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            productSuppliersDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(BANMAERP_FIELD_SUPPLIERS))
                    .map(o -> (ProductSuppliersInfoDTO) o)
                    .map(supplierInfo -> supplierInfo.toProductSuppliersDTO(null,banmaerpProperties))
                    .collect(Collectors.toList());
        }else{
            Specification<ProductSuppliersInfoDTO> specification = createSpecification4SupInfo(name, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            productSuppliersDTOList =  supplierInfoRepository.findAll(specification,PageRequest.of(pageNumber,pageSize))
                    .map(supplierInfo -> supplierInfo.getProductSuppliersDTO())
                    .toList();
        }
        return productSuppliersDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<ProductDTO> saveProducts(List<ProductDTO> products,BanmaerpProperties banmaerpProperties) {
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
            productDTO.setBanmaerpProperties(banmaerpProperties);
        });
        saveOrUpdateProducts = productRepository.saveAllAndFlush(saveOrUpdateProducts);
        return saveOrUpdateProducts;
    }

    @Override
    @CheckBanmaerpProperties
    public ProductDTO saveProduct(ProductDTO productDTO,BanmaerpProperties banmaerpProperties) {
        Optional<ProductSpuDTO> optionalProductSpuDTO = productSpuRepository.findById(productDTO.getSPU().getSPUID());
        optionalProductSpuDTO.ifPresent(productSpuDTO -> productDTO.setProductUUId(productSpuDTO.getProductDTO().getProductUUId()));
        productDTO.setBanmaerpProperties(banmaerpProperties);
        ProductDTO result =  productRepository.saveAndFlush(productDTO);
        return result;
    }

    @Override
    public List<ProductSuppliersDTO> saveSuppliers(List<ProductSuppliersDTO> suppliersDTOS, BanmaerpProperties banmaerpProperties) {
        //todo find existing, one to one
        suppliersDTOS.forEach(suppliersDTO -> suppliersDTO.setBanmaerpProperties(banmaerpProperties));
        return suppliersRepository.saveAllAndFlush(suppliersDTOS);
    }

    @Override
    public ProductSuppliersDTO saveSupplier(ProductSuppliersDTO suppliersDTO, BanmaerpProperties banmaerpProperties) {
        suppliersDTO.setBanmaerpProperties(banmaerpProperties);
        return suppliersRepository.saveAndFlush(suppliersDTO);
    }

    @Override
    public List<ProductSkusDTO> saveSkus(List<ProductSkusDTO> productSkusDTOS, BanmaerpProperties banmaerpProperties) {
        productSkusDTOS.forEach(productSkusDto -> productSkusDto.setBanmaerpProperties(banmaerpProperties));
        return skusRepository.saveAllAndFlush(productSkusDTOS);
    }

    @Override
    public ProductSkusDTO saveSku(ProductSkusDTO skusDTO, BanmaerpProperties banmaerpProperties) {
        skusDTO.setBanmaerpProperties(banmaerpProperties);
        return skusRepository.saveAndFlush(skusDTO);
    }

    @Override
    public List<ProductTagsDTO> saveProductTags(List<ProductTagsDTO> productTagsDTOS, BanmaerpProperties banmaerpProperties) {
        return productTagsRepository.saveAllAndFlush(productTagsDTOS);
    }

    @Override
    public ProductTagsDTO saveProductTag(ProductTagsDTO productTagsDTO, BanmaerpProperties banmaerpProperties) {
        return productTagsRepository.saveAndFlush(productTagsDTO);
    }

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
    private Specification<ProductDTO> createSpecification(String spuIds, String source, String spu, String categoryId, String title, String supplier, String costPriceStart, String costPriceEnd, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (spuIds != null && spuIds != "") {
                CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("SPU").get("SPUID"));
                if (spuIds.contains(",")) {
                    String[] ids = spuIds.split(",");
                    for (int i = 0; i < ids.length; i++) {
                        in.value(Long.parseLong(ids[i]));
                    }
                } else {
                    in.value(Long.parseLong(spuIds));
                }
                predicateList.add(in);
            }

            // SPU code
            if (spu != null && spu != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("SPU").get("code"),
                                spu));
            }
            // 类目id
            if (categoryId != null && categoryId != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("SPU").get("leiMuID"),
                                categoryId));
            }
            // 标题
            if (title != null && title != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("SPU").get("title"),
                                "%" + title + "%"));
            }
            // 供应商名称
            if (supplier != null && supplier != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("suppliers").get("info").get("name"),
                                "%" + supplier + "%"));
            }
            // 成本价起始值
            if (costPriceStart != null && costPriceStart != "") {
                predicateList.add(criteriaBuilder
                        .ge(root.get("SKUs").get("costPrice").as(Double.class),
                                Double.parseDouble(costPriceStart)));
            }
            // 成本价结束值
            if (costPriceEnd != null && costPriceEnd != "") {
                predicateList.add(criteriaBuilder
                        .le(root.get("SKUs").get("costPrice").as(Double.class),
                                Double.parseDouble(costPriceEnd)));
            }

            // 开始时间
            if (searchTimeStart != null && searchTimeEnd != null) {
                predicateList.add(criteriaBuilder
                        .between(root.<DateTime>get("SPU").get(searchTimeField), searchTimeStart, searchTimeEnd));
            }
            Predicate and = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            Order order = criteriaBuilder.desc(root.get("SPU").get("createTime"));
            if (sortBy != null && sortBy != "") {
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("SPU").get("createTime"));
                }
            }
            if (sortField != null && sortField != "") {
                order = criteriaBuilder.desc(root.get("SPU").get(sortField));
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("SPU").get(sortField));
                }
            }
            return criteriaQuery.where(and).orderBy(order).getRestriction();
        };
    }

    private Specification<ProductSkusDTO> createSpecification(String skuIds, String code, String spuId, String costPriceStart, String costPriceEnd, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (skuIds != null && skuIds != "") {
                CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("SKUID"));
                if (skuIds.contains(",")) {
                    String[] id = skuIds.split(",");
                    for (int i = 0; i < id.length; i++) {
                        in.value(Long.parseLong(id[i]));
                    }
                } else {
                    in.value(Long.parseLong(skuIds));
                }
                predicateList.add(in);
            }

            if (code != null && code != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("code"),
                                code));
            }
            // 成本价起始值
            if (costPriceStart != null && costPriceStart != "") {
                predicateList.add(criteriaBuilder
                        .ge(root.get("costPrice").as(Double.class),
                                Double.parseDouble(costPriceStart)));
            }
            // 成本价结束值
            if (costPriceEnd != null && costPriceEnd != "") {
                predicateList.add(criteriaBuilder
                        .le(root.get("costPrice").as(Double.class),
                                Double.parseDouble(costPriceEnd)));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private Specification<ProductTagsDTO> createSpecification(String name, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (name != null && name != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("name"),
                                "%" + name + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private Specification<ProductSuppliersInfoDTO> createSpecification4SupInfo(String name, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (name != null && name != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("name"),
                                "%" + name + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
}
