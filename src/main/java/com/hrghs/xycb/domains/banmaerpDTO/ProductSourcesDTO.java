package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data

public class ProductSourcesDTO {
    @JsonProperty(value = "Url")
    private String url;
    @JsonProperty(value = "Remark")
    private String remark;
    @JsonProperty(value = "IsDefault")
    private boolean isDefault;
}
