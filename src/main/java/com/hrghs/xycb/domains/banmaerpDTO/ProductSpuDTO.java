package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data
@Entity
@Table(name = "bmerp_product_spu")
public class ProductSpuDTO {

    @Id
    @JsonProperty(value = "SPUID")
    private String SPUID;
    @JsonProperty(value = "Code")
    private String code;
    @JsonProperty(value = "Title")
    private String title;
    @JsonProperty(value = "LeiMuID")
    private String leiMuID;
    @JsonProperty(value = "SourceUrl")
    private String sourceUrl;
    @JsonProperty(value = "TagIDs")
    private String tagIDs;
    @JsonProperty(value = "Image")
    private String image;
    @JsonProperty(value = "Status")
    private String status;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private String createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private String updateTime;
}
