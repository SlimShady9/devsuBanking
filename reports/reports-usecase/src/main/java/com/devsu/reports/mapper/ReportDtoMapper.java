package com.devsu.reports.mapper;

import com.devsu.reports.domain.model.ReportRow;
import com.devsu.reports.dto.ReportResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportDtoMapper {

    public static ReportResponseDto toDto(ReportRow domain) {
        return new ReportResponseDto(
                domain.getDate(),
                domain.getCustomer(),
                domain.getAccountNumber(),
                domain.getType(),
                domain.getInitialBalance(),
                domain.isState(),
                domain.getMovement(),
                domain.getBalance());
    }

    public static List<ReportResponseDto> toDtoList(List<ReportRow> domainList) {
        return domainList.stream()
                .map(ReportDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}