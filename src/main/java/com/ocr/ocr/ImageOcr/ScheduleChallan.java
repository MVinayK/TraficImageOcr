package com.ocr.ocr.ImageOcr;

import com.ocr.ocr.model.Address;
import com.ocr.ocr.model.Schedule;
import com.ocr.ocr.service.AddressService;
import com.ocr.ocr.service.AmountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class ScheduleChallan {

    @Autowired
    private AmountCalculateService scheduleService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/schedule/{imageId}")
    public String scheduleChallan(@PathVariable String imageId) {
        try {
            scheduleService.scheduleChallan(imageId);
        } catch (Exception e) {
            log.error("Caught error in Scheduling challan", e);
            throw e;
        }
        return "Done";
    }

    @GetMapping("/schedule/pending")
    public List<Schedule> getPendingSchedules() {
        try {
            return scheduleService.getAllPendingSchedules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @GetMapping("/schedule/pending/address/{phone}")
    public Address getPendingScheduleAddress(@PathVariable String phone) {
        try {
            return addressService.getAddressByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Address();
    }

    @GetMapping("/schedule/all")
    public List<Schedule> getAllSchedules() {
        try {
            return scheduleService.getAllSchedules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
