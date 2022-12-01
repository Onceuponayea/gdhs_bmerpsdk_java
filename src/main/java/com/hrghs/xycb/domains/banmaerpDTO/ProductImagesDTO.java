package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_product_images")
public class ProductImagesDTO {


    @Id
    @JsonIgnore
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "product_ImageId")
    private UUID productImageId;

    @JsonProperty(value = "Src")
    private String src;
    @JsonProperty(value = "Sort")
    private int sort;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDTO productDTO;
}
