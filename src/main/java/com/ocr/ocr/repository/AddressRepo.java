package com.ocr.ocr.repository;

import com.ocr.ocr.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, String> {
}
