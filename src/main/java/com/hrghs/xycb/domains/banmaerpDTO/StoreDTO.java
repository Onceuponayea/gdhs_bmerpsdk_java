package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class StoreDTO {
    @JsonProperty(value = "ID")
    private String id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Platform")
    private String platform;
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
    @JsonProperty(value = "UpdateTime")
    private DateTime updateTime;
}
