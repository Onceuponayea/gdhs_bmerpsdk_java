package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDTO {
    @JsonProperty(value = "Spu")
    private ProductSpuDTO spu;
    @JsonProperty(value = "Descriptions")
    private ProductDescriptionsDTO descriptions;
    @JsonProperty(value = "Skus")
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
