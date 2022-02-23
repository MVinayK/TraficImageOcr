package com.ocr.ocr.ImageOcr;

import com.ocr.ocr.model.Image;
import com.ocr.ocr.service.AmountCalculateService;
import com.ocr.ocr.service.ImageOcrService;
import com.ocr.ocr.service.ImageService;
import com.ocr.ocr.service.StorageService;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageOcrResource {

    @Autowired
    private ImageOcrService imageOcrService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AmountCalculateService scheduleService;

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
    public UUID doOcr(@RequestParam("file") MultipartFile file, @RequestParam("reason") String reason,
                       @RequestParam("description") String description) {
        UUID imageID = UUID.randomUUID();
        try {
            File image = new File(System.getProperty("java.io.tmpdir")+"/"+file.getName());
            file.transferTo(image);
            storageService.store(image, imageID, reason, description);
            return imageID;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/all/images/ocred")
    public List<Image> getAllOcredImages() {
        List<Image> images = imageService.getAllOcredImages();
        System.out.println(images);
        return images;
    }

    @GetMapping("/image/aws/")
    public ResponseEntity<byte[]> getImage(@RequestParam(name = "key") String key) {
        ByteArrayOutputStream byteArrayOutputStream = storageService.getImageByPath(key);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + key + "\"")
                .body(byteArrayOutputStream.toByteArray());
    }

    @PostMapping("/numberplate/{imageId}/{number}")
    public void updateNumberplate(@PathVariable String imageId, @PathVariable String number) {
        System.out.println(imageId);
        System.out.println(number);
        imageService.updatePlateNumber(imageId, number);
        scheduleService.scheduleChallan(imageId);
    }

}
