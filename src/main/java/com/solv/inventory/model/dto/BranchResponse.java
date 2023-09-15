package com.solv.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BranchResponse {
    @JsonProperty("data")
    Object data;

    @JsonProperty("status-code")
    private String statusCode;

    @JsonProperty("status-message")
    private String statusMessage;

}
