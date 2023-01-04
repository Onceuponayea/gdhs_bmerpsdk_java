package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "bmerp_order_tracking_info")
public class OrderTrackingTrackInfoDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID id;

    @JsonProperty(value = "Date")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private DateTime date;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Location")
    private String location;

    @JsonProperty(value = "Status")
    private String status;

    @JsonIgnore
    @JoinColumn(name = "track_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderTrackingDTO trackingDTO;

}
