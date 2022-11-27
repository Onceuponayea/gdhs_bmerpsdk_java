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
@Table(name = "bmerp_product_sources")
public class ProductSourcesDTO {
    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "product_SourceId")
    @Type(type = "uuid-char")
    private UUID productSourceId;

    @JsonProperty(value = "Url")
    private String url;

    @JsonProperty(value = "Remark")
    private String remark;

    @Column(name = "is_default")
    @JsonProperty(value = "IsDefault")
    private boolean isDefault;

    @JsonIgnore
    @JoinColumn(name = "product_SourceId",nullable = false,insertable = false,updatable = false)
    @ManyToOne
    private ProductDTO productDTO;
}
