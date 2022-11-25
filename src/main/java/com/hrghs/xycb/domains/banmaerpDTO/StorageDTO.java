package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrghs.xycb.domains.enums.BanmaerpStorageEnums;
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
    @JsonProperty(value = "FileCategoryID")
    private String fileCategoryID;
    @JsonProperty(value = "FileType")
    private String fileType;
    @JsonProperty(value = "Size")
    private int size;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private String createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private String updateTime;
}
