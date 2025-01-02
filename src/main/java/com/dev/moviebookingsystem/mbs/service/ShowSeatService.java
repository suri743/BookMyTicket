package com.dev.moviebookingsystem.mbs.service;

import com.dev.moviebookingsystem.mbs.dto.AdminDataDto;
import com.dev.moviebookingsystem.mbs.dto.ShowSeatDto;
import com.dev.moviebookingsystem.mbs.exceptions.ShowSeatNotFoundException;
import com.dev.moviebookingsystem.mbs.mapper.ShowSeatMapper;
import com.dev.moviebookingsystem.mbs.model.ShowSeat;
import com.dev.moviebookingsystem.mbs.model.constant.SeatStatus;
import com.dev.moviebookingsystem.mbs.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowSeatService {
    private final ShowSeatMapper showSeatMapper;
    private final ShowSeatRepository showSeatRepository;

    @Transactional
    public ShowSeatDto createShowSeat(ShowSeatDto showSeatDto) {
        showSeatDto = showSeatDto.toBuilder()
            .adminData(AdminDataDto
                           .builder()
                           .createdAt(LocalDateTime.now()).build())
            .build();
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
