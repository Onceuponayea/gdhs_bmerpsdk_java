package com.hrghs.xycb.domains.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
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
