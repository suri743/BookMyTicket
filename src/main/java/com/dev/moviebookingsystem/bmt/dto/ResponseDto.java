package com.dev.moviebookingsystem.bmt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto<T> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;
}
