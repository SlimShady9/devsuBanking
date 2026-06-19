package com.devsu.reports.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.devsu.reports.adapter.port.ReportPersistencePort;

@Repository
public class JdbcReportQueryRepository implements ReportPersistencePort {

        private final JdbcTemplate jdbcTemplate;

        public JdbcReportQueryRepository(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public List<Object[]> buscarDatosEnBaseDatos(String query, LocalDateTime fechaInicio, LocalDateTime fechaFin,
                        String cliente) {

                List<Object> paramsList = new ArrayList<>();

                if (fechaInicio != null) {
                        paramsList.add(fechaInicio);
                }
                if (fechaFin != null) {
                        paramsList.add(fechaFin);
                }
                if (cliente != null) {
                        paramsList.add(cliente);
                }

                List<Map<String, Object>> filasAsMap = jdbcTemplate.queryForList(query, paramsList.toArray());

                List<Object[]> resultado = new ArrayList<>();
                for (Map<String, Object> fila : filasAsMap) {
                        Object[] filaArray = fila.values().toArray();
                        resultado.add(filaArray);
                }

                return resultado;
        }
}