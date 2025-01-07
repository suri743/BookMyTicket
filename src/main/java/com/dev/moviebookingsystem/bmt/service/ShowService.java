package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.dto.ShowDto;
import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.exceptions.ShowNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.AuditoriumMapper;
import com.dev.moviebookingsystem.bmt.mapper.ShowMapper;
import com.dev.moviebookingsystem.bmt.mapper.ShowSeatMapper;
import com.dev.moviebookingsystem.bmt.model.Show;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.dev.moviebookingsystem.bmt.repository.AuditoriumRepository;
import com.dev.moviebookingsystem.bmt.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final AuditoriumMapper auditoriumMapper;
    private final ShowSeatService showSeatService;
    private final SeatService seatService;
    private final ShowMapper showMapper;
    private final ShowSeatMapper showSeatMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ShowDto createShow(ShowDto showDto) {

        Show show = showMapper.mapDtoToEntity(showDto);

        show.setAuditorium(auditoriumRepository.findById(showDto.getAuditorium().getId())
            .orElseThrow(() -> new ShowNotFoundException("Auditorium not found for id: " + showDto.getAuditorium().getId())));

        show = showRepository.save(show);

        List<ShowSeat> showSeats = new ArrayList<>();

        List<SeatDto> seats = seatService.getSeatsByAuditoriumId(show.getAuditorium().getId());

        for(SeatDto seat : seats) {
            ShowSeatDto showSeat = ShowSeatDto.builder()
                .show(showMapper.mapEntityToDto(show))
                .seat(seat)
                .price(300)
                .seatStatus(SeatStatus.AVAILABLE)
                .build();
            showSeat = showSeatService.createShowSeat(showSeat);
            showSeats.add(showSeatMapper.mapDtoToEntity(showSeat));
        }

        show = show.toBuilder()
            .seats(showSeats)
            .build();

        show = showRepository.save(show);
        return showMapper.mapEntityToDto(show);
    }

    public ShowDto getShowById(int showId) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new ShowNotFoundException("Show not found for id: " + showId));

        ShowDto showDto = showMapper.mapEntityToDto(show);
        showDto = showDto.toBuilder()
            .auditorium(auditoriumMapper.mapEntityToDtoForShow(show.getAuditorium()))
            .build();

        return showDto;
    }

    public List<ShowDto> getShowsByAuditoriumId(int auditoriumId) {
        List<Show> shows = showRepository.findShowsByAuditoriumId(auditoriumId);
        return showMapper.mapEntityListToDtoList(shows);
    }

    public List<ShowDto> getAllShows() {
        return showRepository.findAll()
            .stream()
            .map(show -> this.getShowById(show.getId())).toList();
    }

    public void deleteShow(int showId) {
        showRepository.deleteById(showId);
    }
}
