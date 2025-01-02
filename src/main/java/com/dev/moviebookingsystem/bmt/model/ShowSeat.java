package com.dev.moviebookingsystem.bmt.model;

import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity(name = "showSeats")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ShowSeat extends BaseEntity{
    private double price;

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
