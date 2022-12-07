package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_order_refunds")
public class OrderRefundsDTO {

    @Id
    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "order_refund_id")
    private UUID order_refund_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderDTO orderDTO;

    @Column(name = "detail_id")
    @JsonProperty(value = "DetailID")
    private String detailID;

    @Column(name = "original_refund_id")
    @JsonProperty(value = "OrignalRefundID")
    private String orignalRefundID;

    @Column(name = "refund_amount")
    @JsonProperty(value = "RefundAmount")
    private Double refundAmount;

    @Column(name = "refund_currency")
    @JsonProperty(value = "RefundCurrency")
    private String refundCurrency;

    @Column(name = "refund_type")
    @JsonProperty(value = "RefundType")
    private String refundType;

    @Column(name = "refund_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "RefundTime")
    private DateTime refundTime;
}
