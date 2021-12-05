package com.ocr.ocr.service;

import com.ocr.ocr.model.Image;
import com.ocr.ocr.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public void insertImageDate(Image image) {
        imageRepo.save(image);
    }
}
