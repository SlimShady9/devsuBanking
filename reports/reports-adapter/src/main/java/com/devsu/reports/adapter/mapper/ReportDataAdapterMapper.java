package com.devsu.reports.adapter.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;

import com.devsu.reports.adapter.port.ReportPersistencePort;
import com.devsu.reports.domain.model.ReportRow;
import com.devsu.reports.domain.port.ReportDataPort;

public class ReportDataAdapterMapper implements ReportDataPort {

    // Dependencia abstracta mediante inversión de dependencias
    private final ReportPersistencePort reportPersistencePort;

    public ReportDataAdapterMapper(ReportPersistencePort reportPersistencePort) {
        this.reportPersistencePort = reportPersistencePort;
    }

    @Override
    public List<ReportRow> getDatosReporte(LocalDate fechaInicio, LocalDate fechaFin, String cliente) {
        LocalDateTime inicio = (fechaInicio != null) ? fechaInicio.atStartOfDay() : null;
        LocalDateTime fin = (fechaFin != null) ? fechaFin.atTime(LocalTime.MAX) : null;

        String query = "SELECT m.fecha, cl.nombre, c.numeroCuenta, c.tipoCuenta, m.saldoInicial, c.estado, m.valor, m.saldoDisponible "
                +
                "FROM MovimientoEntity m " +
                "JOIN m.cuenta c " +
                "JOIN c.cliente cl " +
                "WHERE (:fechaInicio IS NULL OR m.fecha >= :fechaInicio) " +
                "  AND (:fechaFin IS NULL OR m.fecha <= :fechaFin) " +
                "  AND (:cliente IS NULL OR UPPER(cl.nombre) LIKE UPPER(CONCAT('%', :cliente, '%')))";

        // Llamada desacoplada a través de la interfaz
        List<Object[]> queryResults = reportPersistencePort.buscarDatosEnBaseDatos(query, inicio, fin, cliente);

        return queryResults.stream().map(result -> {
            Timestamp timestamp = (Timestamp) result[0];
            return new ReportRow(
                    timestamp.toLocalDateTime().toLocalDate(),
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    ((Number) result[4]).doubleValue(),
                    (Boolean) result[5],
                    ((Number) result[6]).doubleValue(),
                    ((Number) result[7]).doubleValue());
        }).collect(Collectors.toList());
    }
}