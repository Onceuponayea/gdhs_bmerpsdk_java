package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.ProductRepository;
import com.hrghs.xycb.services.ProductService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private ProductRepository productRepository;
    /**
     * 查询产品列表
     * @param spuIds spuID，用逗号分隔
     * @param source 店铺ID
     * @param spu    spu code
     * @param categoryId erp类目ID
     * @param title  标题
     * @param supplier 	供应商名称
     * @param costPriceStart 成本价起始
     * @param costPriceEnd 成本价结束
     * @param pageNumber 页码（必填）
     * @param pageSize  页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd 	查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField 排序字段名，具体值参见:SortField
     * @param sortBy    排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    public List<ProductDTO> getProductList(String spuIds, long source, String spu, String categoryId, String title, String supplier,
                                           String costPriceStart, String costPriceEnd, int pageNumber, int pageSize,
                                           DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField,
                                           String sortBy, BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询单个产品
     * @param spuId 	SPUID（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<ProductDTO> getProductById(String spuId,
                                              BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 添加产品
     * @param productDto
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<ProductDTO> insertProduct(ProductDTO productDto,
                                             BanmaerpProperties banmaerpProperties) {
        //productRepository.save(productDto);
        String apiUrl = BanmaerpURL.banmaerp_product_POST;
        return saveOrUpdate(apiUrl,productDto,banmaerpProperties);
    }

    /**
     * 更新产品
     * @param productDto
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<ProductDTO> updateProductById(ProductDTO productDto,
                                                 BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_product_PUT,Long.parseLong(productDto.getSpu().getSpuId()));
        return saveOrUpdate(apiUrl,productDto,banmaerpProperties);
    }
    private BanmaErpResponseDTO<ProductDTO> saveOrUpdate(String apiUrl,ProductDTO productDto,
                                                         BanmaerpProperties banmaerpProperties){
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(productDto,httpHeaders);
        return
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                HttpMethod.POST,requestBody,
                                new ParameterizedTypeReference<BanmaErpResponseDTO<ProductDTO>>() {})
                        .getBody();
    }
    /**
     * 查询SKU列表
     * @param skuIds SKUID，用逗号分隔
     * @param code 	sku code
     * @param spuId 	SPUID
     * @param costPriceStart 	成本价起始
     * @param costPriceEnd 	成本价结束
     * @param pageNumber 	页码（必填）
     * @param pageSize      页大小
     * @param searchTimeStart 	查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     排序字段名，具体值参见:SortField
     * @param sortBy    	排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    public BanmaErpResponseDTO getProductSkuList(String skuIds, String code, long spuId, String costPriceStart, String costPriceEnd,
                                                 int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd,
                                                 String searchTimeField, String sortField, String sortBy,
                                                 BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询单个SKU
     * @param skuid 	SkuID（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO getProductSkuById(String skuid,
                                                 BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询Tag列表
     * @param name tag词名称
     * @param pageNumber 页码（必填）
     * @param pageSize   页大小
     * @param searchTimeStart   查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField    查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     排序字段名，具体值参见:SortField
     * @param sortBy        	排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    public BanmaErpResponseDTO getProductTagsList(String name, int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd,
                                                  String searchTimeField, String sortField, String sortBy,
                                                  BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询供应商列表
     * @param name  	供应商名称
     * @param pageNumber 页码（必填）
     * @param pageSize   页大小
     * @param searchTimeStart   查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField    查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     排序字段名，具体值参见:SortField
     * @param sortBy        	排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    public BanmaErpResponseDTO getProductSuppliersList(String name, int pageNumber, int pageSize, DateTime searchTimeStart,
                                                       DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,
                                                       BanmaerpProperties banmaerpProperties) {
        return null;
    }
}
