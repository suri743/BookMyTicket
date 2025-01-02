package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.MovieDto;
import com.dev.moviebookingsystem.bmt.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuditoriumMapper.class)
public interface MovieMapper {

    @Mapping(target = "adminData.createdAt", source = "createdAt")
    @Mapping(target = "adminData.updatedAt", source = "updatedAt")
    MovieDto mapEntityToDto(Movie movie);

    @Mapping(target = "createdAt", source = "adminData.createdAt")
    @Mapping(target = "updatedAt", source = "adminData.updatedAt")
    Movie mapDtoToEntity(MovieDto movieDto);

    List<MovieDto> mapEntityListToDtoList(List<Movie> movies);
    List<Movie> mapDtoListToEntityList(List<MovieDto> movieDtos);
}
