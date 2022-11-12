package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hrghs.xycb.domains.enums.BanmaerpStorageEnums;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class StorageDTO {
    @JsonProperty(value = "ID")
    private String id;
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
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
