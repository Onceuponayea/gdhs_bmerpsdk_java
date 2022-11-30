package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDTO productDTO;
}
