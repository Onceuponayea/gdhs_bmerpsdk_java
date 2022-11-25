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
@Table(name = "bmerp_product_options")
public class ProductOptionsDTO {

    @Id
    private int id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Sort")
    private int sort;
    @JsonProperty(value = "Values")
    private String[] values;

}
