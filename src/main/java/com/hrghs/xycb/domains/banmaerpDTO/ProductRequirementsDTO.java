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
@Table(name = "bmerp_product_requirements")
public class ProductRequirementsDTO {
    @JsonIgnore
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "product_RequirementId")
    private UUID productRequirementId;

    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Contact")
    private String contact;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDTO productDTO;
}
