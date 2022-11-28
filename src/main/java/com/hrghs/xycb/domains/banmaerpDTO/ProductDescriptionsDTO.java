package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_product_descriptions")
public class ProductDescriptionsDTO {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "pd_id")
    private UUID productDescriptionId;

    @JsonProperty(value = "Html")
    private String html;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "Short")
    private String Short;

}
