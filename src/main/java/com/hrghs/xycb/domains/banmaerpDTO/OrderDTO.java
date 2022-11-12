package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderDTO {
    // 变量不要大写开头
    // 枚举enum也要建
    @JsonProperty(value = "Master")
    private OrderMasterDTO master;
    @JsonProperty(value = "Details")
    private OrderDetailsDTO[] details;
    @JsonProperty(value = "Addresses")
    private OrderAddressesDTO[] addresses;
    @JsonProperty(value = "Remarks")
    private OrderRemarksDTO[] remarks;
    @JsonProperty(value = "Refunds")
    private OrderRefundsDTO[] refunds;

}
