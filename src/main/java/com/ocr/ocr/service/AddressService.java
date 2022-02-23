package com.ocr.ocr.service;

import com.ocr.ocr.model.Address;
import com.ocr.ocr.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Address getAddressByPhone(String phone) {
        try {
            return addressRepo.getById(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
