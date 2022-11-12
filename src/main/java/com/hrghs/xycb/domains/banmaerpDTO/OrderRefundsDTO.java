package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderRefundsDTO {
    @JsonProperty(value = "DetailID")
    private String detailID;
    @JsonProperty(value = "OrignalRefundID")
    private String orignalRefundID;
    @JsonProperty(value = "RefundAmount")
    private String refundAmount;
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;
    @JsonProperty(value = "RefundType")
    private String refundType;
    @JsonProperty(value = "RefundTime")
    private DateTime refundTime;
}
