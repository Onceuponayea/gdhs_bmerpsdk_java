package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data

public class ProductSkusOptionsDTO {
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Value")
    private String value;
}
