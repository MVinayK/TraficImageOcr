package com.ocr.ocr.service;

import com.ocr.ocr.model.Address;
import com.ocr.ocr.model.Image;
import com.ocr.ocr.model.Schedule;
import com.ocr.ocr.model.VehicleOwner;
import com.ocr.ocr.repository.ChallanRepo;
import com.ocr.ocr.repository.ImageRepo;
import com.ocr.ocr.repository.ScheduleChallanRepo;
import com.ocr.ocr.util.AmountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AmountCalculateService {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ChallanRepo challanRepo;

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    @Autowired
    private ScheduleChallanRepo scheduleChallanRepo;

    public void scheduleChallan(String imageId) {
        Image image = imageRepo.getById(imageId);
        String plateNo = image.getPlateNumber();
        VehicleOwner owner = vehicleOwnerService.getOwnerDetailsByPlateNumber(plateNo);
        int amount = AmountCalculator.getAmountByReason(image.getReason());
        Schedule schedule = getSchedule(owner, amount);
        scheduleChallanRepo.save(schedule);
    }

    public List<Schedule> getAllPendingSchedules() {
        return scheduleChallanRepo.getAllSchedulesWithStatusPending();
    }

    private Schedule getSchedule(VehicleOwner owner, int amount) {
        Schedule schedule = new Schedule();
        schedule.setAmount(String.valueOf(amount));
        schedule.setEmail(owner.getEmailId());
        schedule.setPhone(owner.getPhoneNumber());
        schedule.setRetry(0);
        schedule.setStatus("pending");
        schedule.setCreateDate(new Date(System.currentTimeMillis()));
        schedule.setLastNotify(new Date(System.currentTimeMillis()));
        return schedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleChallanRepo.findAll();
    }
}
