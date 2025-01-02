package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.Movie;
import com.dev.moviebookingsystem.mbs.model.constant.ShowStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShowDto {
    int id;
    AdminDataDto adminData;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate showDate;
    String language;
    AuditoriumDto auditorium;
    Movie movie;
    ShowStatus showStatus;
    List<ShowSeatDto> seats;
}
