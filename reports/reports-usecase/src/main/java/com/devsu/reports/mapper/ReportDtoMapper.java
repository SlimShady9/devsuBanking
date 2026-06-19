package com.devsu.reports.mapper;

import com.devsu.reports.domain.model.ReportRow;
import com.devsu.reports.dto.ReportResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportDtoMapper {

    public static ReportResponseDto toDto(ReportRow domain) {
        return new ReportResponseDto(
                domain.getFecha(),
                domain.getCliente(),
                domain.getNumeroCuenta(),
                domain.getTipo(),
                domain.getSaldoInicial(),
                domain.isEstado(),
                domain.getMovimiento(),
                domain.getSaldoDisponible());
    }

    public static List<ReportResponseDto> toDtoList(List<ReportRow> domainList) {
        return domainList.stream()
                .map(ReportDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}