package com.hrghs.xycb.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

@Scope("prototype")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BanmaerpSigningVO {

    private String app_id;
    private String app_secret;
    private Long timestamp = System.currentTimeMillis()/1000L;
    /**
     * @@apiNote banmaErp require String format.
     */
    private HttpMethod request_method;
    /**
     * @@apiNote api path
     */
    private String request_path;
    /**
     * @@apiNote request parameters in url
     */
    private String request_query;
    /**
     * @@implNote request over non form-data or urlencoding
     * e.g. JSON.stringify({ "Spu": { "Code": "1005002034496358222" } });
     */
    private String request_body;
    @Value("${erp.banmaerp.X_BANMA_MASTER_SIGN_ALGORITHM}")
    private String sign_algorithm=SHA256.split("#")[1];
}
