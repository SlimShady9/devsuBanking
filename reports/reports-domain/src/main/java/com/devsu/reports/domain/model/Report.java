package com.devsu.reports.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private final LocalDate initDate;
    private final LocalDate endDate;
    private final String customer;
    private final List<ReportRow> rows;

    public Report(LocalDate initDate, LocalDate endDate, String customer, List<ReportRow> rawData) {
        this.initDate = initDate;
        this.endDate = endDate;
        this.customer = customer;
        this.rows = filterAndProcess(rawData);
    }

    private List<ReportRow> filterAndProcess(List<ReportRow> rawData) {
        return rawData.stream()
                .filter(row -> initDate == null || !row.getDate().isBefore(initDate))
                .filter(row -> endDate == null || !row.getDate().isAfter(endDate))
                .filter(row -> customer == null || row.getCustomer().equalsIgnoreCase(customer))
                .collect(Collectors.toList());
    }

    public List<ReportRow> getRows() {
        return rows;
    }
}