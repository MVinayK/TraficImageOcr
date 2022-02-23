package com.ocr.ocr.repository;

import com.ocr.ocr.model.Challan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallanRepo extends JpaRepository<Challan, String> {
}
