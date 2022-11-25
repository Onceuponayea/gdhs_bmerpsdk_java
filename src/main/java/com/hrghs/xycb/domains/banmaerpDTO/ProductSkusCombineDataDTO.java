package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_product_skus_combinedata")
public class ProductSkusCombineDataDTO {

    @Id
    private int id;
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "Quantity")
    private int quantity;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)
    @JoinColumn(name="sku_id")
    private ProductSkusDTO productSkusDTO;
}
