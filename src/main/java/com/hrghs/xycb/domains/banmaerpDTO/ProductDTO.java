package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Product")
public class ProductDTO {
    @JsonProperty(value = "SPU")
    private ProductSpuDTO spu;
    @JsonProperty(value = "Descriptions")
    private ProductDescriptionsDTO descriptions;
    @JsonProperty(value = "SKUs")
    private ProductSkusDTO[] skus;
    @JsonProperty(value = "Options")
    private ProductOptionsDTO[] options;
    @JsonProperty(value = "Images")
    private ProductImagesDTO[] images;
    @JsonProperty(value = "Suppliers")
    private ProductSuppliersDTO[] suppliers;
    @JsonProperty(value = "Requirements")
    private ProductRequirementsDTO[] requirements;
    @JsonProperty(value = "Sources")
    private ProductSourcesDTO[] sources;
    @JsonProperty(value = "Tags")
    private ProductTagsDTO[] tags;
    @JsonProperty(value = "PackMaterials")
    private ProductPackMaterialsDTO[] packMaterials;


}
