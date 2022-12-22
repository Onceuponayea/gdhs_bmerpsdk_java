package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.utils.converters.JodaDateTimeConverter;
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
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Category")
@Entity
@Table(name = "bmerp_category")
public class CategoryDTO {

    @Id
    @JsonProperty(value = "ID")
    @Column(name = "id")
    private String ID;
    @JsonProperty(value = "ParentID")
    @Column(name = "parent_id")
    private String parentID;
    @JsonProperty(value = "Name")
    @Column
    private String name;
    @JsonProperty(value = "Description")
    @Column
    private String description;
    @JsonProperty(value = "Code")
    @Column
    private String code;
    /**
     * @@apiNote DESC 倒序; ASC 正序;
     */
    @JsonProperty(value = "Sort")
    @Column
    private Integer sort;
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    @Column(name = "create_time")
    private DateTime createTime;
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    @Column(name = "update_time")
    private DateTime updateTime;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "banma_master_app_id")
    private BanmaerpProperties banmaerpProperties;
}
