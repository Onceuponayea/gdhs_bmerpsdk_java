package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JpaListStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_order_master")
public class OrderMasterDTO {

    public OrderMasterDTO(String orderId){
        this.ID =  orderId;
    }

    @Id
    @Column(name = "id")
    @JsonProperty(value = "ID")
    private String ID;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "order_uuid", referencedColumnName = "order_UUID")
    @JsonBackReference
    private OrderDTO orderDTO;

    @Column(name = "store_id")
    @JsonProperty(value = "StoreID")
    private String storeID;

    @JsonProperty(value = "Platform")
    private BanmaerpPlatformEnums.Platform platform;

    @Column(name = "original_order_id")
    @JsonProperty(value = "OriginalOrderID")
    private String originalOrderID;

    @Column(name = "display_order_id")
    @JsonProperty(value = "DisplayOrderID")
    private String displayOrderID;

    @Column(name = "pay_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "PayTime")
    private DateTime payTime;

    @Column(name = "pay_channel")
    @JsonProperty(value = "PayChannel")
    private String payChannel;

    @Column(name = "pay_type")
    @JsonProperty("PayType")
    private Integer payType;

    @Column(name = "pay_amount")
    @JsonProperty(value = "PayAmount")
    private Double payAmount;

    @Column(name = "pay_currency")
    @JsonProperty(value = "PayCurrency")
    private String payCurrency;

    @Column(name = "pay_amount_usd")
    @JsonProperty(value = "PayAmountUSD")
    private Double payAmountUSD;

    @Column(name = "pay_amount_cny")
    @JsonProperty(value = "PayAmountCNY")
    private Double payAmountCNY;

    @Column(name = "pay_freight")
    @JsonProperty(value = "PayFreight")
    private Double payFreight;

    @Column(name = "pay_freight_currency")
    @JsonProperty(value = "PayFreightCurrency")
    private String payFreightCurrency;

    @Column(name = "pay_freight_usd")
    @JsonProperty(value = "PayFreightUSD")
    private Double payFreightUSD;

    @Column(name = "pay_freight_cny")
    @JsonProperty(value = "PayFreightCNY")
    private Double payFreightCNY;

    @Column(name = "refund_amount")
    @JsonProperty(value = "RefundAmount")
    private Double refundAmount;

    @Column(name = "refund_currency")
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;

    @Column(name = "refund_amount_usd")
    @JsonProperty(value = "RefundAmountUSD")
    private Double refundAmountUSD;

    @Column(name = "refund_amount_cny")
    @JsonProperty(value = "RefundAmountCNY")
    private Double refundAmountCNY;

    @Column(name = "discount_amount")
    @JsonProperty(value = "DiscountAmount")
    private Double discountAmount;

    @Column(name = "discount_currency")
    @JsonProperty(value = "DiscountCurrency")
    private String discountCurrency;

    @JsonProperty(value = "Status")
    private String status;

    @Column(name = "pay_status")
    @JsonProperty(value = "PayStatus")
    private String payStatus;

    @Column(name = "shipping_type")
    @JsonProperty(value = "ShippingType")
    private String shippingType;

    @Column(name = "country_code")
    @JsonProperty(value = "CountryCode")
    private String countryCode;

    @Column(name = "hold_status")
    @JsonProperty(value = "HoldStatus")
    private String holdStatus;

    @Column(name = "refund_status")
    @JsonProperty(value = "RefundStatus")
    private String refundStatus;

    @JsonProperty(value = "Quantity")
    private Integer Quantity;

    @Column(name = "purchase_freight")
    @JsonProperty(value = "PurchaseFreight")
    private Double purchaseFreight;

    /**
     * @@attention:  斑马erp单词拼错，可能后续会有变动！
     */
    @Column(name = "user_indentity")
    @JsonProperty(value = "UserIndentity")
    private String userIndentity;

    @Column(name = "order_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "OrderTime")
    private DateTime orderTime;

    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty(value = "Tags")
    private List<String> tags;

    @Column(name = "original_order_status")
    @JsonProperty(value = "OriginalOrderStatus")
    private String originalOrderStatus;

    @Column(name = "original_order_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "OriginalOrderTime")
    private DateTime originalOrderTime;

    @Column(name = "original_pay_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "OriginalPayTime")
    private DateTime originalPayTime;

    @Column(name = "have_message")
    @JsonProperty(value = "HaveMessage")
    private Boolean haveMessage;

    @Column(name = "have_remark")
    @JsonProperty(value = "HaveRemark")
    private Boolean haveRemark;

    @Column(name = "latest_delivery_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "LatestDeliveryTime")
    private DateTime latestDeliveryTime;

    @Column(name = "create_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;

    @Column(name = "update_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;

    @Column(name = "is_cod")
    @JsonProperty(value = "IsCOD")
    private Boolean isCOD;

    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty(value = "Flags")
    private List<String> flags;

    @JsonProperty(value = "Email")
    private String email;

    @Column(name = "inventory_status")
    @JsonProperty(value = "InventoryStatus")
    private String inventoryStatus;

    @Column(name = "inventory_mode")
    @JsonProperty(value = "InventoryMode")
    private String inventoryMode;

    @Column(name = "tx_no")
    @JsonProperty(value = "TxNo")
    private String txNo;

    @Column(name = "buyer_name")
    @JsonProperty(value = "BuyerName")
    private String buyerName;

    @JsonProperty(value = "Message")
    private String message;

    @Column(name = "shipping_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "ShippingTime")
    private DateTime shippingTime;

    @Column(name = "original_shipping_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "OriginalShippingTime")
    private DateTime originalShippingTime;

    @Column(name = "original_tags")
    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty(value = "OriginalTags")
    private List<String> originalTags;

    @Column(name = "buyer_phone")
    @JsonProperty(value = "BuyerPhone")
    private String buyerPhone;

    /**
     * 要求交货日期/预计交货日期/期望交货日期
     */
    @Column(name = "requested_delivery_date")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "RequestedDeliveryDate")
    private DateTime requestedDeliveryDate;

    /**
     * 要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00
     */
    @Column(name = "requested_delivery_time")
    @JsonProperty(value = "RequestedDeliveryTime")
    private String requestedDeliveryTime;

    @Column(name = "risk_level")
    @JsonProperty(value = "RiskLevel")
    private String riskLevel;

    @Column(name = "used_point")
    @JsonProperty(value = "UsedPoint")
    private Integer usedPoint;

}
