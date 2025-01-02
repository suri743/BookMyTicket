package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.TicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDto {
    int id;
    AdminDataDto adminData;
    String ticketNumber;
    List<ShowSeatDto> seats;
    ShowDto show;
    String bookingTime;
    double totalAmount;
    UserDto user;
    TicketStatus ticketStatus;
}
