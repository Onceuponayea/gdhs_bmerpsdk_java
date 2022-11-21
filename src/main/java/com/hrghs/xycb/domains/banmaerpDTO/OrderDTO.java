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
@JsonTypeName("Order")
public class OrderDTO {
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
