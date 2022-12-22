package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@NoArgsConstructor
@Table(name = "bmerp_product_images")
public class ProductImagesDTO {

    public ProductImagesDTO(String _src,Integer _sort){
        this.src = _src;
        this.sort = _sort;
    }

    @Id
    @Convert(converter = JpaUUIDConverter.class)
    @JsonIgnore
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "product_ImageId")
    private UUID productImageId;

    @JsonProperty(value = "Src")
    private String src;
    @JsonProperty(value = "Sort")
    private Integer sort;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductDTO productDTO;
}
