package com.ocr.ocr.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.ocr.ocr.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
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

    public void store(File file, UUID imageID, String reason, String description) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.printf("File name : " + file.getName());
        try {
            String imgPath = date+"/" + imageID;
            String plateNumber = imageOcrService.doOcr(file);
            imageService.insertImageDate(getImageEntity(imageID, imgPath, plateNumber, reason, description));
            s3client.putObject(new PutObjectRequest(BUCKET_NAME, imgPath, file));
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException(e.getErrorMessage());
        }

    }

    private Image getImageEntity(UUID imageID, String imgPath, String plateNumber, String reason, String desc) {
        return Image.builder()
                .imageId(imageID.toString())
                .imagePath(imgPath)
                .plateNumber(plateNumber)
                .reason(reason)
                .description(desc)
                .processed("ocr")
                .createdDate(LocalDateTime.now())
                .build();
    }

    public ByteArrayOutputStream getImageByPath(String path) {
        try {
            S3Object s3object = s3client.getObject(new GetObjectRequest(BUCKET_NAME, path));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }

            return baos;
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
