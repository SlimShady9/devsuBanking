package com.devsu.reports.adapter.port;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportPersistencePort {
    List<Object[]> getDataBaseData(String query, LocalDateTime initDate, LocalDateTime endDate,
            String customer);
}