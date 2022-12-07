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
@Table(name = "bmerp_product_skus_options")
public class ProductSkusOptionsDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @Column(name = "sku_optionId")
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID skuOption;

    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Value")
    private String value;

    @JsonIgnore
    @JoinColumn(name = "psku_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductSkusDTO productSkusDTO;
}
