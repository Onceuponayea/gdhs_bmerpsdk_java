package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_order_details_inventorydata")
public class OrderDetailsInventoryDataDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Column(name = "order_inventoryData_id")
    @Type(type = "uuid-char")
    @Id
    private UUID order_inventoryData_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    private OrderDetailsDTO orderDetailsDTO;

    @Column(name = "sku_id")
    @JsonProperty(value = "SKUID")
    private String SKUID;

    @Column(name = "sku_code")
    @JsonProperty(value = "SKUCode")
    private String SKUCode;

    @Column(name = "shortage_quantity")
    @JsonProperty(value = "ShortageQuantity")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer shortageQuantity;

    @Column(name = "lock_quantity")
    @JsonProperty(value = "LockQuantity")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer lockQuantity;

    @Column(name = "spu_id")
    @JsonProperty(value = "SPUID")
    private String SPUID;

}
