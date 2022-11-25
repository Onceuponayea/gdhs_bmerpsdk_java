package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data
@Entity
@Table(name = "bmerp_order_master")
public class OrderMasterDTO {

    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "StoreID")
    private String storeID;
    @JsonProperty(value = "Platform")
    private String platform;
    @JsonProperty(value = "OriginalOrderID")
    private String originalOrderID;
    @JsonProperty(value = "DisplayOrderID")
    private String displayOrderID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "PayTime")
    private String payTime;
    @JsonProperty(value = "PayChannel")
    private String payChannel;
//    @JsonProperty("PayType")
//    private int payType;
    @JsonProperty(value = "PayAmount")
    private double payAmount;
    @JsonProperty(value = "PayCurrency")
    private String payCurrency;
    @JsonProperty(value = "PayAmountUSD")
    private double payAmountUSD;
    @JsonProperty(value = "PayAmountCNY")
    private double payAmountCNY;
    @JsonProperty(value = "PayFreight")
    private double payFreight;
    @JsonProperty(value = "PayFreightCurrency")
    private String payFreightCurrency;
    @JsonProperty(value = "PayFreightUSD")
    private double payFreightUSD;
    @JsonProperty(value = "PayFreightCNY")
    private double payFreightCNY;
    @JsonProperty(value = "RefundAmount")
    private double refundAmount;
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;
    @JsonProperty(value = "RefundAmountUSD")
    private double refundAmountUSD;
    @JsonProperty(value = "RefundAmountCNY")
    private double refundAmountCNY;
    @JsonProperty(value = "DiscountAmount")
    private double discountAmount;
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
    private double purchaseFreight;
    @JsonProperty(value = "UserIndentity")
    private String userIndentity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OrderTime")
    private String orderTime;
    @JsonProperty(value = "Tags")
    private String[] tags;
    @JsonProperty(value = "OriginalOrderStatus")
    private String originalOrderStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalOrderTime")
    private String originalOrderTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalPayTime")
    private String originalPayTime;
    @JsonProperty(value = "HaveMessage")
    private boolean haveMessage;
    @JsonProperty(value = "HaveRemark")
    private boolean haveRemark;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "LatestDeliveryTime")
    private String latestDeliveryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private String createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private String updateTime;
    @JsonProperty(value = "IsCOD")
    private boolean isCOD;
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
    private String shippingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "OriginalShippingTime")
    private String originalShippingTime;
    @JsonProperty(value = "OriginalTags")
    private String[] originalTags;
    @JsonProperty(value = "BuyerPhone")
    private String buyerPhone;
    /**
     * 要求交货日期/预计交货日期/期望交货日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "RequestedDeliveryDate")
    private String requestedDeliveryDate;
    /**
     * 要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00
     */
    @JsonProperty(value = "RequestedDeliveryTime")
    private String requestedDeliveryTime;
    @JsonProperty(value = "ProfitData")
    private String profitData;
    @JsonProperty(value = "RiskLevel")
    private String riskLevel;
    @JsonProperty(value = "UsedPoint")
    private Integer usedPoint;




}
