package com.solv.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "txn_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String name;
    @Column(name="user_email")
    private String email;
    @Column(name = "user_mobile_number")
    private String mobNum;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "created_date")
    private java.sql.Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private java.sql.Timestamp updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

}
