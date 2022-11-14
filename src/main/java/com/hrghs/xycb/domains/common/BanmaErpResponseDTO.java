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
public class BanmaErpResponseDTO<T> {
    @JsonProperty(value = "Code")
    private Integer code;
    @JsonProperty(value = "Time")
    private String time;
    @JsonProperty(value = "Success")
    private Boolean success;
    @JsonProperty(value = "Message")
    private String message;
    @JsonProperty(value = "Data")
    private T data;
    @JsonProperty(value = "Tick")
    private Long tick;
}
