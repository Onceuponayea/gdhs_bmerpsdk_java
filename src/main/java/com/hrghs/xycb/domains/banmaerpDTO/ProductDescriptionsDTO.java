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
@Table(name = "bmerp_product_descriptions")
public class ProductDescriptionsDTO {

        @Id
        private int id;

        @JsonProperty(value = "Html")
        private String html;
        @JsonProperty(value = "Text")
        private String text;
        @JsonProperty(value = "Short")
        private String Short;
}
