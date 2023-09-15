package com.solv.inventory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private int branch_id;
    private String name;
    private String email;
    private String mobile_number;
    private String address;
    private String branch_type;
    private LocalDate created_date;
    private String created_by;
    private LocalDate updated_date;
    private String updated_by;
}
