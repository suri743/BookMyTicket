package com.dev.moviebookingsystem.bmt.dto;

import com.dev.moviebookingsystem.bmt.model.constant.PaymentMode;
import com.dev.moviebookingsystem.bmt.model.constant.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaymentDto {
    int id;
    AdminDataDto adminData;
    LocalDateTime paymentTime;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
    TicketDto ticket;
}
