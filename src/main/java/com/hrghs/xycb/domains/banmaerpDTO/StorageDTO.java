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

@Component
@Data

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Storage")
public class StorageDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Url")
    private String url;
    @JsonProperty(value = "FileCategoryID")
    private String fileCategoryId;
    @JsonProperty(value = "FileType")
    private String fileType;
    @JsonProperty(value = "Size")
    private int size;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
