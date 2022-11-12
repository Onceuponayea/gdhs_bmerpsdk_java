package com.hrghs.xycb.domains.enums;

public class BanmaerpProductEnums {

    public enum Source{
        // 平台同步
        PlatformSynchronization("PlatformSynchronization"),
        // 手工创建
        CreatedByHand("CreatedByHand"),
        // 产品库
        ProductLibrary("ProductLibrary"),
        // 数据采集
        DataAcquisition("DataAcquisition"),
        // 数据搬家
        DataMoving("DataMoving");
        private String source;

        private Source(String source) {
            this.source = source;
        }
    }

    public enum SettlementType{
        // 货到付款
        CashOnDelivery("cashOnDelivery"),
        // 款到发货
        PaymentToDelivery("PaymentToDelivery"),
        // 账期
        AccountPeriod("AccountPeriod");
        private String settlementType;

        private SettlementType(String settlementType) {
            this.settlementType = settlementType;
        }
    }

    public enum SortField_SPU{
        // SPUID
        SPUID("SPUID"),
        // 创建时间
        CreateTime("CreateTime"),
        // 更新时间
        UpdateTime("UpdateTime");
        private String sortField_SPU;

        private SortField_SPU(String sortField_SPU) {
            this.sortField_SPU = sortField_SPU;
        }
    }

    public enum SortField_SKU{
        // SKUID
        SKUID("SKUID"),
        // 创建时间
        CreateTime("CreateTime"),
        // 更新时间
        UpdateTime("UpdateTime");
        private String sortField_SKU;

        private SortField_SKU(String sortField_SKU) {
            this.sortField_SKU = sortField_SKU;
        }
    }
}
