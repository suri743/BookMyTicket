package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.AdminDataDto;
import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.exceptions.ShowNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.ShowMapper;
import com.dev.moviebookingsystem.bmt.model.Show;
import com.dev.moviebookingsystem.bmt.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;
    private final ShowMapper showMapper;

    public ShowDto createShow(ShowDto showDto) {
        showDto = showDto.toBuilder().adminData(AdminDataDto
                                          .builder()
                                          .createdAt(LocalDateTime.now()).build()).build();
        Show show = showRepository.save(showMapper.mapDtoToEntity(showDto));
        return showMapper.mapEntityToDto(show);
    }

    public ShowDto getShowById(int showId) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new ShowNotFoundException("Show not found for id: " + showId));

        return showMapper.mapEntityToDto(show);
    }

    public List<ShowDto> getShowsByAuditoriumId(int auditoriumId) {
        List<Show> shows = showRepository.findShowsByAuditoriumId(auditoriumId);
        return showMapper.mapEntityListToDtoList(shows);
    }

    public List<ShowDto> getAllShows() {
        return showMapper.mapEntityListToDtoList(showRepository.findAll());
    }

    public void deleteShow(int showId) {
        showRepository.deleteById(showId);
    }
}
