package com.ocr.ocr.service;

import com.ocr.ocr.model.Schedule;
import com.ocr.ocr.model.VehicleOwner;
import com.ocr.ocr.repository.ScheduleChallanRepo;
import lombok.Builder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
public class NotificationScheduler {

    @Autowired
    private ScheduleChallanRepo scheduleChallanRepo;

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    public void sendNotifications() {

        List<Schedule> schedules = scheduleChallanRepo.getAllSchedulesWithStatusPending();

        if (CollectionUtils.isEmpty(schedules)) {
            System.out.println("Empty non pending schedule");
            return;
        }

        schedules.parallelStream()
                 .forEach(schedule -> {
                     System.out.println("Send Notifications !!!");
                     VehicleOwner owner = vehicleOwnerService.getOwnerDetailsByPhoneNumber(schedule.getPhone());
                     notify(owner, schedule);
                 });

    }

    private void notify(VehicleOwner owner, Schedule schedule) {
        System.out.println(String.format("Sending email to %s owner of %s", schedule.getEmail(), owner.getNumberPlate()));
        System.out.println(String.format("Sending sms to %s owner of %s", schedule.getPhone(), owner.getNumberPlate()));
    }
}
