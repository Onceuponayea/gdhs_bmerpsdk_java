package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.domains.BanmaerpProperties;
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
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "SKU")
public class ProductSkusDTO implements Serializable {


    @Id
    @Column(name = "sku_id")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "SKUID")
    private Long SKUID;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
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
    private Integer weight;

    @JsonProperty(value = "Length")
    private Integer length;

    @JsonProperty(value = "Width")
    private Integer width;

    @JsonProperty(value = "Height")
    private Integer height;

    @Column(name = "is_valid")
    @JsonProperty(value = "IsValid")
    private Boolean isValid;

    @JsonProperty(value = "Status")
    private String status;

    @JsonProperty(value = "Remark")
    private String remark;

    @JsonProperty(value = "Sort")
    private Integer sort;

    @JsonProperty(value = "Type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "psku_id")
    @JsonProperty(value = "CombineData")
    private List<ProductSkusCombineDataDTO> combineData;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "psku_id")
    @JsonProperty(value = "Options")
    private List<ProductSkusOptionsDTO> options;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;

}
