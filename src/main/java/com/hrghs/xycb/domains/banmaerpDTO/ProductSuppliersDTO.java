package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
//@JsonTypeName("Suppliers")
@Entity
@Table(name = "bmerp_product_suppliers")
public class ProductSuppliersDTO {
    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Sort")
    private int sort;

    @OneToOne(cascade= CascadeType.ALL)
    @JsonProperty(value = "Info")
    private ProductSuppliersInfoDTO info;
}
