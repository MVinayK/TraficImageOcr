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
@Table(name = "images")
public class Image {
    @Id
    @Column(name = "imageid")
    private String imageId;

    @Column(name = "imagepath")
    private String imagePath;

    @Column(name = "platenumber")
    private String plateNumber;

    @Column(name = "createddate")
    private LocalDateTime createdDate;
}
