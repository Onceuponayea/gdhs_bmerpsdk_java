package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data
@Entity
@Table(name = "bmerp_order_refunds")
public class OrderRefundsDTO {

    @Id
    private int id;

    @JsonProperty(value = "DetailID")
    private String detailID;
    @JsonProperty(value = "OrignalRefundID")
    private String orignalRefundID;
    @JsonProperty(value = "RefundAmount")
    private double refundAmount;
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;
    @JsonProperty(value = "RefundType")
    private String refundType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "RefundTime")
    private String refundTime;
}
