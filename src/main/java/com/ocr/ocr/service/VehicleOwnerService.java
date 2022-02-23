package com.ocr.ocr.service;

import com.ocr.ocr.model.VehicleOwner;
import com.ocr.ocr.repository.VehicleOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleOwnerService {

    @Autowired
    private VehicleOwnerRepo vehicleOwnerRepo;

    public VehicleOwner getOwnerDetailsByPlateNumber(String plateNumber) {
        VehicleOwner owner = vehicleOwnerRepo.getOwnerDetailsByPlateNumber(plateNumber);
        return owner;
    }

    public VehicleOwner getOwnerDetailsByPhoneNumber(String phoneNumber) {
        VehicleOwner owner = vehicleOwnerRepo.getOwnerDetailsByPhoneNumber(phoneNumber);
        return owner;
    }
}
