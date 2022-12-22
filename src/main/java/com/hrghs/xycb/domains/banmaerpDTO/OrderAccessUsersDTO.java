package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonTypeName(value = "AccessUsers")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_ARRAY,use = JsonTypeInfo.Id.NAME)
public class OrderAccessUsersDTO {
    @JsonProperty(value = "UserID")
    Integer userID;
    @JsonProperty(value = "DataAccessMode")
    String dataAccessMode;
}
