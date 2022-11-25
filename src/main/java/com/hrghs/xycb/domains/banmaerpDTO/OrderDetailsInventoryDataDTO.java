package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_order_details_inventorydata")
public class OrderDetailsInventoryDataDTO {

    @Id
    private String id;

    @JsonProperty(value = "SKUID")
    private String SKUID;
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "ShortageQuantity")
    private int shortageQuantity;
    @JsonProperty(value = "LockQuantity")
    private int lockQuantity;
    @JsonProperty(value = "SPUID")
    private String SPUID;
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)
    @JoinColumn(name="ID")
    private OrderDetailsDTO orderDetailsDTO;
}
