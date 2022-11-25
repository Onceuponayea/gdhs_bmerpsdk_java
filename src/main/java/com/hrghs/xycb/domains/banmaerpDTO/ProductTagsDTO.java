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
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
//@JsonTypeName("Tags")
@Entity
@Table(name = "bmerp_product_tags")
public class ProductTagsDTO {
    @Id
    private int id;
    @JsonProperty(value = "Name")
    private String name;
}
