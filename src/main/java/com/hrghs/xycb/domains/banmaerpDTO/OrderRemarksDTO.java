package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "CreateTime")
    private DateTime createTime;
}
