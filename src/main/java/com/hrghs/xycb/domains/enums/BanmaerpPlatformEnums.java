package com.hrghs.xycb.domains.enums;

import org.springframework.util.StringUtils;

public class BanmaerpPlatformEnums {

    public enum Platform {
        Amazon("Amazon"),EBay("EBay"),Aliexpress("Aliexpress"),Wish("Wish"),Shopee("Shopee"),
        Rakuten("Rakuten"),Shopify("Shopify"),Shoplazza("Shoplazza"),Lazada("Lazada"),Yahoo("Yahoo"),ShoplineV2("ShoplineV2"),
        MeShop("MeShop"),AllValue("AllValue"),Shopline("Shopline"),Walmart("Walmart"),WooCommerce("WooCommerce"),
        Fordeal("Fordeal"),Shopyy("Shopyy"),FunPinPin("FunPinPin"),Magento("Magento"),AlibabaGlobal("AlibabaGlobal"),ShopeeGlobal("ShopeeGlobal"),
        Shoplus("Shoplus"),LeadongShop("LeadongShop"),Shoprises("Shoprises"),Qoo10("Qoo10"),Coupang("Coupang"),Mercadolibre("Mercadolibre")
        ,TiktokShop("TiktokShop"),ShopExpress("ShopExpress"),TwoCshop("TwoCshop"),UeeShop("UeeShop"),Netsea("Netsea"),Self("Self");
        private String platform;
        Platform(String _platform){
            this.platform = _platform;
        }

        @Override
        public String toString() {
            return StringUtils.hasText(platform)?platform:"";
//            return "Platform{" +
//                    "platform='" + platform + '\'' +
//                    '}';
        }
    }




}
