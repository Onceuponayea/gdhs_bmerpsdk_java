package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSkusCombineDataDTO {
    @JsonProperty(value = "SKUCode")
    private String skuCode;
    @JsonProperty(value = "Quantity")
    private int quantity;
}
