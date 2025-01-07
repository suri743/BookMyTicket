package com.dev.moviebookingsystem.bmt.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
    Integer id;
    AdminDataDto adminData;
    String name;
    String email;
    String mobileNumber;

    @JsonBackReference
    List<TicketDto> tickets;
}
