package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppsInfoDTO {
    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "Secret")
    private String secret;
    @JsonProperty(value = "Scopes")
    private String[] scopes;
    @JsonProperty(value = "AuthType")
    private String authType;
}
