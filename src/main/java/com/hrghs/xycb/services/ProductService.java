package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.DateTime;

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
     * @return
     */
    BanmaErpResponseDTO getProductList(
            String spuIds,
            long source,
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
            String sortBy
    );

    /**
     * 查询单个产品
     * @param spuId 	SPUID（必填）
     * @return
     */
    BanmaErpResponseDTO getProductById(String spuId);

    /**
     * 添加产品
     * @param productDto
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    BanmaErpResponseDTO insertProduct(ProductDTO productDto);

    /**
     * 更新产品
     * @param productDto
     * 必填： Spu(描述信息)，Skus（sku信息）,Options（选项）,Images（图片）
     * @return
     */
    BanmaErpResponseDTO updateProductById(ProductDTO productDto);

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
    BanmaErpResponseDTO getProductSkuList(
            String skuIds,
            String code,
            long spuId,
            String costPriceStart,
            String costPriceEnd,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy
    );

    /**
     * 查询单个SKU
     * @param skuid 	SkuID（必填）
     * @return
     */
    BanmaErpResponseDTO getProductSkuById(String skuid);

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
    BanmaErpResponseDTO getProductTagsList(
            String name,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy
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
     * @return
     */
    BanmaErpResponseDTO getProductSuppliersList(
            String name,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy
    );

}
