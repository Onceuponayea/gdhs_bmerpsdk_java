package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Data
@Entity
@Table(name = "bmerp_product_packmaterials")
public class ProductPackMaterialsDTO {

    @Id
    @JsonProperty(value = "ID")
    private long ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Quantity")
    private int quantity;
}
