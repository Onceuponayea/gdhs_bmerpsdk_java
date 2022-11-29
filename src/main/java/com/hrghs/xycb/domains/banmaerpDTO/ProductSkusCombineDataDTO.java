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
    @Column(name = "sku_code")
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "Quantity")
    private int quantity;

    @JsonIgnore
    @JoinColumn(name = "psku_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductSkusDTO productSkusDTO;
}
