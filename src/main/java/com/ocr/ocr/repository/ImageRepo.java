package com.ocr.ocr.repository;

import com.ocr.ocr.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, String> {

}
