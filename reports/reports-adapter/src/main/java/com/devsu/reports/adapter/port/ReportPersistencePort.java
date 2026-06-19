package com.devsu.reports.adapter.port;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportPersistencePort {
    List<Object[]> buscarDatosEnBaseDatos(String query, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String cliente);
}