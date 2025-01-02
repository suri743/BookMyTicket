package com.dev.moviebookingsystem.bmt.mapper;

import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.dto.TheaterDto;
import com.dev.moviebookingsystem.bmt.model.Auditorium;
import com.dev.moviebookingsystem.bmt.model.Seat;
import com.dev.moviebookingsystem.bmt.model.Theater;
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
