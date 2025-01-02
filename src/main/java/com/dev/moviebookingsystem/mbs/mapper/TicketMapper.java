package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.TicketDto;
import com.dev.moviebookingsystem.mbs.model.Ticket;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShowMapper.class, ShowSeatMapper.class, UserMapper.class})
public interface TicketMapper {

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    Ticket mapDtoToEntity(TicketDto ticketDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    TicketDto mapEntityToDto(Ticket ticket);

    List<TicketDto> mapEntityListToDtoList(List<Ticket> tickets);
    List<Ticket> mapDtoListToEntityList(List<TicketDto> tickets);

}
