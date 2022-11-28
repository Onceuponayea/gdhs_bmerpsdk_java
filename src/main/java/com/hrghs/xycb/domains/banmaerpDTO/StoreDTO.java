package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrghs.xycb.utils.JpaJodaDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
<<<<<<<<< Temporary merge branch 1
=========
import javax.persistence.*;
>>>>>>>>> Temporary merge branch 2

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
//@JsonTypeInfo(include = JsonTypeInfo.As.EXISTING_PROPERTY, use = JsonTypeInfo.Id.NAME, visible = true, property = "Stores")
@JsonTypeName("Store")
public class StoreDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Platform")
    private String platform;
<<<<<<<<< Temporary merge branch 1
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
=========
    @Convert(converter = JpaJodaDateTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "CreateTime")
    @Column(name = "CreateTime",columnDefinition = "TIMESTAMP COMMENT 'Store_CreateTime' ")
    private DateTime createTime;
    @Convert(converter = JpaJodaDateTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "UpdateTime")
    @Column(name = "UpdateTime",columnDefinition = "TIMESTAMP COMMENT 'Store_UpdateTime' ")
>>>>>>>>> Temporary merge branch 2
    private DateTime updateTime;
}
