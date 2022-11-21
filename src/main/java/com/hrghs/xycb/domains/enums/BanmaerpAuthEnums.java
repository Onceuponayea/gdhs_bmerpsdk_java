package com.hrghs.xycb.domains.enums;

public class BanmaerpAuthEnums {
    public enum AuthMethod {
        SIGNING("签名"),
        IP_WHITELIST("IP白名单"),
        MIXED("签名+IP白名单");
        private String authType;

        AuthMethod(String authType) {
            this.authType = authType;
        }

        public String getAuthType() {
            return authType;
        }
    }
}
