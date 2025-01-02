package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.UserDto;
import com.dev.moviebookingsystem.mbs.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    @Lazy
    private TicketMapper ticketMapper;

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    public abstract User mapDtoToEntity(UserDto userDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    public abstract UserDto mapEntityToDto(User user);

    public abstract List<UserDto> mapEntityListToDtoList(List<User> users);
    public abstract List<User> mapDtoListToEntityList(List<UserDto> users);

    @AfterMapping
    protected void mapUserTicketsToUserDto(@MappingTarget UserDto.UserDtoBuilder userDtoBuilder,User user) {
        userDtoBuilder.tickets(ticketMapper.mapEntityListToDtoList(user.getTickets()));
    }

    @AfterMapping
    protected void mapUserDtoTicketsToUser(@MappingTarget User userBuilder,UserDto userDto) {
        userBuilder.setTickets(ticketMapper.mapDtoListToEntityList(userDto.getTickets()));
    }
}
