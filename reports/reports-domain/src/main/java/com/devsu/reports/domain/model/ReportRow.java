package com.devsu.reports.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ReportRow {
    private final LocalDate fecha;
    private final String cliente;
    private final String numeroCuenta;
    private final String tipo;
    private final double saldoInicial;
    private final boolean estado;
    private final double movimiento;

}