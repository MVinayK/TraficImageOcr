package com.ocr.ocr.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrafficConfig {

    @Value("${aws.secretkey}")
    private String SECRET_KEY;

    @Value("${aws.accsesskey}")
    private String ACCESS_KEY;

    @Bean
    public AmazonS3 getS3Client() {
        AmazonS3 s3client = null;
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1).build();
        return s3client;
    }

}
