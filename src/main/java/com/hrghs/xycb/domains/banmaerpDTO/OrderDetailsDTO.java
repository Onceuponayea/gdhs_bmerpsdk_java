package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaListStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
@Table(name = "bmerp_order_details")
public class OrderDetailsDTO {

    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    private String ID;

    @JsonIgnore
//    @JoinColumn(name = "ID",nullable = false,insertable = false,updatable = false)
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderDTO orderDTO;

    @Column(name = "package_id")
    @JsonProperty(value = "PackageID")
    private String packageID;

    @Column(name = "original_order_id")
    @JsonProperty("OriginalOrderID")
    private String originalOrderID;

    @Column(name = "original_detail_id")
    @JsonProperty(value = "OriginalDetailID")
    private String originalDetailID;

    @Column(name = "original_sku")
    @JsonProperty(value = "OriginalSKU")
    private String originalSKU;

    @Column(name = "spu_id")
    @JsonProperty(value = "SPUID")
    private String SPUID;

    @Column(name = "sku_id")
    @JsonProperty(value = "SKUID")
    private String SKUID;

    @Column(name = "sku_type")
    @JsonProperty(value = "SKUType")
    private String SKUType;

    @Column(name = "sku_code")
    @JsonProperty(value = "SKUCode")
    private String SKUCode;

    @Column(name = "sku_specification")
    @JsonProperty(value = "SKUSpecification")
    private String SKUSpecification;

    @JsonProperty(value = "Price")
    private Double price;

    @JsonProperty(value = "Title")
    private String title;

    @Column(name = "original_image")
    @JsonProperty(value = "OriginalImage")
    private String originalImage;

    @JsonProperty(value = "Quantity")
    private Integer quantity;

    @Column(name = "original_quantity")
    @JsonProperty(value = "OriginalQuantity")
    private Integer originalQuantity;

    @JsonProperty(value = "Amount")
    private Double amount;

    @JsonProperty(value = "Status")
    private String status;

    @Column(name = "price_currency")
    @JsonProperty(value = "PriceCurrency")
    private String priceCurrency;

    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty(value = "Properties")
    private List<String> properties;

    @Column(name = "original_product_id")
    @JsonProperty(value = "OriginalProductID")
    private String originalProductID;

    @Column(name = "original_sku_id")
    @JsonProperty(value = "OriginalSKUID")
    private String originalSKUID;

    @Column(name = "warehouse_id")
    @JsonProperty(value = "WarehouseID")
    private String warehouseID;

    @Column(name = "inventory_status")
    @JsonProperty(value = "InventoryStatus")
    private String inventoryStatus;

    @Column(name = "inventory_lock_quantity")
    @JsonProperty(value = "InventoryLockQuantity")
    private Integer inventoryLockQuantity;

    @Column(name = "sku_change_status")
    @JsonProperty(value = "SkuChangeStatus")
    private String skuChangeStatus;

    @Column(name = "original_specification")
    @JsonProperty(value = "OriginalSpecification")
    private String originalSpecification;

    @Column(name = "sku_image")
    @JsonProperty(value = "SKUImage")
    private String SKUImage;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    @JsonProperty(value = "InventoryData")
    private List<OrderDetailsInventoryDataDTO> inventoryData;

    @JsonProperty(value = "Sort")
    private Integer sort;

    @JsonProperty(value = "Type")
    private String type;

    @Column(name = "product_url")
    @JsonProperty(value = "ProductUrl")
    private String productUrl;

    @Column(name = "quantity_status")
    @JsonProperty(value = "QuantityStatus")
    private String quantityStatus;

    @Column(name = "relation_detail_id")
    @JsonProperty(value = "RelationDetailID")
    private String relationDetailID;

    @Column(name = "delivery_instructions")
    @JsonProperty(value = "DeliveryInstructions")
    private String deliveryInstructions;

    @JsonProperty(value = "Priority")
    private Integer priority;


}
