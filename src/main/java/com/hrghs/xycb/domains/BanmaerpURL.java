package com.hrghs.xycb.domains;

import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import org.joda.time.DateTime;

public class BanmaerpURL {
    public static String banmaerp_gateway = "https://gateway.banmaerp.com";
    public final static String banmaerp_GetToken_GET = "/v1/Auth/GetToken";
    public final static String banmaerp_RefreshToken_GET = "/v1/Auth/RefreshToken?refreshToken=%s";
    public final static String banmaerp_productlist_GET = "/v1/product?PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s";
    public final static String banmaerp_product_GET = "/v1/product/%s";
    public final static String banmaerp_product_PUT = "/v1/product/%s";
    public final static String banmaerp_product_POST = "/v1/product/";
    public final static String Banmaerp_product_sku_GET = "/v1/product/sku/%s";
    public final static String Banmaerp_product_skulist_GET = "/v1/product/sku?skuids=%s&spuid=%s";
    public final static String Banmaerp_taglist_GET = "/v1/product/tag?name=%s";
    public final static String Banmaerp_supplierlist_GET = "/v1/product/supplier?name=%s";
    public final static String banmaerp_order_GET = "/v1/order?PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s";
    public final static String banmaerp_orderdetail_GET = "/v1/order/%s";
    /**
     * 0=返回认证令牌(默认值)  1=浏览器直接跳转
     **/
    public final static String banmaerp_sso_GET = "/v1/sso/Passport?Account=%s&ClientIP=%s&Mode=%d";
    public final static String banmaerp_categorylist_GET = "/v1/category?PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s";
    public final static String banmaerp_category_GET = "/v1/category/%s";
    public final static String banmaerp_storelist_GET = "/v1/store?ids=%s&name=%s&platform=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s" +
            "&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_store_GET = "/v1/store/%s";
    public final static String banmaerp_accountlist_GET = "/v1/Account?IDs=%s&Email=%s&Phone=%s&RealName=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s" +
            "&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_account_GET = "/v1/Account/%d";
    public final static String banmaerp_accountDataAccess_GET = "/v1/Account/%d/DataAccess/Store";
    public final static String banmaerp_accountAdd_POST = "/v1/account";
    public final static String banmaerp_accountLogout_DELETE = "/v1/account/%d";
    public final static String banmaerp_accountPrivileges_GET = "/v1/Account/%ld/DataAccess/Store";
    public final static String banmaerp_storagelist_GET = "/v1/storage?PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s";
    public final static String banmaerp_storage_GET = "/v1/storage/%s";
    public final static String banmaerp_storage_stream_POST = "/v1/storage/stream?name=%s";
    public final static String banmaerp_storage_base64_POST = "/v1/storage/base64?name=%s";
//    private final static String banmaerp_gateway = "";
//    private final static String banmaerp_gateway = "";

}
