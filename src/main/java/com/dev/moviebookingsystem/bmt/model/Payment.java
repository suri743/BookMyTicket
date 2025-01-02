package com.dev.moviebookingsystem.bmt.model;

import com.dev.moviebookingsystem.bmt.model.constant.PaymentMode;
import com.dev.moviebookingsystem.bmt.model.constant.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class Payment extends BaseEntity{
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne
    private Ticket ticket;
}
