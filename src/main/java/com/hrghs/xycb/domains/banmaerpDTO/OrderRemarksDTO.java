package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_order_remarks")
public class OrderRemarksDTO {

    @Id
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderDTO orderDTO;

    @JsonProperty(value = "Category")
    private String category;
    @JsonProperty(value = "Content")
    private String content;

    @Column(name = "create_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
}
