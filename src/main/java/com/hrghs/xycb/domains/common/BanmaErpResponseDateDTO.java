package com.hrghs.xycb.domains.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BanmaErpResponseDateDTO {

    @JsonProperty("Categorys")
    private CategoryDTO[] categorys;

    @JsonProperty("Category")
    private CategoryDTO category;

    @JsonProperty("Orders")
    private OrderDTO[] orders;

    @JsonProperty("Order")
    private OrderDTO order;

    @JsonProperty("Products")
    private ProductDTO[] products;

    @JsonProperty("Product")
    private ProductDTO product;

    @JsonProperty("SKUs")
    private ProductSkusDTO[] skus;

    @JsonProperty("SKU")
    private ProductSkusDTO sku;

    @JsonProperty("Tags")
    private ProductTagsDTO[] tags;

    @JsonProperty("Suppliers")
    private ProductSuppliersInfoDTO[] suppliers;

    @JsonProperty("Storages")
    private StorageDTO[] storages;

    @JsonProperty("Storage")
    private StorageDTO storage;

    @JsonProperty("Stores")
    private StoreDTO[] stores;

    @JsonProperty("Store")
    private StoreDTO store;

    @JsonProperty("Page")
    private PageDTO page;


}
