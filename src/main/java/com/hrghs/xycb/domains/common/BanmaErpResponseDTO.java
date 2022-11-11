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
public class BanmaErpResponseDTO {
    @JsonProperty(value = "Code")
    private int code;
    @JsonProperty(value = "Time")
    private String time;
    @JsonProperty(value = "Success")
    private boolean success;
    @JsonProperty(value = "Message")
    private String message;
    @JsonProperty(value = "Data")
    private Object data;
    @JsonProperty(value = "Tick")
    private long tick;
}
