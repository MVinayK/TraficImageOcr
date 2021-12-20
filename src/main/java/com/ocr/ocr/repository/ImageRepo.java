package com.ocr.ocr.repository;

import com.ocr.ocr.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, String> {

    @Query(value = "select * from images where processed = 'ocr'", nativeQuery = true)
    List<Image> findAllImagesWithProcessedOcr();
}
