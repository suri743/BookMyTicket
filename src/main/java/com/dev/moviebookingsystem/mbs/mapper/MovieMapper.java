package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.MovieDto;
import com.dev.moviebookingsystem.mbs.model.Movie;
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
