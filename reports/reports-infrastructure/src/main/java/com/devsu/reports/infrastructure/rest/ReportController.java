package com.devsu.reports.infrastructure.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import com.devsu.reports.dto.ReportResponseDto;
import com.devsu.reports.usecase.GenerateReportUseCase;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private final GenerateReportUseCase generateReportUseCase;

    public ReportController(GenerateReportUseCase generateReportUseCase) {
        this.generateReportUseCase = generateReportUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> getReporte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) String cliente) {

        List<ReportResponseDto> report = generateReportUseCase.execute(fechaInicio, fechaFin, cliente);
        return ResponseEntity.ok(report);
    }
}
