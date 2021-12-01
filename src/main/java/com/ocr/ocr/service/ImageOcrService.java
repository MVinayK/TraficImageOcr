package com.ocr.ocr.service;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageOcrService {

    private static final String tessData = "C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\tessdata";

    private static final String image = "C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\plate_1.jpg";

    public String doOcr(String imageId) {
        Tesseract tesseract = new Tesseract();
        try {
            String imagePath = getImagePathFromDB(imageId);
            tesseract.setDatapath(tessData);
            String text=tesseract.doOCR(new File(imagePath));
            text=text.replaceAll("[^a-zA-Z0-9]", "");
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getImagePathFromDB(String imageId) {
        //fetch the image path from DB
        //Download the image from S3

        return image;
    }
}
