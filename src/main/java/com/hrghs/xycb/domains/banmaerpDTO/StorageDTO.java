package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("Storage")
@Entity
@Table(name = "bmerp_storage")
public class StorageDTO {

    @Id
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Url")
    private String url;
    @Column(name = "file_category_id")
    @JsonProperty(value = "FileCategoryID")
    private String fileCategoryID;
    @Column(name = "file_type")
    @JsonProperty(value = "FileType")
    private String fileType;
    @JsonProperty(value = "Size")
    private Integer size;
    @Column(name = "create_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @Column(name = "update_time")
    @Convert(converter = JodaDateTimeConverter.class)
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
