package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.exceptions.SeatNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.MapperHelper;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Seat;
import com.dev.moviebookingsystem.bmt.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final MapperHelper mapperHelper;
    private final SeatHelper seatHelper;

    public SeatDto createSeat(SeatDto seatDto) {

        Auditorium auditorium = seatHelper.validateSeatCreation(seatDto);

        SeatDto.SeatDtoBuilder seatDtoBuilder = seatHelper.seatDetermination(seatDto);

        Seat seat = mapperHelper.mapSeatDtoToEntity(seatDtoBuilder.build(), auditorium);

        seat = seatRepository.save(seat);
        return mapperHelper.mapSeatEntityToDto(seat, auditorium);
    }

    public SeatDto getSeatById(int seatId) {
        Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new SeatNotFoundException("Seat not found for id: " + seatId));

        return mapperHelper.mapSeatEntityToDto(seat, null);
    }

    public List<SeatDto> getAllSeats() {
        return seatRepository.findAll().stream()
            .map(seat -> this.getSeatById(seat.getId())).toList();
    }

    public List<SeatDto> getSeatByAuditoriumId(int auditoriumId) {
        List<Seat> seats = seatRepository.findSeatsByAuditoriumId(auditoriumId);
        return seats.stream()
            .map(seat -> this.getSeatById(seat.getId())).toList();
    }

}
