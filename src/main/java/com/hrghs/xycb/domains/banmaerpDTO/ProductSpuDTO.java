package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSpuDTO {
    @JsonProperty(value = "SPUID")
    private String spuId;
    @JsonProperty(value = "Code")
    private String code;
    @JsonProperty(value = "Title")
    private String title;
    @JsonProperty(value = "LeiMuID")
    private String leiMuID;
    @JsonProperty(value = "Image")
    private String image;
    @JsonProperty(value = "Source")
    private String source;
    @JsonProperty(value = "DefaultSupplierID")
    private String defaultSupplierID;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "IsExemptQuality")
    private boolean isExemptQuality;
    @JsonProperty(value = "Keywords")
    private String keywords;
    @JsonProperty(value = "CreateTime")
    private String createTime;
    @JsonProperty(value = "UpdateTime")
    private String updateTime;
}
