package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.rmi.server.UID;

@Component
@Data
@Entity
@Table(name = "bmerp_product_skus_combinedata")
public class ProductSkusCombineDataDTO {
    @JsonIgnore
    @GeneratedValue
    @Id
    private UID sku_cmbdId;
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "Quantity")
    private int quantity;

    @JsonIgnore
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)
    @JoinColumn(name="sku_cmbdId",nullable = false,insertable = false,updatable = false)
    private ProductSkusDTO productSkusDTO;
}
