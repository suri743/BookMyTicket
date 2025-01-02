package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.constant.TicketStatus;
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
