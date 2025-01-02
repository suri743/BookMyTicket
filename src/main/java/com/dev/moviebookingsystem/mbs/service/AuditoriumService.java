package com.dev.moviebookingsystem.mbs.service;

import com.dev.moviebookingsystem.mbs.dto.AdminDataDto;
import com.dev.moviebookingsystem.mbs.dto.AuditoriumDto;
import com.dev.moviebookingsystem.mbs.exceptions.AuditoriumNotFoundException;
import com.dev.moviebookingsystem.mbs.exceptions.TheaterNotFoundException;
import com.dev.moviebookingsystem.mbs.mapper.AuditoriumMapper;
import com.dev.moviebookingsystem.mbs.mapper.MapperHelper;
import com.dev.moviebookingsystem.mbs.model.Auditorium;
import com.dev.moviebookingsystem.mbs.model.Theater;
import com.dev.moviebookingsystem.mbs.repository.AuditoriumRepository;
import com.dev.moviebookingsystem.mbs.repository.TheaterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        auditoriumDto = auditoriumDto.toBuilder().adminData(AdminDataDto
                                             .builder()
                                             .createdAt(LocalDateTime.now()).build()).build();

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
            .seats(seatService.getSeatByAuditoriumId(auditoriumId))
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

    public int getAuditoriumCapacity(int auditoriumId) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId)
            .orElseThrow(() -> new AuditoriumNotFoundException("Auditorium not found for id: " + auditoriumId));
        return auditorium.getCapacity();
    }
}
