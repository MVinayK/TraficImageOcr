package com.ocr.ocr.service;

import com.ocr.ocr.model.Image;
import com.ocr.ocr.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public void insertImageDate(Image image) {
        imageRepo.save(image);
    }

    public List<Image> getAllOcredImages() {
        return imageRepo.findAllImagesWithProcessedOcr();
    }

    public void updatePlateNumber(String imageId, String number) {
        Image image = imageRepo.findById(imageId).get();
        image.setPlateNumber(number);
        imageRepo.save(image);
    }
}
