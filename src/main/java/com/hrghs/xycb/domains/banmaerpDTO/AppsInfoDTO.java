package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.*;
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
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime createTime;

    @JsonProperty(value = "UpdateTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime updateTime;
}
