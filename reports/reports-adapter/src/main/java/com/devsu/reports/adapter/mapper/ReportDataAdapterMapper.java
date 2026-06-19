package com.devsu.reports.adapter.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        StringBuilder query = new StringBuilder(
                "SELECT m.creation_date AS fecha, " +
                        "       p.name AS cliente, " +
                        "       a.account_number AS numero_cuenta, " +
                        "       a.account_type AS tipo, " +
                        "       a.balance AS saldo_actual, " +
                        "       a.state AS estado, " +
                        "       m.amount AS movimiento " +
                        "FROM public.movement m " +
                        "JOIN public.account a ON m.account_id = a.id " +
                        "JOIN public.customer c ON a.customer_id = c.id " +
                        "JOIN public.person p ON c.id = p.id " +
                        "WHERE 1=1 ");

        List<Object> paramsList = new ArrayList<>();

        if (fechaInicio != null) {
            query.append("AND m.creation_date >= ? ");
            paramsList.add(fechaInicio);
        }
        if (fechaFin != null) {
            query.append("AND m.creation_date <= ? ");
            paramsList.add(fechaFin);
        }
        if (cliente != null && !cliente.trim().isEmpty()) {
            query.append("AND p.name ILIKE ? ");
            paramsList.add("%" + cliente + "%");
        }

        query.append("ORDER BY m.creation_date DESC");

        List<Object[]> queryResults = reportPersistencePort.buscarDatosEnBaseDatos(query.toString(), inicio, fin,
                cliente);

        return queryResults.stream().map(result -> {
            Timestamp timestamp = (Timestamp) result[0];
            return new ReportRow(
                    timestamp.toLocalDateTime().toLocalDate(),
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    ((Number) result[4]).doubleValue(),
                    (Boolean) result[5],
                    ((Number) result[6]).doubleValue());
        }).collect(Collectors.toList());
    }
}