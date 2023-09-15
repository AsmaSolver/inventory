package com.solv.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Response {

    @JsonProperty("status-code")
    private String statusCode;
    @JsonProperty("status-message")
    private String statusMessage;

}
