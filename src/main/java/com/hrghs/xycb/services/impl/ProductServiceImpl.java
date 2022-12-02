package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.ProductRepository;
import com.hrghs.xycb.repositories.ProductSpuRepository;
import com.hrghs.xycb.services.ProductService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.*;

@Service
@Lazy
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
    public List<ProductDTO> getProductList(String spuIds, String source, String spu, String categoryId, String title, String supplier, String costPriceStart, String costPriceEnd, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_productlist_GET, pageNumber, pageSize,
                searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<ProductDTO> productDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_PRODUCTS)
        )
                .map(o -> (ProductDTO) o)
                .collect(Collectors.toList());
        return productDTOList;
    }


    /**
     * 查询单个产品
     *
     * @param spuId SPUID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public ProductDTO getProductById(String spuId,
                                     BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_product_GET, spuId);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
//        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
//                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
//                .getBody();
//        ProductDTO productDTO = null;
//        try {
//            productDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<ProductDTO>() {});
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return productDTO;
        return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {
                })
                .getBody().getData();
    }

    /**
     * 添加产品
     *
     * @param productDto 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<ProductDTO> insertProduct(ProductDTO productDto,
                                                         BanmaerpProperties banmaerpProperties) {
        String apiUrl = BanmaerpURL.banmaerp_product_POST;
        return saveOrUpdate(apiUrl, HttpMethod.POST, productDto, banmaerpProperties);
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
        return saveOrUpdate(apiUrl, HttpMethod.PUT, productDto, banmaerpProperties);
    }

    private BanmaErpResponseDTO<ProductDTO> saveOrUpdate(String apiUrl, HttpMethod method, ProductDTO productDto,
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
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestBody = new HttpEntity(requestBodyJson, httpHeaders);
        return
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                method, requestBody,
                                new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {
                                })
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
    public List<ProductSkusDTO> getProductSkuList(String skuIds, String code, String spuId, String costPriceStart, String costPriceEnd, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.Banmaerp_product_skulist_GET, skuIds, spuId);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<ProductSkusDTO> productSkusDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_SKUS)
        )
                .map(o -> (ProductSkusDTO) o)
                .collect(Collectors.toList());
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
    public ProductSkusDTO getProductSkuById(String skuid,
                                            BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.Banmaerp_product_sku_GET, skuid);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        ProductSkusDTO productSkusDTO =
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<ProductSkusDTO>>() {
                        })
                        .getBody().getData();
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
                                                   String searchTimeField, String sortField, String sortBy,
                                                   BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.Banmaerp_taglist_GET, name);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<ProductTagsDTO> productTagsDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_TAGS)
        )
                .map(o -> (ProductTagsDTO) o)
                .collect(Collectors.toList());
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
                                                                 DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,
                                                                 BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.Banmaerp_supplierlist_GET, name);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<ProductSuppliersInfoDTO> productSuppliersInfoDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_SUPPLIERS)
        )
                .map(o -> (ProductSuppliersInfoDTO) o)
                .collect(Collectors.toList());
        return productSuppliersInfoDTOList;
    }

    @Override
    public List<ProductDTO> saveALL(List<ProductDTO> products) {
//        List<Long> ids = new ArrayList<>();
//        products.forEach(productDTO -> ids.add(productDTO.getSPU().getSPUID()));
//        List<ProductSpuDTO> byIDNotIn = productSpuRepository.findBySPUIDIn(ids);
//        byIDNotIn.forEach(productSpuDTO -> System.out.println(productSpuDTO.getSPUID()));
//        for (int i = 0; i < products.size(); i++) {
//            for (int j = 0; j < byIDNotIn.size(); j++) {
//                if ()
//            }
//        }
//        products.forEach(productDTO -> System.out.println(productDTO.getSPU().getSPUID()));
        return productRepository.saveAllAndFlush(products);
    }

    @Transactional
    @Override
    public ProductDTO save(ProductDTO productDTO) {
//        ProductSpuDTO bySPUID = productSpuRepository.findBySPUID(productDTO.getSPU().getSPUID());
//        if (bySPUID != null) {
//            ProductDTO byProductUUId = productRepository.findByProductUUId(bySPUID.getProductDTO().getProductUUId());
//            System.out.println(byProductUUId.getProductUUId());
//            // 添加UUID
//            productDTO.setProductUUId(byProductUUId.getProductUUId());
//            // 添加 ProductDescriptionId
//            productDTO.getDescriptions().setProductDescriptionId(byProductUUId.getDescriptions().getProductDescriptionId());
//            // 添加images每个元素id
//            for (int i = 0; i < byProductUUId.getImages().size(); i++) {
//                productDTO.getImages().get(i).setProductImageId(byProductUUId.getImages().get(i).getProductImageId());
//            }
//            // 添加Options每个元素id
//            for (int i = 0; i < byProductUUId.getOptions().size(); i++) {
//                productDTO.getOptions().get(i).setProductOptionsId(byProductUUId.getOptions().get(i).getProductOptionsId());
//            }
//            // 添加Requirements每个元素id
////            for (int i = 0; i < byProductUUId.getRequirements().size(); i++) {
////                productDTO.getRequirements().get(i).setProductRequirementId(byProductUUId.getRequirements().get(i).getProductRequirementId());
////            }
//            for (int i = 0; i < byProductUUId.getSKUs().size(); i++) {
//                // 添加SKUs中每个CombineData的每个元素id
//                for (int j = 0; j < byProductUUId.getSKUs().get(i).getCombineData().size(); j++) {
//                    productDTO.getSKUs().get(i).getCombineData().get(j).setSku_cmbdId(
//                            byProductUUId.getSKUs().get(i).getCombineData().get(j).getSku_cmbdId()
//                    );
//                }
//                // 添加SKUs中每个Options的每个元素id
//                for (int j = 0; j < byProductUUId.getSKUs().get(i).getOptions().size(); j++) {
//                    productDTO.getSKUs().get(i).getOptions().get(j).setSkuOption(
//                            byProductUUId.getSKUs().get(i).getOptions().get(j).getSkuOption()
//                    );
//                }
//
//            }
//            // 添加Tags每个元素id
//            for (int i = 0; i < byProductUUId.getTags().size(); i++) {
//                productDTO.getTags().get(i).setProductTagId(byProductUUId.getTags().get(i).getProductTagId());
//            }
//            // 添加Sources每个元素id
//            for (int i = 0; i < byProductUUId.getSources().size(); i++) {
//                productDTO.getSources().get(i).setProductSourceId(byProductUUId.getSources().get(i).getProductSourceId());
//            }
//        }
        return productRepository.saveAndFlush(productDTO);

    }

}
