package com.ocr.ocr.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedulechallan")
public class Schedule {

    @Id
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "retry")
    private int retry;

    @Column(name = "amount")
    private String amount;

    @Column(name = "createddate")
    private Date createDate;

    @Column(name = "lastnotify")
    private Date lastNotify;

    @Column(name = "status")
    private String status;
}
