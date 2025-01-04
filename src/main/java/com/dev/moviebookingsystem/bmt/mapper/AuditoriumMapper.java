package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AuditoriumMapper {

    @Autowired
    @Lazy
    SeatMapper seatMapper;

    @Autowired
    @Lazy
    ShowMapper showMapper;

    @Autowired
    @Lazy
    TheaterMapper theaterMapper;

    public AuditoriumDto mapEntityToDto(Auditorium auditorium) {
        return AuditoriumDto
            .builder()
            .id(auditorium.getId())
            .name(auditorium.getName())
            .capacity(auditorium.getCapacity())
            .auditoriumFeatures(auditorium.getAuditoriumFeatures())
            .seats(seatMapper.mapEntityListToDtoList(auditorium.getSeats()))
            .shows(showMapper.mapEntityListToDtoList(auditorium.getShows()))
            .build();
    }

    public Auditorium mapDtoToEntity(AuditoriumDto auditoriumDto) {

        Auditorium auditorium = new Auditorium();

        if ( auditoriumDto.getId() != null ) {
            auditorium.setId(auditoriumDto.getId() );
        }

        if(auditoriumDto.getAdminData() != null) {
            auditorium.setCreatedAt(auditoriumDto.getAdminData().getCreatedAt());
            auditorium.setUpdatedAt(auditoriumDto.getAdminData().getUpdatedAt());
        }

        if(auditoriumDto.getTheater() != null) {
            auditorium.setTheater(theaterMapper.mapDtoToEntity(auditoriumDto.getTheater()));
        }

        return auditorium.toBuilder()
            .name(auditoriumDto.getName())
            .capacity(auditoriumDto.getCapacity())
            .auditoriumFeatures(auditoriumDto.getAuditoriumFeatures())
            .seats(seatMapper.mapDtoListToEntityList(auditoriumDto.getSeats()))
            .shows(showMapper.mapDtoListToEntityList(auditoriumDto.getShows()))
            .build();
    }

    public List<AuditoriumDto> mapEntityListToDtoList(List<Auditorium> auditoriums) {
        return Objects.isNull(auditoriums) ? null :
            auditoriums.stream().map(this::mapEntityToDto).toList();
    }

    public List<Auditorium> mapDtoListToEntityList(List<AuditoriumDto> auditoriumDtos) {
        return Objects.isNull(auditoriumDtos) ? null :
               auditoriumDtos.stream().map(this::mapDtoToEntity).toList();
    }

    public AuditoriumDto mapEntityToDtoForShow(Auditorium auditorium) {
        return AuditoriumDto
            .builder()
            .id(auditorium.getId())
            .name(auditorium.getName())
            .capacity(auditorium.getCapacity())
            .auditoriumFeatures(auditorium.getAuditoriumFeatures())
            .theater(theaterMapper.mapEntityToDto(auditorium.getTheater()))
            .build();
    }

    public Auditorium mapDtoToEntityForShow(AuditoriumDto auditoriumDto) {
        return Auditorium
            .builder()
            .id(auditoriumDto.getId())
            .name(auditoriumDto.getName())
            .capacity(auditoriumDto.getCapacity())
            .auditoriumFeatures(auditoriumDto.getAuditoriumFeatures())
            .createdAt(auditoriumDto.getAdminData().getCreatedAt())
            .updatedAt(auditoriumDto.getAdminData().getUpdatedAt())
            .theater(theaterMapper.mapDtoToEntity(auditoriumDto.getTheater()))
            .build();
    }
}
