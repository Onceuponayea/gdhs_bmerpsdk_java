package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.hrghs.xycb.domains.BanmaerpProperties;
import lombok.Data;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_product_suppliers")
public class ProductSuppliersDTO {
    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "Sort")
    private Integer sort;

    @JsonIgnore
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductDTO productDTO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true)
    @JoinColumn(name = "supinfo_id", referencedColumnName = "id")
    @JsonProperty(value = "Info")
    @JsonManagedReference
    private ProductSuppliersInfoDTO info;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;
}
