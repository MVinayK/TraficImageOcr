package com.ocr.ocr.ImageOcr;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class ImageOcrResource {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/ocr")
    public String doOcr() {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
            String text=tesseract.doOCR(new File("C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\plate_1.jpg"));
            text=text.replaceAll("[^a-zA-Z0-9]", "");
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}
