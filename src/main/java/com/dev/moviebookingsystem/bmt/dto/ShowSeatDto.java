package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShowSeatDto {
    Integer id;
    AdminDataDto adminData;
    double price;
    ShowDto show;
    SeatDto seat;
    String seatNumber;
    SeatStatus seatStatus;

    @JsonBackReference
    TicketDto ticket;
}
