package com.solv.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
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
    private Timestamp created_date;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "updated_date")
    private Timestamp updated_date;

    @Column(name = "updated_by")
    private String updated_by;
}

