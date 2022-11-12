package com.hrghs.xycb.domains.banmaerpDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductImagesDTO {
    @JsonProperty(value = "Src")
    private String src;
    @JsonProperty(value = "Sort")
    private int Sort;
}
