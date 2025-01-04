package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ShowSeatMapper {

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
}
