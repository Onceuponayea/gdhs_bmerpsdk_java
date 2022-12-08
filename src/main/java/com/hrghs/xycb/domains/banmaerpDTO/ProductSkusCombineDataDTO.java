package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "bmerp_product_skus_combinedata")
public class ProductSkusCombineDataDTO {
    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Id
    private UUID sku_cmbdId;

    @Column(name = "sku_code")
    @JsonProperty(value = "SKUCode")
    private String SKUCode;
    @JsonProperty(value = "Quantity")
    private Integer quantity;

    @JsonIgnore
    @JoinColumn(name = "psku_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductSkusDTO productSkusDTO;
}
