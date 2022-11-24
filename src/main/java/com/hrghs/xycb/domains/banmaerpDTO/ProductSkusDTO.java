package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("SKU")
public class ProductSkusDTO {
    @JsonProperty(value = "SKUID")
    private String SKUID;
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
