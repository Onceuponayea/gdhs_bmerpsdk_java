package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductTagsDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.DateTime;
import java.util.List;

public interface ProductService {

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
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<ProductDTO> getProductList(
            String spuIds,
            String source,
            String spu,
            String categoryId,
            String title,
            String supplier,
            String costPriceStart,
            String costPriceEnd,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个产品
     * @param spuId 	SPUID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    ProductDTO getProductById(String spuId,
                                       BanmaerpProperties banmaerpProperties);

    /**
     * 添加产品
     * @param productDto
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    ProductDTO addProduct(ProductDTO productDto,
                                      BanmaerpProperties banmaerpProperties);

    /**
     * 更新产品
     * @param productDto
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    ProductDTO updateProductById(ProductDTO productDto,
                                          BanmaerpProperties banmaerpProperties);

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
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<ProductSkusDTO> getProductSkuList(
            String skuIds,
            String code,
            String spuId,
            String costPriceStart,
            String costPriceEnd,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个SKU
     * @param skuid 	SkuID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    ProductSkusDTO getProductSkuById(String skuid,
                                          BanmaerpProperties banmaerpProperties);

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
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<ProductTagsDTO> getProductTagsList(
            String name,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

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
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<ProductSuppliersInfoDTO> getProductSuppliersList(
            String name,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

}
