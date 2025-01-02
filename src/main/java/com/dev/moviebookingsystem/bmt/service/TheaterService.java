package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.AdminDataDto;
import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.dto.TheaterDto;
import com.dev.moviebookingsystem.bmt.exceptions.TheaterNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.TheaterMapper;
import com.dev.moviebookingsystem.bmt.model.Theater;
import com.dev.moviebookingsystem.bmt.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;
    private final AuditoriumService auditoriumService;

    public TheaterDto createTheater(TheaterDto theaterDto) {
        theaterDto = theaterDto.toBuilder().adminData(AdminDataDto
                                          .builder()
                                          .createdAt(LocalDateTime.now()).build()).build();
        Theater theater = theaterRepository.save(theaterMapper.mapDtoToEntity(theaterDto));
        return theaterMapper.mapEntityToDto(theater);
    }

    public TheaterDto getTheaterById(int theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
            .orElseThrow(() -> new TheaterNotFoundException("Theater not found for id: " + theaterId));

        List<AuditoriumDto> auditoriums = auditoriumService.getAuditoriumsByTheaterId(theaterId);
        return theaterMapper.mapEntityToDto(theater).toBuilder()
            .auditoriums(auditoriums).build();
    }

    public List<TheaterDto> getTheatersByCityId(int cityId) {
        List<Theater> theaters = theaterRepository.findTheatersByCityId(cityId);
        return theaterMapper.mapCityToTheaters(theaters);
    }

    public List<TheaterDto> getAllTheaters() {
        List<TheaterDto> theaterDtos = theaterMapper.mapEntityListToDtoList(
            theaterRepository.findAll());

       return theaterDtos.stream()
           .map(theaterDto -> this.getTheaterById(theaterDto.getId()))
           .toList();
    }
}
