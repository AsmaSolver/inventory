package com.solv.inventory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
@Builder
public class txn_branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int branch_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mob_number")
    private String mobile_number;

    @Column(name = "address")
    private String address;

    @Column(name = "branch_type")
    private String branch_type;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "updated_date")
    private LocalDate updated_date;

    @Column(name = "updated_by")
    private String updated_by;
}

