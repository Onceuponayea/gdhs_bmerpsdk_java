package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
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
    @Convert(converter = JpaUUIDConverter.class)
    @Id
    @Column(name = "product_SourceId")
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID productSourceId;

    @JsonProperty(value = "Url")
    private String url;

    @JsonProperty(value = "Remark")
    private String remark;

    @Column(name = "is_default")
    @JsonProperty(value = "IsDefault")
    private Boolean isDefault;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductDTO productDTO;
}
