package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class CategoryDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "ParentID")
    private String parentID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "Code")
    private String code;
    @JsonProperty(value = "Sort")
    /**
     * @@apiNote DESC 倒序; ASC 正序;
     */
    private int sort;
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;

}
