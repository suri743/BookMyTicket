package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.constant.PhysicalSeatStatus;
import com.dev.moviebookingsystem.mbs.model.constant.SeatType;
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
