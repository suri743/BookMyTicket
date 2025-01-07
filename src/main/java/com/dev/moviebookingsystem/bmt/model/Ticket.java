package com.dev.moviebookingsystem.bmt.model;

import com.dev.moviebookingsystem.bmt.model.constant.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Ticket extends BaseEntity{

    private String ticketNumber;

    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<ShowSeat> seats;

    @ManyToOne(optional = false)
    private Show show;

    private LocalDateTime bookingTime;

    private double totalAmount;

    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
