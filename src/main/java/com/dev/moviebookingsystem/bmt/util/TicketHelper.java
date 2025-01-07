package com.dev.moviebookingsystem.bmt.util;

import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.dto.UserDto;
import com.dev.moviebookingsystem.bmt.exceptions.InvalidTicketDetailsException;
import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.dev.moviebookingsystem.bmt.service.ShowSeatService;
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

        Map<Integer, ShowSeatDto> showSeats =
            showSeatService.getShowSeatsByShowId(ticketDto.getShow().getId()).stream()
                .collect(Collectors.toMap(ShowSeatDto::getId, seat -> seat));

        List<ShowSeatDto> bookingSeats = new ArrayList<>();

        for (ShowSeatDto seat : ticketDto.getSeats()){
            if(!showSeats.containsKey(seat.getId())) {
                throw new InvalidTicketDetailsException("Seat not available for show: " + seat.getId());
            }
            bookingSeats.add(showSeats.get(seat.getId()));
        }

        if(bookingSeats.size() != ticketDto.getSeats().size()) {
            throw new InvalidTicketDetailsException("Invalid seats provided for ticket");
        }

        return bookingSeats;
    }

}
