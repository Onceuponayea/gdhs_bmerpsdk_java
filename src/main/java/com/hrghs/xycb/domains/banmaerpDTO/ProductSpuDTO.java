package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "bmerp_product_spu")
public class ProductSpuDTO {

    @Column(name = "spu_id")
    @Id
    @JsonProperty(value = "SPUID")
    private Long SPUID;

    @JsonProperty(value = "Code")
    private String code;

    @JsonProperty(value = "Title")
    private String title;

    @Column(name = "leimu_id")
    @JsonProperty(value = "LeiMuID")
    private String leiMuID;

    @Column(name = "source_url")
    @JsonProperty(value = "SourceUrl")
    private String sourceUrl;

    @JsonProperty(value = "TagIDs")
    private String tagIDs;

    @JsonProperty(value = "Image")
    private String image;

    @JsonProperty(value = "Status")
    private String status;

    @JsonProperty(value = "Source")
    private String source;

    @Column(name = "Default_Supplier_ID")
    @JsonProperty(value = "DefaultSupplierID")
    private String defaultSupplierID;

    @JsonProperty(value = "Remark")
    private String remark;

    @Column(name = "Is_Exempt_Quality")
    @JsonProperty(value = "IsExemptQuality")
    private boolean isExemptQuality;

    @JsonProperty(value = "Keywords")
    private String keywords;

//    @JsonSerialize(contentUsing = JodaDateTimeSerialiser.class)
//    @JsonDeserialize(contentUsing = JodaDateTimeDeserialiser.class)
    @Convert(converter = JodaDateTimeConverter.class)
    @Column(name = "create_time")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;

//    @JsonSerialize(contentUsing = JodaDateTimeSerialiser.class)
//    @JsonDeserialize(contentUsing = JodaDateTimeDeserialiser.class)
    @Convert(converter = JodaDateTimeConverter.class)
    @Column(name = "update_time")
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
