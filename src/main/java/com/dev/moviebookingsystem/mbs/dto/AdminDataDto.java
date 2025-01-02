package com.dev.moviebookingsystem.mbs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminDataDto {
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

