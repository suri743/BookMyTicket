package com.dev.moviebookingsystem.bmt.service;

<<<<<<< Updated upstream:src/main/java/com/dev/moviebookingsystem/bmt/service/SeatHelper.java
import com.dev.moviebookingsystem.bmt.dto.AdminDataDto;
import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.exceptions.InvalidSeatRowColumnException;
import com.dev.moviebookingsystem.bmt.exceptions.SeatNotFoundException;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Seat;
import com.dev.moviebookingsystem.bmt.model.constant.PhysicalSeatStatus;
import com.dev.moviebookingsystem.bmt.model.constant.SeatType;
import com.dev.moviebookingsystem.bmt.repository.AuditoriumRepository;
import com.dev.moviebookingsystem.bmt.repository.SeatRepository;
=======
import com.dev.moviebookingsystem.mbs.dto.SeatDto;
import com.dev.moviebookingsystem.mbs.exceptions.InvalidSeatRowColumnException;
import com.dev.moviebookingsystem.mbs.exceptions.SeatNotFoundException;
import com.dev.moviebookingsystem.mbs.model.Auditorium;
import com.dev.moviebookingsystem.mbs.model.Seat;
import com.dev.moviebookingsystem.mbs.model.constant.PhysicalSeatStatus;
import com.dev.moviebookingsystem.mbs.model.constant.SeatType;
import com.dev.moviebookingsystem.mbs.repository.AuditoriumRepository;
import com.dev.moviebookingsystem.mbs.repository.SeatRepository;
>>>>>>> Stashed changes:src/main/java/com/dev/moviebookingsystem/mbs/service/SeatHelper.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SeatHelper {

    private final SeatRepository seatRepository;
    private final AuditoriumRepository auditoriumRepository;

    public Auditorium validateSeatCreation(SeatDto seatDto) {
        if(Objects.isNull(seatDto.getRow())
           || seatDto.getRow() < 0
           || Objects.isNull(seatDto.getColumn())
           || seatDto.getColumn() < 0) {
            throw new InvalidSeatRowColumnException("Invalid row or column for seat: "
                                                    + seatDto.getRow() + " " + seatDto.getColumn());
        }

        List<Seat> allSeats = seatRepository.findAll();

        for(Seat seat : allSeats) {
            if((seat.getRow() == seatDto.getRow() && seat.getColumn() == seatDto.getColumn()) ||
               seat.getSeatNumber().equals(seatDto.getSeatNumber())) {
                throw new InvalidSeatRowColumnException("Seat already exists for row and column: "
                                                        + seatDto.getRow() + " " + seatDto.getColumn());
            }
        }

        Auditorium auditoriumCapacity = auditoriumRepository.findById(seatDto.getAuditorium().getId())
            .orElseThrow(() -> new SeatNotFoundException("Auditorium not found for id: "
                                                        + seatDto.getAuditorium().getId()));

        List<Seat> seats = seatRepository.findSeatsByAuditoriumId(seatDto.getAuditorium().getId());

        if(seats.size() + 1 > auditoriumCapacity.getCapacity()) {
            throw new SeatNotFoundException("Seat capacity exceeded for auditorium: "
                                            + seatDto.getAuditorium().getId());
        }

        return auditoriumCapacity;
    }

    public SeatDto.SeatDtoBuilder seatDetermination(SeatDto seatDto) {
        SeatDto.SeatDtoBuilder seatDtoBuilder = seatDto.toBuilder();
        if(seatDto.getSeatStatus() == null) {
            seatDtoBuilder.seatStatus(PhysicalSeatStatus.AVAILABLE);
        }

        if(seatDto.getSeatType() == null) {
            seatDtoBuilder.seatType(SeatType.SILVER);
        }
        return seatDtoBuilder;
    }
}
