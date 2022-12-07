package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaListStringConverter;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Component
@Data
@Entity
@Table(name = "bmerp_product_options")
public class ProductOptionsDTO {
    @JsonIgnore
    @Convert(converter = JpaUUIDConverter.class)
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "product_optionId")
    private UUID productOptionsId;

    @Column(name = "option_name")
    @JsonProperty(value = "Name")
    private String name;

    @Column(name = "option_sort")
    @JsonProperty(value = "Sort")
    private Integer sort;

    @Column(name = "option_values")
    @Convert(converter = JpaListStringConverter.class)
    @JsonProperty(value = "Values")
    private List<String> values;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductDTO productDTO;

}
