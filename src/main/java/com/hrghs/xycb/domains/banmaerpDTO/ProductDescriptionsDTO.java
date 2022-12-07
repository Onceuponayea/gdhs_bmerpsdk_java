package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_product_descriptions")
public class ProductDescriptionsDTO {

    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "pd_id")
    private UUID productDescriptionId;

    @JsonProperty(value = "Html")
    private String html;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "Short")
    private String Short;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_uuid")
    @JsonBackReference
    private ProductDTO productDTO;

}
