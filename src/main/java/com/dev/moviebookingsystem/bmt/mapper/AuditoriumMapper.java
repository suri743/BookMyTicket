package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuditoriumMapper {

    @Lazy
    private final SeatMapper seatMapper;
    @Lazy
    private final ShowMapper showMapper;

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
        return Auditorium
            .builder()
            .id(auditoriumDto.getId())
            .name(auditoriumDto.getName())
            .capacity(auditoriumDto.getCapacity())
            .auditoriumFeatures(auditoriumDto.getAuditoriumFeatures())
            .createdAt(auditoriumDto.getAdminData().getCreatedAt())
            .updatedAt(auditoriumDto.getAdminData().getUpdatedAt())
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
}
