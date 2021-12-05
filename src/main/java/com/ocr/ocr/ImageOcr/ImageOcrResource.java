package com.ocr.ocr.ImageOcr;

import com.ocr.ocr.service.ImageOcrService;
import com.ocr.ocr.service.ImageService;
import com.ocr.ocr.service.StorageService;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.UUID;

@RestController
public class ImageOcrResource {

    @Autowired
    private ImageOcrService imageOcrService;

    @Autowired
    private StorageService storageService;

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

    @PostMapping("/ocr/image")
    public UUID doOcr(@RequestParam("file") MultipartFile file) {
        UUID imageID = UUID.randomUUID();
        try {
            File image = new File(System.getProperty("java.io.tmpdir")+"/"+file.getName());
            file.transferTo(image);
            storageService.store(image, imageID);
            return imageID;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
