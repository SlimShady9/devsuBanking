package com.devsu.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ReportResponseDto {
    private final LocalDate fecha;
    private final String cliente;
    private final String numeroCuenta;
    private final String tipo;
    private final double saldoInicial;
    private final boolean estado;
    private final double movimiento;
    private final double saldoDisponible;
}
