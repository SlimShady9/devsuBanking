package com.devsu.reports.domain.model;

import java.time.LocalDate;

public class ReportRow {
    private final LocalDate fecha;
    private final String cliente;
    private final String numeroCuenta;
    private final String tipo;
    private final double saldoInicial;
    private final boolean estado;
    private final double movimiento;
    private final double saldoDisponible;

    public ReportRow(LocalDate fecha, String cliente, String numeroCuenta, String tipo,
            double saldoInicial, boolean estado, double movimiento, double saldoDisponible) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public double getMovimiento() {
        return movimiento;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }
}