package com.dev.moviebookingsystem.mbs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CityDto {
    int id;
    AdminDataDto adminData;
    String name;
    String state;
    List<TheaterDto> theaters;
}
