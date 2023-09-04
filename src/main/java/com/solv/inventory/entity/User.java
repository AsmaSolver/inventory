package com.solv.inventory.entity;

import javax.persistence.*;

@Entity
@Table(name = "txn_user")
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
