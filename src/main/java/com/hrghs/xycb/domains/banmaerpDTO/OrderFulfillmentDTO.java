package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bmerp_order_fulfillment")
public class OrderFulfillmentDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID id;

    @JsonProperty(value = "ExpressCompany")
    @Column(name = "express_company")
    private String expressCompany;

    @JsonProperty(value = "ExpressNo")
    @Column(name = "express_no")
    private String expressNo;

    @JsonProperty(value = "DeliveryTime")
    @Column(name = "delivery_time")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime deliveryTime;

    @JsonIgnore
    @Column(name = "order_id")
    private String orderId;
}
