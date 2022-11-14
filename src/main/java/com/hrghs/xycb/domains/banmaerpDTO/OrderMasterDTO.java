package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderMasterDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "StoreID")
    private String storeId;
    @JsonProperty(value = "Platform")
    private String platform;
    @JsonProperty(value = "OriginalOrderID")
    private String originalOrderId;
    @JsonProperty(value = "DisplayOrderID")
    private String displayOrderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "PayTime")
    private DateTime payTime;
    @JsonProperty(value = "PayChannel")
    private String payChannel;
    @JsonProperty("PayType")
    private int payType;
    @JsonProperty(value = "PayAmount")
    private String payAmount;
    @JsonProperty(value = "PayCurrency")
    private String payCurrency;
    @JsonProperty(value = "PayAmountUSD")
    private String payAmountUsd;
    @JsonProperty(value = "PayAmountCNY")
    private String payAmountCny;
    @JsonProperty(value = "PayFreight")
    private String payFreight;
    @JsonProperty(value = "PayFreightCurrency")
    private String payFreightCurrency;
    @JsonProperty(value = "PayFreightUSD")
    private String payFreightUsd;
    @JsonProperty(value = "PayFreightCNY")
    private String payFreightCny;
    @JsonProperty(value = "RefundAmount")
    private String refundAmount;
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;
    @JsonProperty(value = "RefundAmountUSD")
    private String refundAmountUsd;
    @JsonProperty(value = "RefundAmountCNY")
    private String refundAmountCny;
    @JsonProperty(value = "DiscountAmount")
    private String discountAmount;
    @JsonProperty(value = "DiscountCurrency")
    private String discountCurrency;
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "PayStatus")
    private String payStatus;
    @JsonProperty(value = "ShippingType")
    private String shippingType;
    @JsonProperty(value = "CountryCode")
    private String countryCode;
    @JsonProperty(value = "HoldStatus")
    private String holdStatus;
    @JsonProperty(value = "RefundStatus")
    private String refundStatus;
    @JsonProperty(value = "Quantity")
    private int Quantity;
    @JsonProperty(value = "PurchaseFreight")
    private String purchaseFreight;
    @JsonProperty(value = "UserIndentity")
    private String userIndentity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OrderTime")
    private DateTime orderTime;
    @JsonProperty(value = "Tags")
    private String[] tags;
    @JsonProperty(value = "OriginalOrderStatus")
    private String originalOrderStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalOrderTime")
    private DateTime originalOrderTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalPayTime")
    private DateTime originalPayTime;
    @JsonProperty(value = "HaveMessage")
    private boolean haveMessage;
    @JsonProperty(value = "HaveRemark")
    private boolean haveRemark;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "LatestDeliveryTime")
    private DateTime latestDeliveryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
    @JsonProperty(value = "IsCOD")
    private boolean isCod;
    @JsonProperty(value = "Flags")
    private String[] flags;
    @JsonProperty(value = "Email")
    private String email;
    @JsonProperty(value = "InventoryStatus")
    private String inventoryStatus;
    @JsonProperty(value = "InventoryMode")
    private String inventoryMode;
    @JsonProperty(value = "TxNo")
    private String txNo;
    @JsonProperty(value = "BuyerName")
    private String buyerName;
    @JsonProperty(value = "Message")
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "ShippingTime")
    private DateTime shippingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalShippingTime")
    private DateTime originalShippingTime;
    @JsonProperty(value = "OriginalTags")
    private String[] originalTags;
    @JsonProperty(value = "BuyerPhone")
    private String buyerPhone;
    /**
     * 要求交货日期/预计交货日期/期望交货日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "RequestedDeliveryDate")
    private DateTime requestedDeliveryDate;
    /**
     * 要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00
     */
//    @JsonProperty(value = "RequestedDeliveryDate")
//    private DateTime requestedDeliveryDates;
    @JsonProperty(value = "RiskLevel")
    private String riskLevel;
    @JsonProperty(value = "UsedPoint")
    private String usedPoint;

}
