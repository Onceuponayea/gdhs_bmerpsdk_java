package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_product_skus")
public class ProductSkusDTO implements Serializable {

    @Column(name = "sku_id")
    @Id
    @JsonProperty(value = "SKUID")
    private Long SKUID;

    @JsonIgnore
    @JoinColumn(name = "sku_id",nullable = false,insertable = false,updatable = false)
    @ManyToOne
    private ProductDTO productDTO;

    @JsonProperty(value = "Code")
    private String code;

    @JsonProperty(value = "Specification")
    private String specification;

    @Column(name = "cost_price")
    @JsonProperty(value = "CostPrice")
    private String costPrice;

    @JsonProperty(value = "Image")
    private String image;

    @JsonProperty(value = "Weight")
    private int weight;

    @JsonProperty(value = "Length")
    private int length;

    @JsonProperty(value = "Width")
    private int width;

    @JsonProperty(value = "Height")
    private int height;

    @Column(name = "is_valid")
    @JsonProperty(value = "IsValid")
    private boolean isValid;

    @JsonProperty(value = "Status")
    private String status;

    @JsonProperty(value = "Remark")
    private String remark;

    @JsonProperty(value = "Sort")
    private int sort;

    @JsonProperty(value = "Type")
    private String type;

    @OneToMany(mappedBy = "productSkusDTO",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonProperty(value = "CombineData")
    private List<ProductSkusCombineDataDTO> combineData;

    @OneToMany(mappedBy = "productSkusDTO",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonProperty(value = "Options")
    private List<ProductSkusOptionsDTO> options;

}
