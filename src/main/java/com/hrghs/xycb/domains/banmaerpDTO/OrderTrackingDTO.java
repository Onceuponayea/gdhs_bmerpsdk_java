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

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_order_tracking")
public class OrderTrackingDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID id;

    @JsonProperty(value = "Status")
    private String status;

    @Column(name = "express_no")
    @JsonProperty(value = "ExpressNo")
    private String expressNo;

    @JsonProperty(value = "ReceiptedTime")
    @Convert(converter = JodaDateTimeConverter.class)
    @Column(name = "receipted_time")
    private DateTime receiptedTime;

    @JsonProperty(value = "UpdateTime")
    @Convert(converter = JodaDateTimeConverter.class)
    @Column(name = "update_time")
    private DateTime updateTime;

    @JsonIgnore
    @Column(name = "order_id")
    private String orderId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonProperty(value = "TrackInfo")
    private List<OrderTrackingTrackInfoDTO> trackInfo;
}
