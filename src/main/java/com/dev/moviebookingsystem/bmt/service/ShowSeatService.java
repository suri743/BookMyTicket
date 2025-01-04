package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.ShowSeatDto;
import com.dev.moviebookingsystem.bmt.exceptions.ShowSeatNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.ShowSeatMapper;
import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import com.dev.moviebookingsystem.bmt.model.constant.SeatStatus;
import com.dev.moviebookingsystem.bmt.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowSeatService {
    private final ShowSeatMapper showSeatMapper;
    private final ShowSeatRepository showSeatRepository;

    @Transactional
    public ShowSeatDto createShowSeat(ShowSeatDto showSeatDto) {
        return showSeatMapper.mapEntityToDto(showSeatRepository.save(showSeatMapper.mapDtoToEntity(showSeatDto)));
    }

    public ShowSeatDto getShowSeatById(int showSeatId) {
        return showSeatMapper.mapEntityToDto(showSeatRepository.findById(showSeatId)
                                                 .orElseThrow(() -> new ShowSeatNotFoundException("ShowSeat not found for id: " + showSeatId)));
    }

    public List<ShowSeatDto> getAllAvailableShowSeats() {
        return showSeatMapper.mapShowSeatEntityListToShowSeatDtoList(
            showSeatRepository.findAll().stream()
                .filter(showSeat ->
                            showSeat.getSeatStatus()
                                .equals(SeatStatus.AVAILABLE))
                .toList());
    }

    public List<ShowSeatDto> getShowSeatsByShowId(int showId) {
        List<ShowSeat> showSeats = showSeatRepository.findShowSeatsByShowId(showId);
        return showSeatMapper.mapShowSeatEntityListToShowSeatDtoList(showSeats);
    }

    public List<ShowSeatDto> getShowSeatsByTicketId(int ticketId) {
        return showSeatMapper.mapShowSeatEntityListToShowSeatDtoList(
            showSeatRepository.findByTicketId(ticketId));
    }
}
