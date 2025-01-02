package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.CityDto;
import com.dev.moviebookingsystem.mbs.dto.TheaterDto;
import com.dev.moviebookingsystem.mbs.model.Theater;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring",
    uses = {AuditoriumMapper.class, CityMapper.class})
@DependsOn("cityMapper")
public abstract class TheaterMapper {

    @Autowired
    @Lazy
    CityMapper cityMapper;

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    public abstract Theater mapDtoToEntity(TheaterDto theaterDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    public abstract TheaterDto mapEntityToDto(Theater theater);

    public abstract List<TheaterDto> mapEntityListToDtoList(List<Theater> theaters);
    public abstract List<Theater> mapDtoListToEntityList(List<TheaterDto> theaters);

    @AfterMapping
    public void mapCityToTheaterDto(TheaterDto.TheaterDtoBuilder theaterDtoBuilder, Theater theater) {
        theaterDtoBuilder.city(cityMapper.mapEntityToDto(theater.getCity()));
    }

    @AfterMapping
    public void mapTheaterDtoToCity(Theater theaterBuilder, TheaterDto theaterDto) {
        theaterBuilder.setCity(cityMapper.mapDtoToEntity(theaterDto.getCity()));
    }

    @AfterMapping
    public List<TheaterDto> mapCityToTheaters(List<Theater> theaters) {
        return theaters.stream().map(theater -> {
            TheaterDto theaterDto = mapEntityToDto(theater);
            theaterDto = theaterDto.toBuilder().city(null).build();
            return theaterDto;
        }).toList();
    }


}
