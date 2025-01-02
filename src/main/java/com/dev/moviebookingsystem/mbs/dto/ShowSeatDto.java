package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.constant.SeatStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShowSeatDto {
    int id;
    AdminDataDto adminData;
    double price;
    ShowDto show;
    SeatDto seat;
    SeatStatus seatStatus;
    TicketDto ticket;
}
