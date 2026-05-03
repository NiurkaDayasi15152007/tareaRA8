package pio.daw.ra8.mercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Individuo {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private double saldoActual;
    private double saldoInicial;

    @ManyToOne
    private Simulacion simulacion;

    public Individuo() {
    }

    public Individuo(String nombre, double saldoInicial) {
        this.nombre = nombre;
        this.saldoInicial = saldoInicial;
        this.saldoActual = saldoInicial; 
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Simulacion getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(Simulacion simulacion) {
        this.simulacion = simulacion;
    }

    @Override
    public String toString() {
        return "Individuo{id=" + id
                + ", nombre='" + nombre + "'"
                + ", saldoActual=" + saldoActual + "}";
    }
}
