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
@Table(name = "bmerp_product_skus_options")
public class ProductSkusOptionsDTO {

    @Id
    private int id;

    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Value")
    private String value;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)
    @JoinColumn(name="sku_id")
    private ProductSkusDTO productSkusDTO;
}
