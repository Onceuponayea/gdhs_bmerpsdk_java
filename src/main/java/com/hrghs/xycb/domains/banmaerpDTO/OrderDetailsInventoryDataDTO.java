package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderDetailsInventoryDataDTO {
    @JsonProperty(value = "SKUID")
    private long skuId;
    @JsonProperty(value = "SKUCode")
    private String skuCode;
    @JsonProperty(value = "ShortageQuantity")
    private int shortageQuantity;
    @JsonProperty(value = "LockQuantity")
    private int lockQuantity;
    @JsonProperty(value = "SPUID")
    private long spuId;
}
