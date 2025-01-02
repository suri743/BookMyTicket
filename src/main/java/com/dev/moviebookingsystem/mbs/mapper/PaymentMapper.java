package com.dev.moviebookingsystem.mbs.mapper;

import com.dev.moviebookingsystem.mbs.dto.PaymentDto;
import com.dev.moviebookingsystem.mbs.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface PaymentMapper {

    @Mapping(source = "adminData.createdAt", target = "createdAt")
    @Mapping(source = "adminData.updatedAt", target = "updatedAt")
    Payment mapDtoToEntity(PaymentDto paymentDto);

    @Mapping(source = "createdAt", target = "adminData.createdAt")
    @Mapping(source = "updatedAt", target = "adminData.updatedAt")
    PaymentDto mapEntityToDto(Payment payment);

    List<PaymentDto> mapEntityListToDtoList(List<Payment> payments);
    List<Payment> mapDtoListToEntityList(List<PaymentDto> payments);
}
