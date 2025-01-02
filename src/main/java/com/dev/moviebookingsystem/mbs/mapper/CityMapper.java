package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.CityDto;
import com.dev.moviebookingsystem.mbs.model.City;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Mapping(target = "adminData.createdAt", source = "createdAt")
    @Mapping(target = "adminData.updatedAt", source = "updatedAt")
    @Mapping(target = "theaters", ignore = true)
    public abstract CityDto mapEntityToDto(City city);

    @Mapping(target = "createdAt", source = "adminData.createdAt")
    @Mapping(target = "updatedAt", source = "adminData.updatedAt")
    @Mapping(target = "theaters", ignore = true)
    public abstract City mapDtoToEntity(CityDto cityDto);

    public abstract List<CityDto> mapEntityListToDtoList(List<City> cities);
    public abstract List<City> mapDtoListToEntityList(List<CityDto> cities);
}
