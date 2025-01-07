package com.dev.moviebookingsystem.bmt.util;

import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.dto.UserDto;
import com.dev.moviebookingsystem.bmt.exceptions.InvalidTicketDetailsException;
import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.dev.moviebookingsystem.bmt.service.ShowSeatService;
import com.dev.moviebookingsystem.bmt.service.ShowService;
import com.dev.moviebookingsystem.bmt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TicketHelper {

    private final UserService userService;
    private final ShowSeatService showSeatService;
    private final ShowService showService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeatDto> validateTicket(TicketDto ticketDto)
        throws InvalidTicketDetailsException {
        if (Objects.isNull(ticketDto.getUser()) || Objects.isNull(ticketDto.getUser().getId())) {
            throw new InvalidTicketDetailsException("Invalid user provided: ");
        }
        Integer userId = ticketDto.getUser().getId();
        UserDto user = userService.getUserById(userId);
        if(Objects.isNull(user)) {
            throw new InvalidTicketDetailsException("User not found for id: " + userId);
        }

        if(ticketDto.getSeats() == null || ticketDto.getSeats().isEmpty()) {
            throw new InvalidTicketDetailsException("No seats provided for ticket");
        }

        if(Objects.isNull(ticketDto.getShow()) || Objects.isNull(ticketDto.getShow().getId())) {
            throw new InvalidTicketDetailsException("Invalid show provided: ");
        }

        if(Objects.isNull(showService.getShowById(ticketDto.getShow().getId()))) {
            throw new InvalidTicketDetailsException("Show not found for id: " + ticketDto.getShow().getId());
        }

        ShowDto show = showService.getShowById(ticketDto.getShow().getId());

        Map<Integer, ShowSeatDto> showSeats =
            showSeatService.getShowSeatsByShowId(ticketDto.getShow().getId()).stream()
                .collect(Collectors.toMap(ShowSeatDto::getId, seat -> seat));

        if(showSeats.isEmpty())
            throw new InvalidTicketDetailsException("All the seats are booked for show: "
                                                    + show.getStartTime());

        List<ShowSeatDto> bookingSeats = new ArrayList<>();

        for (ShowSeatDto seat : ticketDto.getSeats()){
            if(!showSeats.containsKey(seat.getId())) {
                throw new InvalidTicketDetailsException("Provided Seat not available: " + seat.getId());
            }
            bookingSeats.add(showSeats.get(seat.getId()));
        }

        if(bookingSeats.size() != ticketDto.getSeats().size()) {
            throw new InvalidTicketDetailsException("Invalid seats provided for ticket");
        }

        return bookingSeats;
    }

}
