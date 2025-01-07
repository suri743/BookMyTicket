package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.dto.TheaterDto;
import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.dto.UserDto;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Show;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import com.dev.moviebookingsystem.bmt.model.Theater;
import com.dev.moviebookingsystem.bmt.model.Ticket;
import com.dev.moviebookingsystem.bmt.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class TicketMapper {
    private final MovieMapper movieMapper;

    public List<Ticket> mapDtoListToEntityList(List<TicketDto> ticketDtos){
        return ticketDtos.stream()
            .map(this::mapDtoToEntity)
            .toList();
    }

    public List<TicketDto> mapEntityListToDtoList(List<Ticket> tickets){
        return tickets.stream()
            .map(this::mapEntityToDto)
            .toList();
    }

    public Ticket mapDtoToEntity(TicketDto ticketDto){

        Ticket.TicketBuilder<?, ?> ticketBuilder = Ticket.builder();

        if(ticketDto.getId() != null)
            ticketBuilder.id(ticketDto.getId());

        return ticketBuilder
            .seats(getSeatsDtoToEntity(ticketDto.getSeats()))
            .user(User.builder().id(ticketDto.getUser().getId()).build())
            .bookingTime(ticketDto.getBookingTime())
            .ticketNumber(ticketDto.getTicketNumber())
            .totalAmount(ticketDto.getTotalAmount())
            .ticketStatus(ticketDto.getTicketStatus())
            .show(mapShowToEntity(ticketDto.getShow()))
            .build();
    }

    public TicketDto mapEntityToDto(Ticket ticket){

        return TicketDto.builder()
            .id(ticket.getId())
            .seats(getSeatsEntityToDto(ticket.getSeats()))
            .user(UserDto.builder().id(ticket.getUser().getId()).build())
            .bookingTime(ticket.getBookingTime())
            .ticketNumber(ticket.getTicketNumber())
            .totalAmount(ticket.getTotalAmount())
            .ticketStatus(ticket.getTicketStatus())
            .seats(getSeatsEntityToDto(ticket.getSeats()))
            .show(mapShowToDto(ticket.getShow()))
            .build();

    }

    private List<ShowSeat> getSeatsDtoToEntity(List<ShowSeatDto> showSeatDtos){
        if(showSeatDtos == null)
            return List.of();

        List<ShowSeat> showSeats = new ArrayList<>();
        for(ShowSeatDto showSeatDto : showSeatDtos){
            showSeats.add(ShowSeat.builder()
                .id(showSeatDto.getId())
                .price(showSeatDto.getPrice())
                .seatStatus(showSeatDto.getSeatStatus())
                .seat(null)
                .build());
        }
        return showSeats;
    }

    private List<ShowSeatDto> getSeatsEntityToDto(List<ShowSeat> showSeats){
        if(showSeats == null)
            return List.of();

        List<ShowSeatDto> showSeatDtos = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeatDtos.add(ShowSeatDto.builder()
                .id(showSeat.getId())
                .price(showSeat.getPrice())
                .seatStatus(showSeat.getSeatStatus())
                .seatNumber(showSeat.getSeat() != null ? showSeat.getSeat().getSeatNumber() : null)
                .build());
        }
        return showSeatDtos;
    }

    private ShowDto mapShowToDto(Show show){
        return ShowDto.builder()
            .id(show.getId())
            .showDate(show.getShowDate())
            .startTime(show.getStartTime())
            .endTime(show.getEndTime())
            .movie(movieMapper.mapEntityToDto(show.getMovie()))
            .auditorium(mapAuditoriumToDto(show.getAuditorium()))
            .showStatus(show.getShowStatus())
            .language(show.getLanguage())
            .build();
    }

    private Show mapShowToEntity(ShowDto showDto){
        return Show.builder()
            .id(showDto.getId())
            .showDate(showDto.getShowDate())
            .startTime(showDto.getStartTime())
            .endTime(showDto.getEndTime())
            .movie(movieMapper.mapDtoToEntity(showDto.getMovie()))
            .auditorium(mapAuditoriumToEntity(showDto.getAuditorium()))
            .showStatus(showDto.getShowStatus())
            .language(showDto.getLanguage())
            .build();
    }

    private AuditoriumDto mapAuditoriumToDto(Auditorium auditorium){
        return AuditoriumDto.builder()
            .id(auditorium.getId())
            .name(auditorium.getName())
            .theater(mapTheaterToDto(auditorium.getTheater()))
            .auditoriumFeatures(auditorium.getAuditoriumFeatures())
            .build();
    }

    private Auditorium mapAuditoriumToEntity(AuditoriumDto auditoriumDto){
        if(Objects.isNull(auditoriumDto) || auditoriumDto.getId() == null)
            return Auditorium.builder().build();

        return Auditorium.builder()
            .id(auditoriumDto.getId())
            .name(auditoriumDto.getName())
            .theater(mapTheaterToEntity(auditoriumDto.getTheater()))
            .auditoriumFeatures(auditoriumDto.getAuditoriumFeatures())
            .build();


    }

    private Theater mapTheaterToEntity(TheaterDto theaterDto){

        if(Objects.isNull(theaterDto) || theaterDto.getId() == null)
            return Theater.builder().build();

        return Theater.builder()
            .id(theaterDto.getId())
            .name(theaterDto.getName())
            .build();
    }

    private TheaterDto mapTheaterToDto(Theater theater){
        return TheaterDto.builder()
            .id(theater.getId())
            .name(theater.getName())
            .build();
    }

}
