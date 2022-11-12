package com.hrghs.xycb.domains.enums;

public class BanmaerpOrderEnums {
    public enum Status {
        Ordered("Ordered"), Audit("Audit"), PrepareDeliver("PrepareDeliver"), PartialDeliver("PartialDeliver"),
        Delivered("Delivered"), Cancel("Cancel");
        private String Status;

        private Status(String Status) {
            this.Status = Status;
        }
    }

    public enum PayStatus {
        // 未付款
        NonPayment("Nonpayment"),
        // 已付款
        Paid("Paid");
        private String payStatus;

        private PayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
    }

    public enum HoldStatus {
        // 未暂停
        NotPaused("Notpaused"),
        // 已暂停
        Paused("Paused");
        private String holdStatus;

        private HoldStatus(String holdStatus) {
            this.holdStatus = holdStatus;
        }
    }

    public enum RefundStatus {
        // 无退款
        NoRefunds("Norefunds"),
        // 全部退款
        FullRefund("Fullrefund"),
        // 部分退款
        PartialRefund("Partialrefund"),
        // 待同步
        ToBeSynchronized("Tobesynchronized");
        private String refundStatus;

        private RefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }
    }

    public enum InventoryStatus {
        // 未知
        Unknown("Unknown"),
        // 有货
        InStock("Instock"),
        // 部分缺货
        SomeAreOutOfStock("SomeAreOutOfStock"),
        // 全部缺货
        AllOutOfStock("AllOutOfStock");
        private String inventoryStatus;

        private InventoryStatus(String inventoryStatus) {
            this.inventoryStatus = inventoryStatus;
        }
    }

    public enum SearchTimeField {
        // 创建时间
        CreateTime("CreateTime"),
        // 更新时间
        UpdateTime("UpdateTime"),
        // 订单生成时间
        OrderTime("OrderTime"),
        // 支付时间
        PayTime("PayTime"),
        // 最新的发货时间
        LatestDeliveryTime("LatestDeliveryTime"),
        // 平台上显示的下单时间
        OriginalOrderTime("OriginalOrderTime"),
        //平台上显示的付款时间
        OriginalPayTime("OriginalPayTime");
        private String searchTimeField;

        private SearchTimeField(String searchTimeField) {
            this.searchTimeField = searchTimeField;
        }
    }

    public enum InventoryMode {
        // 本地仓库存
        LocalWarehouseStorage("LocalWarehouseStorage"),
        // 第三方仓库存
        ThirdPartyWarehouseStorage("ThirdPartyWarehouseStorage"),
        // 无库存发货
        ShippedOutOfStock("ShippedOutOfStock");
        private String inventoryMode;

        private InventoryMode(String inventoryMode) {
            this.inventoryMode = inventoryMode;
        }
    }

    public enum SKUType {
        // 普通产品
        Ordinary("ordinary"),
        // 组合产品
        Combination("combination");
        private String sKUType;

        private SKUType(String sKUType) {
            this.sKUType = sKUType;
        }
    }

    public enum SkuChangeStatus {
        // 未变更
        NoChange("NoChange"),
        // 已换货
        Exchanged("Exchanged");
        private String skuChangeStatus;

        private SkuChangeStatus(String skuChangeStatus) {
            this.skuChangeStatus = skuChangeStatus;
        }
    }

    public enum Type {
        // 正常产品
        Ordinary("ordinary"),
        // 补发产品
        Reissue("reissue"),
        // 赠品
        Gifts("Gifts");
        private String type;

        private Type(String type) {
            this.type = type;
        }
    }

    public enum QuantityStatus {
        // 原始值
        Raw("Raw"),
        // 已调整后的值
        Adjusted("Adjusted");
        private String quantityStatus;

        private QuantityStatus(String quantityStatus) {
            this.quantityStatus = quantityStatus;
        }
    }

    public enum AddrType {
        // 未知
        Unknown("Unknown"),
        // 收货地址
        ShippingAddress("ShippingAddress"),
        // 账单地址
        BillingAddress("BillingAddress");
        private String addrType;

        private AddrType(String addrType) {
            this.addrType = addrType;
        }
    }

    public enum Category {
        // 买家添加的备注
        OriginalComments("OriginalComments"),
        // 客服添加的备注
        CustomerServiceNotes("CustomerServiceNotes"),
        // 物流添加的备注
        LogisticsNotes("LogisticsNotes"),
        // 采购添加的备注
        PurchaseNotes("PurchaseNotes");
        private String category;

        private Category(String category) {
            this.category = category;
        }
    }

    public enum SortField {
        // 订单ID
        ID("ID"),
        // 创建时间
        CreateTime("CreateTime"),
        // 更新时间
        UpdateTime("UpdateTime"),
        // 订单生成时间
        OrderTime("OrderTime"),
        // 支付时间
        PayTime("PayTime"),
        // 最新的发货时间
        LatestDeliveryTime("LatestDeliveryTime"),
        // 平台上显示的下单时间
        OriginalOrderTime("OriginalOrderTime"),
        // 平台上显示的付款时间
        OriginalPayTime("OriginalPayTime");
        private String sortField;

        private SortField(String sortField) {
            this.sortField = sortField;
        }
    }



    public enum RiskLevel {
        // 未知
        Unknown("Unknown"),
        // 低风险
        LowRisk("LowRisk"),
        // 中风险
        MediumRisk("MediumRisk"),
        // 高风险
        HighRisk("HighRisk");
        private String riskLevel;

        private RiskLevel(String riskLevel) {
            this.riskLevel = riskLevel;
        }
    }
}
