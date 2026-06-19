package com.devsu.reports.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.reports.adapter.mapper.ReportDataAdapterMapper;
import com.devsu.reports.adapter.port.ReportPersistencePort;
import com.devsu.reports.domain.port.ReportDataPort;
import com.devsu.reports.usecase.GenerateReportUseCase;

@Configuration
public class BeanConfiguration {

    @Bean
    public ReportDataPort reportDataPort(ReportPersistencePort reportPersistencePort) {
        return new ReportDataAdapterMapper(reportPersistencePort);
    }

    @Bean
    public GenerateReportUseCase generateReportUseCase(ReportDataPort reportDataPort) {
        return new GenerateReportUseCase(reportDataPort);
    }
}
