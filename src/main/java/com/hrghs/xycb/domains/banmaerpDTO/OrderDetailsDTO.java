package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;
import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_order_details")
public class OrderDetailsDTO {

    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "PackageID")
    private String packageID;
    @JsonProperty("OriginalOrderID")
    private String originalOrderID;
    @JsonProperty(value = "OriginalDetailID")
    private String originalDetailID;
    @JsonProperty(value = "OriginalSKU")
    private String originalSKU;
    @JsonProperty(value = "SPUID")
    private String SPUID;
    @JsonProperty(value = "SKUID")
    private String SKUID;
    @JsonProperty(value = "SKUType")
    private String SKUType;
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "SKUSpecification")
    private String SKUSpecification;
    @JsonProperty(value = "Price")
    private double price;
    @JsonProperty(value = "Title")
    private String title;
    @JsonProperty(value = "OriginalImage")
    private String originalImage;
    @JsonProperty(value = "Quantity")
    private int quantity;
    @JsonProperty(value = "OriginalQuantity")
    private int originalQuantity;
    @JsonProperty(value = "Amount")
    private double amount;
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "PriceCurrency")
    private String priceCurrency;
    @JsonProperty(value = "Properties")
    private String[] properties;
    @JsonProperty(value = "OriginalProductID")
    private String originalProductID;
    @JsonProperty(value = "OriginalSKUID")
    private String originalSKUID;
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
    private String SKUImage;
    @OneToMany(mappedBy = "orderDetailsDTO",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonProperty(value = "InventoryData")
    private List<OrderDetailsInventoryDataDTO> inventoryData;
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
