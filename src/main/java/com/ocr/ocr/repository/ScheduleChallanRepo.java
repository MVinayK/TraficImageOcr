package com.ocr.ocr.repository;


import com.ocr.ocr.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleChallanRepo extends JpaRepository<Schedule, String> {

    @Query(value = "select * from schedulechallan where status = 'pending'", nativeQuery = true)
    List<Schedule> getAllSchedulesWithStatusPending();

}
