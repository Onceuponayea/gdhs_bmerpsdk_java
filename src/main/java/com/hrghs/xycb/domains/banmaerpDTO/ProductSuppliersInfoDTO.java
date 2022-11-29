package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bmerp_product_suppliers_info")
public class ProductSuppliersInfoDTO {

    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Contact")
    private String contact;
    @JsonProperty(value = "Phone")
    private String phone;
    @JsonProperty(value = "Address")
    private String address;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "Url")
    private String url;
    @JsonProperty(value = "QQ")
    private String qq;
    @Column(name = "we_chat")
    @JsonProperty(value = "WeChat")
    private String weChat;
    @Column(name = "wang_wang")
    @JsonProperty(value = "WangWang")
    private String wangWang;
    @Column(name = "settlement_type")
    @JsonProperty(value = "SettlementType")
    private String settlementType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @JsonBackReference
    private ProductSuppliersDTO productSuppliersDTO;
}