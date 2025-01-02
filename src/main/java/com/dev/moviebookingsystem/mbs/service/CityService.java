package com.dev.moviebookingsystem.mbs.service;

import com.dev.moviebookingsystem.mbs.dto.AdminDataDto;
import com.dev.moviebookingsystem.mbs.dto.CityDto;
import com.dev.moviebookingsystem.mbs.exceptions.CityNotFoundException;
import com.dev.moviebookingsystem.mbs.mapper.CityMapper;
import com.dev.moviebookingsystem.mbs.model.City;
import com.dev.moviebookingsystem.mbs.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final TheaterService theaterService;

    public CityDto createCity(CityDto cityDto) {
        cityDto = cityDto.toBuilder().adminData(AdminDataDto.builder()
                                       .createdAt(LocalDateTime.now()).build()).build();
        City city = cityRepository.save(cityMapper.mapDtoToEntity(cityDto));
        return cityMapper.mapEntityToDto(city);
    }

    public CityDto getCityById(int cityId) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new CityNotFoundException("City not found for id: " + cityId));

        return cityMapper.mapEntityToDto(city).toBuilder()
            .theaters(theaterService.getTheatersByCityId(cityId)).build();
    }

    public List<CityDto> getAllCities() {
        return cityMapper.mapEntityListToDtoList(cityRepository.findAll()).stream()
            .map(cityDto -> cityDto.toBuilder()
                 .theaters(theaterService.getTheatersByCityId(cityDto.getId())).build())
            .toList();
    }

    public void deleteCity(int cityId) {
        cityRepository.deleteById(cityId);
    }
}
