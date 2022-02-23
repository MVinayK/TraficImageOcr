package com.ocr.ocr.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "challan")
public class Challan {

    @Id
    @Column(name = "challanid")
    private String challanId;

    @Column(name = "imageid")
    private String imageId;

    @Column(name = "amount")
    private String amount;

    @Column(name = "fine")
    private String fine;

    @Column(name = "notificationretry")
    private String notificationRetry;
}
