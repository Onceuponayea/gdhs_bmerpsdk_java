package com.hrghs.xycb.domains;

public class BanmaerpURL {
    public  static String banmaerp_gateway = "https://gateway.banmaerp.com";
    public final static String banmaerp_GetToken_GET = "/v1/Auth/GetToken";
    public final static String banmaerp_RefreshToken_GET = "/v1/Auth/RefreshToken?refreshToken=%s";
    public final static String banmaerp_productlist_GET = "/v1/product/?PageNumber=%d&PageSize=%d&SearchTimeStart=%tF&SearchTimeEnd=%tF";
    public final static String banmaerp_product_GET = "/v1/product/%ld";
    public final static String banmaerp_product_PUT = "/v1/product/%ld";
    public final static String banmaerp_product_POST = "/v1/product/";
    public final static String banmaerp_order_GET = "/v1/order?PageNumber=%d&PageSize=%d&SearchTimeStart=%tF&SearchTimeEnd=%tF";
    public final static String banmaerp_orderdetail_GET = "/v1/order/%ld";
    /**0=返回认证令牌(默认值)  1=浏览器直接跳转 **/
    public final static String banmaerp_sso_GET = "/v1/sso/Passport?Account=%s&ClientIP=%s&Mode=%d";
    public final static String banmaerp_categorylist_GET = "/v1/category?PageNumber=%d&PageSize=%d&SearchTimeStart=%tF&SearchTimeEnd=%tF";
    public final static String banmaerp_category_GET = "/v1/category/%s";
    public final static String banmaerp_storelist_GET = "/v1/store?PageNumber=%d&PageSize=%d&SearchTimeStart=%tF&SearchTimeEnd=%tF";
    public final static String banmaerp_store_GET = "/v1/store/%ld";

//    private final static String banmaerp_gateway = "";
//    private final static String banmaerp_gateway = "";

}
