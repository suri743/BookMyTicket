package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.TicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDto {
    Integer id;
    AdminDataDto adminData;
    String ticketNumber;
    List<ShowSeatDto> seats;
    ShowDto show;
    LocalDateTime bookingTime;
    double totalAmount;

    @JsonManagedReference
    UserDto user;
    TicketStatus ticketStatus;
}
