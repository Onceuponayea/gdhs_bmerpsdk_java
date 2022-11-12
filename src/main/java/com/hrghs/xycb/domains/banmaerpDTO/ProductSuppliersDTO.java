package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSuppliersDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Sort")
    private int sort;
    @JsonProperty(value = "Info")
    private ProductSuppliersInfoDTO info;
}
