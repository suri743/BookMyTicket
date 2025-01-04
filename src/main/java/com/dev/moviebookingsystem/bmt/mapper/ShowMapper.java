package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.model.Seat;
import com.dev.moviebookingsystem.bmt.model.Show;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShowSeatMapper.class, MovieMapper.class})
public abstract class ShowMapper {

    @Autowired
    @Lazy
    ShowSeatMapper showSeatMapper;

    @Autowired
    @Lazy
    SeatMapper seatMapper;



    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "auditorium", ignore = true)
    public abstract ShowDto mapEntityToDto(Show show);

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "auditorium", ignore = true)
    public abstract Show mapDtoToEntity(ShowDto showDto);

    public abstract List<ShowDto> mapEntityListToDtoList(List<Show> shows);

    public abstract List<Show> mapDtoListToEntityList(List<ShowDto> showDtos);

    @AfterMapping
    public void mapSeatsToDto(Show show, @MappingTarget ShowDto.ShowDtoBuilder showDtoBuilder) {
        if (show.getSeats() != null) {
           List<ShowSeatDto> showSeatDtos = show.getSeats()
               .stream()
               .map(showSeat ->  ShowSeatDto.builder()
                          .id(showSeat.getId())
                          .price(showSeat.getPrice())
                          .seatStatus(showSeat.getSeatStatus())
                          .seatNumber(showSeat.getSeat() != null ?
                                showSeat.getSeat().getSeatNumber() : null)
                       .build())
               .toList();

            showDtoBuilder.seats(showSeatDtos);
        }
    }

    @AfterMapping
    public void mapSeatsToEntity(ShowDto showDto, @MappingTarget Show.ShowBuilder<?, ?> show) {
        if (showDto.getSeats() != null) {
            List<ShowSeat> showSeats = showDto.getSeats()
                .stream()
                .map(showSeatDto -> {
                    ShowSeat showSeat = new ShowSeat();
                    showSeat.setId(showSeatDto.getId());
                    showSeat.setPrice(showSeatDto.getPrice());
                    showSeat.setSeatStatus(showSeatDto.getSeatStatus());
                    showSeat.setSeat(showSeatDto.getSeatNumber() != null ?
                        Seat.builder().seatNumber(showSeatDto.getSeatNumber()).build() : null);
                     return showSeat;
                })
                .toList();

            // Set the parent reference using forEach
            showSeats.forEach(seat -> seat.setShow(show.build()));

            show.seats(showSeats);
        }
    }
}