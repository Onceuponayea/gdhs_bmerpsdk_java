package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppsInfoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "ID")
    private String ID;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "Name")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "Status")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "Secret")
    private String secret;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "Scopes")
    private String[] scopes;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "AuthType")
    private String authType;

    @JsonProperty(value = "CreateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateTime createTime;

    @JsonProperty(value = "UpdateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DateTime updateTime;
}
