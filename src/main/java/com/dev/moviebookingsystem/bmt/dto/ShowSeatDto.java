package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
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
