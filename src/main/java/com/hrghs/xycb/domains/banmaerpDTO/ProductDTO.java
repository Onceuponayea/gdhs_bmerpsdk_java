package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.utils.converters.ProductDeserialiser;
import com.hrghs.xycb.utils.converters.ProductSerialiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_product")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Product")
public class ProductDTO implements Serializable {
    /**
     * 商品表可能无限增长，会增长到需要拆表的地步，所以需要提前设计成UUID方式
     */
    @JsonIgnore
    @Column(name = "product_uuid")
    @Type(type = "uuid-char")
    @Id
    @GeneratedValue
    private UUID productUUId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_spuid",referencedColumnName = "spu_id")
//    @JsonSerialize(contentUsing = ProductSerialiser.ProductSpuSerializer.class)
//    @JsonDeserialize(contentUsing = ProductDeserialiser.ProductSpuDeserializer.class)
    @JsonProperty(value = "SPU")
    private ProductSpuDTO SPU;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_DescriptionId",referencedColumnName = "pd_id")
    @JsonProperty(value = "Descriptions")
    private ProductDescriptionsDTO descriptions;

    @JsonIgnore
    private Long product_skuid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "SKUs")
    private List<ProductSkusDTO> SKUs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Options")
    private List<ProductOptionsDTO> options;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Images")
    private List<ProductImagesDTO> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Suppliers")
    private List<ProductSuppliersDTO> suppliers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Requirements")
    private List<ProductRequirementsDTO> requirements;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Sources")
    private List<ProductSourcesDTO> sources;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "Tags")
    private List<ProductTagsDTO> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDTO")
    @JsonProperty(value = "PackMaterials")
    private List<ProductPackMaterialsDTO> packMaterials;

}
