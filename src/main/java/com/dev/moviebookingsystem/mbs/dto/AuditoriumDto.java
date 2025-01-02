package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.constant.AuditoriumFeature;
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
