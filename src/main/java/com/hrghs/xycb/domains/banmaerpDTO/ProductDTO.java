package com.hrghs.xycb.domains.banmaerpDTO;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import com.hrghs.xycb.utils.converters.ProductDeserialiser;
import com.hrghs.xycb.utils.converters.ProductSerialiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static jodd.util.StringPool.COMMA;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_product")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Product")
public class ProductDTO implements Serializable {
    /**
     * 商品表可能无限增长，会增长到需要拆表的地步，所以需要提前设计成UUID方式
     */
    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @Column(name = "product_uuid")
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    private UUID productUUId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true, optional = true)
    @JoinColumn(name = "product_spuid", referencedColumnName = "spu_id")
    @JsonManagedReference
    @JsonProperty(value = "SPU")
    private ProductSpuDTO SPU;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true)
    @JoinColumn(name = "product_DescriptionId", referencedColumnName = "pd_id")
    @JsonManagedReference
    @JsonProperty(value = "Descriptions")
    private ProductDescriptionsDTO descriptions;

    @JsonSerialize(using = ProductSerialiser.ProductSkusSerializer.class)
    @JsonDeserialize(using = ProductDeserialiser.ProductSkusDeserializer.class)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonProperty(value = "SKUs")
    private List<ProductSkusDTO> SKUs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Options")
    @JoinColumn(name = "product_id")
    private List<ProductOptionsDTO> options;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Images")
    @JoinColumn(name = "product_id")
    private List<ProductImagesDTO> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Suppliers")
    @JoinColumn(name = "product_id")
    private List<ProductSuppliersDTO> suppliers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Requirements")
    @JoinColumn(name = "product_id")
    private List<ProductRequirementsDTO> requirements;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Sources")
    @JoinColumn(name = "product_id")
    private List<ProductSourcesDTO> sources;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "Tags")
    @JoinColumn(name = "product_id")
    private List<ProductTagsDTO> tags;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(value = "PackMaterials")
    @JoinColumn(name = "product_id")
    private List<ProductPackMaterialsDTO> packMaterials;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;

}
