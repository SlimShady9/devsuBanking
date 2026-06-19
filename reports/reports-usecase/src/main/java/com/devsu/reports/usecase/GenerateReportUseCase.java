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

    public List<ReportResponseDto> execute(LocalDate fechaInicio, LocalDate fechaFin, String cliente) {
        List<ReportRow> datosDominio = reportDataPort.getDatosReporte(fechaInicio, fechaFin, cliente);

        return ReportDtoMapper.toDtoList(datosDominio);
    }
}