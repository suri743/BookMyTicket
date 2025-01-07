package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.exceptions.TicketNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.TicketMapper;
import com.dev.moviebookingsystem.bmt.model.Ticket;
import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.dev.moviebookingsystem.bmt.model.constant.TicketStatus;
import com.dev.moviebookingsystem.bmt.repository.TicketRepository;
import com.dev.moviebookingsystem.bmt.util.TicketHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepo;
    private final TicketMapper ticketMapper;
    private final TicketHelper ticketHelper;
    private final ShowSeatService showSeatService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketDto createTicket(TicketDto ticketDto) {

        List<ShowSeatDto> showSeats = ticketHelper.validateTicket(ticketDto);

        double totalAmount = showSeats.stream().mapToDouble(ShowSeatDto::getPrice).sum();
        //LOCK THE SEATS
        for(ShowSeatDto showSeat : showSeats){
            showSeat = showSeat.toBuilder().seatStatus(SeatStatus.LOCKED).build();
            showSeatService.updateShowSeat(showSeat);
        }

        ticketDto = ticketDto.toBuilder()
            .ticketNumber("TICKET" + ticketDto.getShow().getId() + ticketDto.getUser().getId())
            .bookingTime(LocalDateTime.now())
            .totalAmount(totalAmount)
            .show(showSeats.get(0).getShow())
            .seats(showSeats)
            .ticketStatus(TicketStatus.BOOKED)
            .build();

        Ticket ticket = ticketRepo.save(ticketMapper.mapDtoToEntity(ticketDto));

        for (ShowSeatDto showSeat : showSeats) {
            showSeat = showSeat.toBuilder().seatStatus(SeatStatus.BOOKED).build();
            showSeatService.updateShowSeat(showSeat);
        }

        return ticketMapper.mapEntityToDto(ticketRepo.findById(ticket.getId()).orElseThrow());

    }

    public TicketDto getTicketById(int ticketId) {
        return ticketMapper.mapEntityToDto(ticketRepo.findById(ticketId)
                                                 .orElseThrow(() -> new TicketNotFoundException("Ticket not found for id: " + ticketId)));
    }

    public List<TicketDto> getTicketsByUserId(int userId) {
        return ticketMapper.mapEntityListToDtoList(ticketRepo.findByUserId(userId));
    }

}
