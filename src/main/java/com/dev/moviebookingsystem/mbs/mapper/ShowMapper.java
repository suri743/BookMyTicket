package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.ShowDto;
import com.dev.moviebookingsystem.mbs.model.Show;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ShowSeatMapper.class, MovieMapper.class})
public abstract class ShowMapper {

    @Autowired
    @Lazy
    AuditoriumMapper auditoriumMapper;

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    public abstract Show mapDtoToEntity(ShowDto showDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    public abstract ShowDto mapEntityToDto(Show show);

    public abstract List<ShowDto> mapEntityListToDtoList(List<Show> shows);
    abstract List<Show> mapDtoListToEntityList(List<ShowDto> shows);

    @AfterMapping
    protected void mapAuditoriumToShowDto(ShowDto.ShowDtoBuilder showDtoBuilder, Show show) {
        showDtoBuilder.auditorium(auditoriumMapper.mapEntityToDto(show.getAuditorium()));
    }

    @AfterMapping
    protected void mapAuditoriumDtoToShow(Show showBuilder, ShowDto showDto) {
        showBuilder.setAuditorium(auditoriumMapper.mapDtoToEntity(showDto.getAuditorium()));
    }
}
