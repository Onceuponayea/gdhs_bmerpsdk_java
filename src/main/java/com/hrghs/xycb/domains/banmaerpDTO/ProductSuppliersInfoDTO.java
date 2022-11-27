package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @JsonProperty(value = "WeChat")
    private String weChat;
    @JsonProperty(value = "WangWang")
    private String wangWang;
    @JsonProperty(value = "SettlementType")
    private String settlementType;
}