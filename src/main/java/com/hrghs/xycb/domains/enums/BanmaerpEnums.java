package com.hrghs.xycb.domains.enums;

public class BanmaerpEnums {

    public enum Platform {
        Amazon("Amazon"),EBay("EBay"),Aliexpress("Aliexpress"),Wish("Wish"),Shopee("Shopee"),
        Rakuten("Rakuten"),Shopify("Shopify"),Shoplazza("Shoplazza"),Lazada("Lazada"),Yahoo("Yahoo"),
        MeShop("MeShop"),AllValue("AllValue"),Shopline("Shopline"),Walmart("Walmart"),WooCommerce("WooCommerce"),
        Fordeal("Fordeal"),Shopyy("Shopyy"),FunPinPin("FunPinPin"),Magento("Magento"),ShopeeGlobal("ShopeeGlobal"),
        Shoplus("Shoplus"),LeadongShop("LeadongShop"),Shoprises("Shoprises"),Qoo10("Qoo10"),
        ShopExpress("ShopExpress"),TwoCshop("TwoCshop"),Self("Self");
        private String platform;
        private Platform(String _platform){
            this.platform = _platform;
        }
    }

    public enum OrderStatus {
        Ordered("Ordered"),Audit("Audit"),PrepareDeliver("PrepareDeliver"),PartialDeliver("PartialDeliver"),
        Delivered("Delivered"),Cancel("Cancel");
        private String orderStatus;
        private OrderStatus(String _orderStatus){
            this.orderStatus = _orderStatus;
        }
    }


}
