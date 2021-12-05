package com.ocr.ocr.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ocr.ocr.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${aws.bucket.name}")
    private String BUCKET_NAME;

    @Value("${aws.accsesskey}")
    private String ACCESS_KEY;

    @Value("${aws.secretkey}")
    private String SECRET_KEY;

    @Value("${aws.bucket.folder.name}")
    private String FOLDER_NAME;

    @Autowired
    private ImageOcrService imageOcrService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AmazonS3 s3client;

    public void store(File file, UUID imageID) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.printf("File name : " + file.getName());
        try {
            String imgPath = date+"/" + imageID;
            //check why plate number is null
            String plateNumber = imageOcrService.doOcr(file);
            imageService.insertImageDate(getImageEntity(imageID, imgPath, plateNumber));
            s3client.putObject(new PutObjectRequest(BUCKET_NAME, imgPath, file));
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException(e.getErrorMessage());
        }

    }

    private Image getImageEntity(UUID imageID, String imgPath, String plateNumber) {
        return Image.builder()
                .imageId(imageID.toString())
                .imagePath(imgPath)
                .plateNumber(plateNumber)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
