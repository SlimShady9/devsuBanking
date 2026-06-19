package com.devsu.reports.usecase;

import com.devsu.reports.domain.port.ReportDataPort;
import com.devsu.reports.domain.model.ReportRow;
import com.devsu.reports.dto.ReportResponseDto;
import com.devsu.reports.mapper.ReportDtoMapper;

import java.time.LocalDate;
import java.util.List;

public class GenerateReportUseCase {

    private final ReportDataPort reportDataPort;

    public GenerateReportUseCase(ReportDataPort reportDataPort) {
        this.reportDataPort = reportDataPort;
    }

    public List<ReportResponseDto> execute(LocalDate initDate, LocalDate endDate, String customer) {
        List<ReportRow> domainData = reportDataPort.getReportData(initDate, endDate, customer);

        return ReportDtoMapper.toDtoList(domainData);
    }
}