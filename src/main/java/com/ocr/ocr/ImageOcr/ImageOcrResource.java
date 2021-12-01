package com.ocr.ocr.ImageOcr;

import com.ocr.ocr.service.ImageOcrService;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class ImageOcrResource {

    @Autowired
    private ImageOcrService imageOcrService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/ocr/{imageId}")
    public String doOcr(@PathVariable String imageId) {
        try {
            return imageOcrService.doOcr(imageId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
