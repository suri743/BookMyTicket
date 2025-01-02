package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.PhysicalSeatStatus;
import com.dev.moviebookingsystem.bmt.model.constant.SeatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SeatDto {
    Integer id;
    AdminDataDto adminData;
    Integer row;
    Integer column;
    String seatNumber;
    SeatType seatType;
    PhysicalSeatStatus seatStatus;
    Integer auditoriumId;
    AuditoriumDto auditorium;
}
