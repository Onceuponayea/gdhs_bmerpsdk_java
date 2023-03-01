package com.hrghs.xycb.domains;

public class BanmaerpURL {
    public static String banmaerp_gateway = "https://gateway.banmaerp.com";
    public final static String banmaerp_GetToken_GET = "/v1/Auth/GetToken";
    public final static String banmaerp_RefreshToken_GET = "/v1/Auth/RefreshToken?refreshToken=%s";
    public final static String banmaerp_productlist_GET = "/v1/product?SPUIDS=%s&Source=%s&Spu=%s&CategoryID=%s&Title=%s&Supplier=%s&CostPriceStart=%s&CostPriceEnd=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_product_GET = "/v1/product/%s";
    public final static String banmaerp_product_PUT = "/v1/product/%s";
    public final static String banmaerp_product_POST = "/v1/product/";
    public final static String Banmaerp_product_sku_GET = "/v1/product/sku/%s";
    public final static String Banmaerp_product_skulist_GET = "/v1/product/sku?SKUIDs=%s&Code=%s&SPUID=%s&CostPriceStart=%s&CostPriceEnd=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String Banmaerp_taglist_GET = "/v1/product/tag?Name=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String Banmaerp_supplierlist_GET = "/v1/product/supplier?Name=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_order_GET = "/v1/order?WithAccessUser=true&IDs=%s&StoreID=%s&Platform=%s&Status=%s&PayStatus=%s&HoldStatus=%s&RefundStatus=%s&InventoryStatus=%s&CountryCode=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_orderdetail_GET = "/v1/order/%s";
    public final static String banmaerp_orderFulfillments_GET = "/v1/order/%s/fulfillments";
    public final static String banmaerp_orderTrackings_GET = "/v1/order/%s/trackings";
    /**
     * 0=返回认证令牌(默认值)  1=浏览器直接跳转
     **/
    public final static String banmaerp_sso_GET = "/v1/sso/Passport?Account=%s&ClientIP=%s&UserID=%d&Mode=%d";
    public final static String banmaerp_ssoRegister_POST = "/v1/sso/Register";
    public final static String banmaerp_categorylist_GET = "/v1/category?IDs=%s&Name=%S&ParentID=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_category_GET = "/v1/category/%s";
    public final static String banmaerp_category_POST= "/v1/category";
    public final static String banmaerp_storelist_GET = "/v1/store?ids=%s&name=%s&platform=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s" +
            "&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_storelist_GET_Simple = "/v1/store?PageNumber=%d";
    public final static String banmaerp_store_GET = "/v1/store/%s";
    public final static String banmaerp_accountlist_GET = "/v1/Account?IDs=%s&Email=%s&Phone=%s&RealName=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s" +
            "&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_account_GET = "/v1/Account/%d";
    public final static String banmaerp_accountDataAccess_GET = "/v1/Account/%d/DataAccess/Store";
    public final static String banmaerp_accountAdd_POST = "/v1/account";
    public final static String banmaerp_accountLogout_DELETE = "/v1/account/%d";
    public final static String banmaerp_accountPrivileges_GET = "/v1/Account/%ld/DataAccess/Store";

    public final static String banmaerp_storage_stream_POST = "/v1/storage/stream?name=%s";
    public final static String banmaerp_storage_base64_POST = "/v1/storage/base64?name=%s";
    public final static String banmaerp_storage_form_POST = "/v1/storage/form";
    public final static String banmaerp_storagelist_GET = "/v1/storage?IDs=%s&Name=%s&FileType=%s&FileCategoryID=%s&PageNumber=%d&PageSize=%d&SearchTimeStart=%s&SearchTimeEnd=%s&SearchTimeField=%s&SortField=%s&SortBy=%s";
    public final static String banmaerp_storage_GET = "/v1/storage/%s";


}
