package com.dev.moviebookingsystem.mbs.dto;

import com.dev.moviebookingsystem.mbs.model.constant.PaymentMode;
import com.dev.moviebookingsystem.mbs.model.constant.PaymentStatus;
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
