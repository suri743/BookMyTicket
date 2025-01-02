package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.AuditoriumDto;
import com.dev.moviebookingsystem.mbs.dto.SeatDto;
import com.dev.moviebookingsystem.mbs.dto.TheaterDto;
import com.dev.moviebookingsystem.mbs.model.Auditorium;
import com.dev.moviebookingsystem.mbs.model.Seat;
import com.dev.moviebookingsystem.mbs.model.Theater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {
    private final TheaterMapper theaterMapper;
    private final SeatMapper seatMapper;
    private final AuditoriumMapper auditoriumMapper;

    public Auditorium mapTheaterEntityToDto(Auditorium auditorium, Theater theater) {
        return auditorium.toBuilder()
            .theater(theater)
            .build();
    }

    public TheaterDto getTheaterDto(Theater theater) {
        return theaterMapper.mapEntityToDto(theater);
    }

    public SeatDto mapSeatEntityToDto(Seat seat, Auditorium auditorium){
        SeatDto seatDto = seatMapper.mapEntityToDto(seat);

        if(auditorium == null) {
            seatDto = seatDto.toBuilder()
                .auditoriumId(seat.getAuditorium() == null ? null : seat.getAuditorium().getId())
                .build();
        }else {
            seatDto = seatDto.toBuilder()
                .auditoriumId(auditorium.getId())
                .build();
        }


        return seatDto;
    }

    public Seat mapSeatDtoToEntity(SeatDto seat, Auditorium auditorium){
        Seat seatEntity = seatMapper.mapDtoToEntity(seat);
        seatEntity = seatEntity.toBuilder()
            .auditorium(auditorium)
            .build();

        return seatEntity;
    }
}
