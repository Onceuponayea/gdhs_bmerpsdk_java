package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "RefundTime")
    private DateTime refundTime;
}
