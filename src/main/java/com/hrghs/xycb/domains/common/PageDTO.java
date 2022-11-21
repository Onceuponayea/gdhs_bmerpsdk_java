package com.hrghs.xycb.domains.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component

@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,visible = true,use = JsonTypeInfo.Id.NAME)
@JsonTypeName("Page")
public class PageDTO {
    @JsonProperty(value = "PageNumber")
    int pageNumber;
    @JsonProperty(value = "PageCount")
    int pageCount;
    @JsonProperty(value = "PageSize")
    int pageSize;
    @JsonProperty(value = "TotalCount")
    int totalCount;
    @JsonProperty(value = "HasMore")
    boolean hasMore;
}
