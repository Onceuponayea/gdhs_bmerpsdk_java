package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import com.hrghs.xycb.utils.converters.JpaUserAccessOrderConverter;
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
    @Convert(converter = JpaUUIDConverter.class)
    @Column(name = "order_UUID")
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID orderUUID;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true,optional = true)
    @JoinColumn(name = "order_masterId", referencedColumnName = "id")
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

    @Column(name = "banma_account_id")
    @Convert(converter = JpaUserAccessOrderConverter.class)
    @JsonProperty(value = "AccessUsers")
    private List<OrderAccessUsersDTO> accessUsers;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;
}
