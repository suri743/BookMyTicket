package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.AdminDataDto;
import com.dev.moviebookingsystem.mbs.dto.AuditoriumDto;
import com.dev.moviebookingsystem.mbs.model.Auditorium;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

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
            .adminData(getAdminDataDto(auditorium))
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
        return auditoriums.stream().map(this::mapEntityToDto).toList();
    }

    public List<Auditorium> mapDtoListToEntityList(List<AuditoriumDto> auditoriumDtos) {
        return auditoriumDtos.stream().map(this::mapDtoToEntity).toList();
    }

    private static AdminDataDto getAdminDataDto(Auditorium auditorium) {
        return AdminDataDto
            .builder()
            .createdAt(auditorium.getCreatedAt())
            .updatedAt(auditorium.getUpdatedAt())
            .build();
    }
}
