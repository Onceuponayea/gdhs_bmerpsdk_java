package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductOptionsDTO {

    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Sort")
    private int sort;
    @JsonProperty(value = "Values")
    private String[] values;

}
