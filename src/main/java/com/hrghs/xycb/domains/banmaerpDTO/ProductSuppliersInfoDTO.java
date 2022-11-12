package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSuppliersInfoDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Contact")
    private String contact;
    @JsonProperty(value = "Phone")
    private String phone;
    @JsonProperty(value = "Address")
    private String address;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Url")
    private String url;
    @JsonProperty(value = "QQ")
    private String qq;
    @JsonProperty(value = "WeChat")
    private String weChat;
    @JsonProperty(value = "WangWang")
    private String wangWang;
    @JsonProperty(value = "SettlementType")
    private String settlementType;
}
