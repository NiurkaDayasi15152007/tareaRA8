package pio.daw.ra8.mercado.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Simulacion {

    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private int numRondas;
    private int numIndividuos;
    private double saldoInicial;

    @OneToMany(mappedBy = "simulacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Individuo> individuos = new ArrayList<>();

    @OneToMany(mappedBy = "simulacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Intercambio> intercambios = new ArrayList<>();

    public Simulacion() {
    }

    public Simulacion(String nombre, int numRondas, int numIndividuos, double saldoInicial) {
        this.nombre = nombre;
        this.numRondas = numRondas;
        this.numIndividuos = numIndividuos;
        this.saldoInicial = saldoInicial;
    }

    public void addIndividuo(Individuo ind) {
        individuos.add(ind);
        ind.setSimulacion(this);
    }

    public void addIntercambio(Intercambio inter) {
        intercambios.add(inter);
        inter.setSimulacion(this);
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

    public int getNumRondas() {
        return numRondas;
    }

    public void setNumRondas(int numRondas) {
        this.numRondas = numRondas;
    }

    public int getNumIndividuos() {
        return numIndividuos;
    }

    public void setNumIndividuos(int n) {
        this.numIndividuos = n;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double s) {
        this.saldoInicial = s;
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public List<Intercambio> getIntercambios() {
        return intercambios;
    }

    @Override
    public String toString() {
        return "Simulacion{id=" + id
                + ", nombre='" + nombre + "'"
                + ", rondas=" + numRondas
                + ", individuos=" + numIndividuos
                + ", saldoInicial=" + saldoInicial + "}";
    }
}
