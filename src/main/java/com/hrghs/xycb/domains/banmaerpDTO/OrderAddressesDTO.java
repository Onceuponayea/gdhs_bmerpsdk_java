package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.utils.converters.JpaUUIDConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
@Table(name = "bmerp_order_address")
public class OrderAddressesDTO {

    @Id
    @Convert(converter = JpaUUIDConverter.class)
    @JsonIgnore
    @Column(name = "order_address_id")
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID order_address_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderDTO orderDTO;

    @JsonProperty(value = "Name")
    private String name;

    @Column(name = "name_kana")
    @JsonProperty(value = "NameKana")
    private String nameKana;

    @JsonProperty(value = "Country")
    private String country;

    @Column(name = "country_code")
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

    @Column(name = "postal_code")
    @JsonProperty(value = "PostalCode")
    private String postalCode;

    @Column(name = "addr_type")
    @JsonProperty(value = "AddrType")
    private String addrType;

    @JsonProperty(value = "Address1")
    private String address1;
    @JsonProperty(value = "Address2")
    private String address2;
    @JsonProperty(value = "Address3")
    private String address3;

    @Column(name = "is_original")
    @JsonProperty(value = "IsOriginal")
    private boolean isOriginal;

    @Column(name = "tax_no")
    @JsonProperty(value = "TaxNo")
    private String taxNo;

}
