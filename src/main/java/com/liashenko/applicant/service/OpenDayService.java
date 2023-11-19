package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.OpenDayResponseDto;
import com.liashenko.applicant.entity.OpenDay;
import com.liashenko.applicant.repository.OpenDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OpenDayService {
    private final OpenDayRepository openDayRepository;

    @Autowired
    public OpenDayService(OpenDayRepository openDayRepository) {
        this.openDayRepository = openDayRepository;
    }

    public List<OpenDayResponseDto> getAll() {
        Iterable<OpenDay> openDays = this.openDayRepository.findAll();
        Stream<OpenDay> stream = StreamSupport.stream(openDays.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public List<OpenDayResponseDto> getByDateDifferenceFromCurrentDate(int differenceDay) {
        Date currentDate = new Date();

        long daysInMillis = (long) differenceDay * 24 * 60 * 60 * 1000;
        Date nextDay = new Date(currentDate.getTime() + daysInMillis);

        Iterable<OpenDay> openDays = this.openDayRepository.findWithDateDifference(currentDate, nextDay);
        Stream<OpenDay> stream = StreamSupport.stream(openDays.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }


    public OpenDayResponseDto toDto(OpenDay openDay) {
        OpenDayResponseDto openDayResponseDto = new OpenDayResponseDto();

        openDayResponseDto.setDate(openDay.getDate());
        openDayResponseDto.setDescription(openDay.getDescription());

        return openDayResponseDto;
    }
}
