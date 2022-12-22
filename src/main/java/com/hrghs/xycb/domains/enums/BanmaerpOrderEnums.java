package com.hrghs.xycb.domains.enums;

import com.google.gson.annotations.SerializedName;

public class BanmaerpOrderEnums {
    public enum Status {
        @SerializedName(value = "已下单")
        Ordered("已下单"),
        @SerializedName(value = "待审核")
        Audit("待审核"),
        @SerializedName(value = "待发货")
        PrepareDeliver("待发货"),
        @SerializedName(value = "部分发货")
        PartialDeliver("部分发货"),
        @SerializedName(value = "已发货")
        Delivered("已发货"),
        @SerializedName(value = "已取消")
        Cancel("已取消"),
        @SerializedName(value = "Unknown")
        Unknown("Unknown"),
        @SerializedName(value = "All")
        All("All");
        private String value;

        Status(String _value) {
            this.value = _value;
        }

        public String getValue() {
            return value;
        }

        public String valueOf(){
            return value;
        }
        public static Status valueof(String _value){
            switch (_value){
                case "已下单": return Ordered;
                case "待审核": return Audit;
                case "待发货": return PrepareDeliver;
                case "部分发货": return PartialDeliver;
                case "已发货": return Delivered;
                case "已取消": return Cancel;
                case "Unknown": return Unknown;
                case "All": return All;
            }
            return Unknown;
        }
    }

    public enum PayStatus {
        // 未付款
        NonPayment("未付款"),
        // 已付款
        Paid("已付款");
        private String payStatus;

        PayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
    }

    public enum HoldStatus {
        // 未暂停
        NotPaused("未暂停"),
        // 已暂停
        Paused("已暂停");
        private String holdStatus;

        HoldStatus(String holdStatus) {
            this.holdStatus = holdStatus;
        }
    }

    public enum RefundStatus {
        // 无退款
        NoRefunds("无退款"),
        // 全部退款
        FullRefund("全部退款"),
        // 部分退款
        PartialRefund("部分退款"),
        // 待同步
        ToBeSynchronized("待同步");
        private String refundStatus;

        RefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }
    }

    public enum InventoryStatus {
        // 未知
        Unknown("未知"),
        // 有货
        InStock("有货"),
        // 部分缺货
        SomeAreOutOfStock("部分缺货"),
        // 全部缺货
        AllOutOfStock("全部缺货");
        private String inventoryStatus;

        InventoryStatus(String inventoryStatus) {
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

        SearchTimeField(String searchTimeField) {
            this.searchTimeField = searchTimeField;
        }
    }

    public enum InventoryMode {
        // 本地仓库存
        LocalWarehouseStorage("本地仓库存"),
        // 第三方仓库存
        ThirdPartyWarehouseStorage("第三方仓库存"),
        // 无库存发货
        ShippedOutOfStock("无库存发货");
        private String inventoryMode;

        InventoryMode(String inventoryMode) {
            this.inventoryMode = inventoryMode;
        }
    }

    public enum SKUType {
        // 普通产品
        Ordinary("普通"),
        // 组合产品
        Combination("组合");
        private String sKUType;

        SKUType(String sKUType) {
            this.sKUType = sKUType;
        }
    }

    public enum SkuChangeStatus {
        // 未变更
        NoChange("未变更"),
        // 已换货
        Exchanged("已换货");
        private String skuChangeStatus;

        SkuChangeStatus(String skuChangeStatus) {
            this.skuChangeStatus = skuChangeStatus;
        }
    }

    public enum Type {
        // 正常产品
        Ordinary("普通"),
        // 补发产品
        Reissue("补发"),
        // 赠品
        Gifts("赠品");
        private String type;

        Type(String type) {
            this.type = type;
        }
    }

    public enum QuantityStatus {
        // 原始值
        Raw("原始"),
        // 已调整后的值
        Adjusted("已调整");
        private String quantityStatus;

        QuantityStatus(String quantityStatus) {
            this.quantityStatus = quantityStatus;
        }
    }

    public enum AddrType {
        // 未知
        Unknown("未知"),
        // 收货地址
        ShippingAddress("收货地址"),
        // 账单地址
        BillingAddress("账单地址");
        private String addrType;

        AddrType(String addrType) {
            this.addrType = addrType;
        }
    }

    public enum Category {
        // 买家添加的备注
        OriginalComments("原始备注"),
        // 客服添加的备注
        CustomerServiceNotes("客服备注"),
        // 物流添加的备注
        LogisticsNotes("物流备注"),
        // 采购添加的备注
        PurchaseNotes("采购备注");
        private String category;

        Category(String category) {
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

        SortField(String sortField) {
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

        RiskLevel(String riskLevel) {
            this.riskLevel = riskLevel;
        }
    }
}
