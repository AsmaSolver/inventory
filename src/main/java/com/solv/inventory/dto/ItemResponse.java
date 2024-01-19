package com.solv.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ItemResponse {

    @JsonProperty("data")
    Object data;

    @JsonProperty("status-code")
    private int statusCode;

    @JsonProperty("status-message")
    private String statusMessage;
}
