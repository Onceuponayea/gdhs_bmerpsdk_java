package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSkusDTO {
    @JsonProperty(value = "SKUID")
    private String skuId;
    @JsonProperty(value = "Code")
    private String code;
    @JsonProperty(value = "Specification")
    private String specification;
    @JsonProperty(value = "CostPrice")
    private String costPrice;
    @JsonProperty(value = "Image")
    private String image;
    @JsonProperty(value = "Weight")
    private int weight;
    @JsonProperty(value = "Length")
    private int length;
    @JsonProperty(value = "Width")
    private int width;
    @JsonProperty(value = "Height")
    private int height;
    @JsonProperty(value = "IsValid")
    private boolean isValid;
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Sort")
    private int sort;
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "CombineData")
    private ProductSkusCombineDataDTO[] combineData;
    @JsonProperty(value = "Options")
    private ProductSkusOptionsDTO[] options;




}
