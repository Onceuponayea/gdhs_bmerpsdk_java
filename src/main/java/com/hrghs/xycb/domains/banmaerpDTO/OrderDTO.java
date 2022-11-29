package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.utils.converters.ProductDeserialiser;
import com.hrghs.xycb.utils.converters.ProductSerialiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_order")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Order")
public class OrderDTO {

    @JsonIgnore
    @Column(name = "order_UUID")
    @GeneratedValue
    @Type(type = "uuid-char")
    @Id
    private UUID orderUUID;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,optional = true)
    @JoinColumn(name = "order_masterId",referencedColumnName = "id")
    @JsonManagedReference
    @JsonProperty(value = "Master")
    private OrderMasterDTO master;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonProperty(value = "Details")
    private List<OrderDetailsDTO> details;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonProperty(value = "Addresses")
    private List<OrderAddressesDTO> addresses;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonProperty(value = "Remarks")
    private List<OrderRemarksDTO> remarks;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonProperty(value = "Refunds")
    private List<OrderRefundsDTO> refunds;

}
