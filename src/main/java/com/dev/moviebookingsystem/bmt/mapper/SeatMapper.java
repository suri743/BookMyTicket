package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.model.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    @Mapping(target = "auditorium", ignore = true)
    Seat mapDtoToEntity(SeatDto seatDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    @Mapping(target = "auditorium", ignore = true)
    SeatDto mapEntityToDto(Seat seat);

    List<SeatDto> mapEntityListToDtoList(List<Seat> seats);
    List<Seat> mapDtoListToEntityList(List<SeatDto> seats);
}
