package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderRemarksDTO {
    @JsonProperty(value = "Category")
    private String category;
    @JsonProperty(value = "Content")
    private String content;
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
}
