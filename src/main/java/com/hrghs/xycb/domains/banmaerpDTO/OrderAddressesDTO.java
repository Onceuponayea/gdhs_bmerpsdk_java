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
@Table(name = "bmerp_order_address")
public class OrderAddressesDTO {

    @Id
    private int id;

    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "NameKana")
    private String nameKana;
    @JsonProperty(value = "Country")
    private String country;
    @JsonProperty(value = "CountryCode")
    private String countryCode;
    @JsonProperty(value = "Province")
    private String province;
    @JsonProperty(value = "City")
    private String city;
    @JsonProperty(value = "County")
    private String county;
    @JsonProperty(value = "Phone")
    private String phone;
    @JsonProperty(value = "Email")
    private String email;
    @JsonProperty(value = "PostalCode")
    private String postalCode;
    @JsonProperty(value = "AddrType")
    private String addrType;
    @JsonProperty(value = "Address1")
    private String address1;
    @JsonProperty(value = "Address2")
    private String address2;
    @JsonProperty(value = "Address3")
    private String address3;
    @JsonProperty(value = "IsOriginal")
    private boolean isOriginal;
    @JsonProperty(value = "TaxNo")
    private String taxNo;

}
