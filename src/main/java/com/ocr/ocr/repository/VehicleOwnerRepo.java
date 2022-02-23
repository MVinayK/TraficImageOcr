package com.ocr.ocr.repository;

import com.ocr.ocr.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleOwnerRepo extends JpaRepository<VehicleOwner, String> {

    @Query(value = "select * from vehicleowner where platenumber = ?1", nativeQuery = true)
    VehicleOwner getOwnerDetailsByPlateNumber(String plateNumber);

    @Query(value = "select * from vehicleowner where phone = ?1", nativeQuery = true)
    VehicleOwner getOwnerDetailsByPhoneNumber(String phoneNumber);

}
