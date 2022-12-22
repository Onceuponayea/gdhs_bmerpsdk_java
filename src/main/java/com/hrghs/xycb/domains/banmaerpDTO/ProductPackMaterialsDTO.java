package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.domains.BanmaerpProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_product_packmaterials")
public class ProductPackMaterialsDTO {

    @Id
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "ID")
    private Long ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Quantity")
    private Integer quantity;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(cascade = CascadeType.REFRESH ,fetch = FetchType.EAGER)
    private ProductDTO productDTO;
}
