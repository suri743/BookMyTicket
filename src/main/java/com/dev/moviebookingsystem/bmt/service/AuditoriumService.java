package com.dev.moviebookingsystem.bmt.service;
import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.exceptions.AuditoriumNotFoundException;
import com.dev.moviebookingsystem.bmt.exceptions.TheaterNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.AuditoriumMapper;
import com.dev.moviebookingsystem.bmt.mapper.MapperHelper;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Theater;
import com.dev.moviebookingsystem.bmt.repository.AuditoriumRepository;
import com.dev.moviebookingsystem.bmt.repository.TheaterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final AuditoriumMapper auditoriumMapper;
    private final ShowService showService;
    private final SeatService seatService;
    private final MapperHelper mapperHelper;
    private final TheaterRepository theaterRepository;

    @Transactional
    public AuditoriumDto createAuditorium(AuditoriumDto auditoriumDto) {
        Optional<Theater> optionalTheater = theaterRepository.findById(auditoriumDto.getTheater().getId());

        if (optionalTheater.isEmpty()) {
            throw new TheaterNotFoundException("Theater not found for id: " + auditoriumDto.getTheater().getId());
        }

        Auditorium auditorium = auditoriumMapper.mapDtoToEntity(auditoriumDto);
        auditorium = auditoriumRepository.save(mapperHelper.mapTheaterEntityToDto(auditorium, optionalTheater.get()));
        return auditoriumMapper.mapEntityToDto(auditorium);
    }

    public AuditoriumDto getAuditoriumById(int auditoriumId) {

        Auditorium auditorium = auditoriumRepository.findById(auditoriumId)
            .orElseThrow(() -> new AuditoriumNotFoundException("Auditorium not found for id: " + auditoriumId));

        AuditoriumDto auditoriumDto = auditoriumMapper.mapEntityToDto(auditorium);

        auditoriumDto = auditoriumDto.toBuilder()
            .shows(showService.getShowsByAuditoriumId(auditoriumId))
            .seats(seatService.getSeatsByAuditoriumId(auditoriumId))
            .theater(mapperHelper.getTheaterDto(auditorium.getTheater()))
            .build();

        return auditoriumDto;
    }

    public List<AuditoriumDto> getAllAuditoriums() {
        List<AuditoriumDto> auditoriumDtos = auditoriumMapper.mapEntityListToDtoList(auditoriumRepository.findAll());
        auditoriumDtos = auditoriumDtos.stream()
            .map(auditoriumDto -> this.getAuditoriumById(auditoriumDto.getId()))
            .toList();

        return auditoriumDtos;
    }

    public List<AuditoriumDto> getAuditoriumsByTheaterId(int theaterId) {
        return auditoriumMapper.mapEntityListToDtoList(
            auditoriumRepository.findAuditoriumsByTheaterId(theaterId));
    }
}
