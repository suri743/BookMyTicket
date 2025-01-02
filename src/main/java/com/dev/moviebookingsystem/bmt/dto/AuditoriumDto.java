package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.AuditoriumFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuditoriumDto {
    int id;
    String name;
    AdminDataDto adminData;
    int capacity;
    List<SeatDto> seats;
    List<ShowDto> shows;
    List<AuditoriumFeature> auditoriumFeatures;
    TheaterDto theater;
}
