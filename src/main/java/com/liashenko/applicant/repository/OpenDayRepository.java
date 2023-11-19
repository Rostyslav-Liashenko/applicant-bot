package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.OpenDay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OpenDayRepository extends CrudRepository<OpenDay, UUID> {
    @Query("SELECT od FROM OpenDay od WHERE od.date BETWEEN :currentDate AND :nextDate")
    List<OpenDay> findWithDateDifference(
            @Param("currentDate") Date currentDate,
            @Param("nextDate") Date nextDate
    );
}
