package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.UserDto;
import com.dev.moviebookingsystem.bmt.model.User;
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
    public TicketMapper ticketMapper;

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    @Mapping(target = "tickets", ignore = true)
    public abstract User mapDtoToEntity(UserDto userDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    @Mapping(target = "tickets", ignore = true)
    public abstract UserDto mapEntityToDto(User user);

    public abstract List<UserDto> mapEntityListToDtoList(List<User> users);
    public abstract List<User> mapDtoListToEntityList(List<UserDto> users);

    @AfterMapping
    public void mapUserTicketsToUserDto(@MappingTarget UserDto.UserDtoBuilder userDtoBuilder,User user) {
        if(user.getTickets() != null)
            userDtoBuilder.tickets(ticketMapper.mapEntityListToDtoList(user.getTickets()));
    }

    @AfterMapping
    public void mapUserDtoTicketsToUser(@MappingTarget User.UserBuilder<?, ?> userBuilder,UserDto userDto) {
        if(userDto.getTickets() != null)
            userBuilder.tickets(ticketMapper.mapDtoListToEntityList(userDto.getTickets()));
    }
}
