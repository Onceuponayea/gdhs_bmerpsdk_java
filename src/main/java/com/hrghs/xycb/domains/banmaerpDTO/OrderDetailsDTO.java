package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderDetailsDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "PackageID")
    private String packageID;
    @JsonProperty(value = "OriginalDetailID")
    private String originalDetailID;
    @JsonProperty(value = "OriginalSKU")
    private String originalSKU;
    @JsonProperty(value = "SPUID")
    private long spuId;
    @JsonProperty(value = "SKUID")
    private long skuId;
    @JsonProperty(value = "SKUType")
    private String skuType;
    @JsonProperty(value = "SKUCode")
    private String skuCode;
    @JsonProperty(value = "SKUSpecification")
    private String skuSpecification;
    @JsonProperty(value = "Price")
    private String price;
    @JsonProperty(value = "Title")
    private String title;
    @JsonProperty(value = "OriginalImage")
    private String originalImage;
    @JsonProperty(value = "Quantity")
    private int quantity;
    @JsonProperty(value = "OriginalQuantity")
    private int originalQuantity;
    @JsonProperty(value = "Amount")
    private String amount;
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "PriceCurrency")
    private String priceCurrency;
    @JsonProperty(value = "Properties")
    private String properties;
    @JsonProperty(value = "OriginalProductID")
    private String originalProductID;
    @JsonProperty(value = "OriginalSKUID")
    private String originalSkuId;
    @JsonProperty(value = "WarehouseID")
    private String warehouseID;
    @JsonProperty(value = "InventoryStatus")
    private String inventoryStatus;
    @JsonProperty(value = "InventoryLockQuantity")
    private int inventoryLockQuantity;
    @JsonProperty(value = "SkuChangeStatus")
    private String skuChangeStatus;
    @JsonProperty(value = "OriginalSpecification")
    private String originalSpecification;
    @JsonProperty(value = "SKUImage")
    private int skuImage;
    @JsonProperty(value = "InventoryData")
    private OrderDetailsInventoryDataDTO[] inventoryData;
    @JsonProperty(value = "Sort")
    private int sort;
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "ProductUrl")
    private String productUrl;
    @JsonProperty(value = "QuantityStatus")
    private String quantityStatus;
    @JsonProperty(value = "RelationDetailID")
    private String relationDetailID;
    @JsonProperty(value = "DeliveryInstructions")
    private String deliveryInstructions;
    @JsonProperty(value = "Priority")
    private int priority;


}
