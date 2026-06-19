package com.devsu.reports.domain.port;

import java.time.LocalDate;
import java.util.List;

import com.devsu.reports.domain.model.ReportRow;

public interface ReportDataPort {
    List<ReportRow> getReportData(LocalDate initDate, LocalDate endDate, String customer);
}