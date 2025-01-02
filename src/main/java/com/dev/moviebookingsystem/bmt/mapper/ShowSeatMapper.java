package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ShowSeatMapper {

    @Autowired
    @Lazy
    SeatMapper seatMapper;

    @Autowired
    @Lazy
    ShowMapper showMapper;

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    @Mapping(target = "ticket", ignore = true)
    public abstract ShowSeat mapDtoToEntity(ShowSeatDto showSeatDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    @Mapping(target = "ticket", ignore = true)
    public abstract ShowSeatDto mapEntityToDto(ShowSeat showSeat);

    public abstract List<ShowSeatDto> mapShowSeatEntityListToShowSeatDtoList(List<ShowSeat> showSeats);
    public abstract List<ShowSeat> mapShowSeatDtoListToShowSeatEntityList(List<ShowSeatDto> showSeats);

    @AfterMapping
    public void mapSeatToShowSeatDto(ShowSeatDto.ShowSeatDtoBuilder showSeatDtoBuilder, ShowSeat showSeat) {
        showSeatDtoBuilder.seat(seatMapper.mapEntityToDto(showSeat.getSeat()));
    }

    @AfterMapping
    public void mapShowToShowSeatDto(ShowSeatDto.ShowSeatDtoBuilder showSeatDtoBuilder, ShowSeat showSeat) {
        showSeatDtoBuilder.show(showMapper.mapEntityToDto(showSeat.getShow()));
    }

    @AfterMapping
    public void mapSeatDtoToShowSeat(ShowSeat showSeatBuilder, ShowSeatDto showSeatDto) {
        showSeatBuilder.setSeat(seatMapper.mapDtoToEntity(showSeatDto.getSeat()));
    }

    @AfterMapping
    public void mapShowDtoToShowSeat(ShowSeat showSeatBuilder, ShowSeatDto showSeatDto) {
        showSeatBuilder.setShow(showMapper.mapDtoToEntity(showSeatDto.getShow()));
    }
}
