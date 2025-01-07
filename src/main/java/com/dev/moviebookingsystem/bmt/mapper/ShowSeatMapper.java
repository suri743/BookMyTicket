package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.dto.MovieDto;
import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Movie;
import com.dev.moviebookingsystem.bmt.model.Seat;
import com.dev.moviebookingsystem.bmt.model.Show;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;

import com.dev.moviebookingsystem.bmt.model.Ticket;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ShowSeatMapper {

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "show", ignore = true)
    public abstract ShowSeat mapDtoToEntity(ShowSeatDto showSeatDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "show", ignore = true)
    public abstract ShowSeatDto mapEntityToDto(ShowSeat showSeat);

    public abstract List<ShowSeatDto> mapShowSeatEntityListToShowSeatDtoList(List<ShowSeat> showSeats);
    public abstract List<ShowSeat> mapShowSeatDtoListToShowSeatEntityList(List<ShowSeatDto> showSeats);

    @AfterMapping
    public void mapSeatToSeatDto(@MappingTarget ShowSeatDto.ShowSeatDtoBuilder showSeatDtoBuilder,
                                 ShowSeat showSeat) {
        if(showSeat.getSeat() != null)
            showSeatDtoBuilder.seat(mapSeatToSeatDto(showSeat.getSeat()));
    }

    @AfterMapping
    public void mapSeatDtoToSeat(@MappingTarget ShowSeat.ShowSeatBuilder<?, ?> showSeatBuilder,
                                 ShowSeatDto showSeatDto) {
        if(showSeatDto.getSeat() != null)
            showSeatBuilder.seat(mapSeatDtoToSeat(showSeatDto.getSeat()));
    }

    @AfterMapping
    public void mapShowToShowDto(@MappingTarget ShowSeatDto.ShowSeatDtoBuilder showSeatDtoBuilder,
                                 ShowSeat showSeat) {
        if(showSeat.getShow() != null)
            showSeatDtoBuilder.show(mapShowToShowDto(showSeat.getShow()));
    }

    @AfterMapping
    public void mapShowDtoToShow(@MappingTarget ShowSeat.ShowSeatBuilder<?, ?> showSeatBuilder,
                                 ShowSeatDto showSeatDto) {
        if(showSeatDto.getShow() != null)
            showSeatBuilder.show(mapShowDtoToShow(showSeatDto.getShow()));
    }

    @AfterMapping
    public void mapTicketToTicketDto(@MappingTarget ShowSeatDto.ShowSeatDtoBuilder showSeatDtoBuilder,
                                     ShowSeat showSeat) {
        if(showSeat.getTicket() != null)
            showSeatDtoBuilder.ticket(mapTicketToTicketDto(showSeat.getTicket()));
    }

    @AfterMapping
    public void mapTicketDtoToTicket(@MappingTarget ShowSeat.ShowSeatBuilder<?, ?> showSeatBuilder,
                                     ShowSeatDto showSeatDto) {
        if(showSeatDto.getTicket() != null)
            showSeatBuilder.ticket(mapTicketDtoToTicket(showSeatDto.getTicket()));
    }

    private SeatDto mapSeatToSeatDto(Seat seat) {
        if(Objects.isNull(seat) || seat.getId() == 0)
            return SeatDto.builder().build();

        return SeatDto.builder()
            .id(seat.getId())
            .seatNumber(seat.getSeatNumber())
            .seatType(seat.getSeatType())
            .build();
    }

    private Seat mapSeatDtoToSeat(SeatDto seatDto) {

        if(Objects.isNull(seatDto) || seatDto.getId() == null)
            return Seat.builder().build();

        return Seat.builder()
            .id(seatDto.getId())
            .seatNumber(seatDto.getSeatNumber())
            .seatType(seatDto.getSeatType())
            .build();
    }

    private ShowDto mapShowToShowDto(Show show) {
        if(Objects.isNull(show) || show.getId() == 0)
            return ShowDto.builder().build();

        return ShowDto.builder()
            .id(show.getId())
            .showDate(show.getShowDate())
            .startTime(show.getStartTime())
            .endTime(show.getEndTime())
            .movie(mapMovieToDto(show.getMovie()))
            .auditorium(mapAuditoriumToAuditoriumDto(show.getAuditorium()))
            .build();
    }

    private Show mapShowDtoToShow(ShowDto showDto) {
        if(Objects.isNull(showDto) || showDto.getId() == null)
            return Show.builder().build();

        return Show.builder()
            .id(showDto.getId())
            .showDate(showDto.getShowDate())
            .auditorium(mapAuditoriumDtoToAuditorium(showDto.getAuditorium()))
            .build();
    }

    private MovieDto mapMovieToDto(Movie movie) {
        if(Objects.isNull(movie) || movie.getId() == 0)
            return MovieDto.builder().build();

        return MovieDto.builder()
            .id(movie.getId())
            .name(movie.getName())
            .build();
    }

    private TicketDto mapTicketToTicketDto(Ticket ticket) {
        if(Objects.isNull(ticket) || ticket.getId() == 0)
            return TicketDto.builder().build();

        return TicketDto.builder()
            .id(ticket.getId())
            .build();
    }

    private Ticket mapTicketDtoToTicket(TicketDto ticketDto) {
        if(Objects.isNull(ticketDto) || ticketDto.getId() == null)
            return Ticket.builder().build();

        return Ticket.builder()
            .id(ticketDto.getId())
            .build();
    }

    private Auditorium mapAuditoriumDtoToAuditorium(AuditoriumDto auditoriumDto) {
        if(Objects.isNull(auditoriumDto) || auditoriumDto.getId() == null)
            return Auditorium.builder().build();

        return Auditorium.builder()
            .id(auditoriumDto.getId())
            .name(auditoriumDto.getName())
            .build();
    }

    private AuditoriumDto mapAuditoriumToAuditoriumDto(Auditorium auditorium) {
        if(Objects.isNull(auditorium) || auditorium.getId() == 0)
            return AuditoriumDto.builder().build();

        return AuditoriumDto.builder()
            .id(auditorium.getId())
            .name(auditorium.getName())
            .build();
    }

}
