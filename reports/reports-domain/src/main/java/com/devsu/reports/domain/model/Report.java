package com.devsu.reports.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final String cliente;
    private final List<ReportRow> filas;

    public Report(LocalDate fechaInicio, LocalDate fechaFin, String cliente, List<ReportRow> datosEnBruto) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.filas = filtrarYProcesar(datosEnBruto);
    }

    private List<ReportRow> filtrarYProcesar(List<ReportRow> datos) {
        return datos.stream()
                .filter(row -> fechaInicio == null || !row.getFecha().isBefore(fechaInicio))
                .filter(row -> fechaFin == null || !row.getFecha().isAfter(fechaFin))
                .filter(row -> cliente == null || row.getCliente().equalsIgnoreCase(cliente))
                .collect(Collectors.toList());
    }

    public List<ReportRow> getFilas() {
        return filas;
    }
}