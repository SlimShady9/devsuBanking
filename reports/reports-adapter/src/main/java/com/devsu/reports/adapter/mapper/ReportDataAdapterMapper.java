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
    public List<ReportRow> getReportData(LocalDate initDate, LocalDate endDate, String customer) {
        LocalDateTime initDateTime = (initDate != null) ? initDate.atStartOfDay() : null;
        LocalDateTime endDateTime = (endDate != null) ? endDate.atTime(LocalTime.MAX) : null;
        StringBuilder query = new StringBuilder(
                "SELECT sub.fecha, sub.cliente, sub.numero_cuenta, sub.tipo, " +
                        "       sub.initialBalance, sub.estado, sub.movimiento, sub.balance " +
                        "FROM (" +
                        "  SELECT m.creation_date AS fecha, " +
                        "         p.name AS cliente, " +
                        "         a.account_number AS numero_cuenta, " +
                        "         a.account_type AS tipo, " +
                        "         a.state AS estado, " +
                        "         m.amount AS movimiento, " +
                        // 1. Calculate the true rolling balance up to this point using all movements
                        "         (a.balance - " +
                        "          SUM(m.amount) OVER(PARTITION BY m.account_id) + " +
                        "          SUM(m.amount) OVER(PARTITION BY m.account_id ORDER BY m.creation_date ASC, m.id ASC ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)"
                        +
                        "         ) AS balance, " +
                        // 2. Initial balance is simply the current calculated balance minus this
                        // specific movement
                        "         (a.balance - " +
                        "          SUM(m.amount) OVER(PARTITION BY m.account_id) + " +
                        "          SUM(m.amount) OVER(PARTITION BY m.account_id ORDER BY m.creation_date ASC, m.id ASC ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) - m.amount"
                        +
                        "         ) AS initialBalance " +
                        "  FROM public.movement m " +
                        "  JOIN public.account a ON m.account_id = a.id " +
                        "  JOIN public.customer c ON a.customer_id = c.id " +
                        "  JOIN public.person p ON c.id = p.id " +
                        ") sub " +
                        "WHERE 1=1 ");

        List<Object> paramsList = new ArrayList<>();

        if (initDate != null) {
            query.append("AND sub.fecha >= ? ");
            paramsList.add(initDate);
        }
        if (endDate != null) {
            query.append("AND sub.fecha <= ? ");
            paramsList.add(endDate);
        }
        if (customer != null && !customer.trim().isEmpty()) {
            query.append("AND sub.cliente ILIKE ? ");
            paramsList.add("%" + customer + "%");
        }

        query.append("ORDER BY sub.fecha ASC");

        List<Object[]> queryResults = reportPersistencePort.getDataBaseData(query.toString(), initDateTime,
                endDateTime,
                customer);

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