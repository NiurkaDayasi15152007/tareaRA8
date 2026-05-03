package pio.daw.ra8.mercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Intercambio {

    @Id
    @GeneratedValue
    private long id;

    private int numRonda;
    private double importe;

    @ManyToOne
    private Individuo emisor;

    @ManyToOne
    private Individuo receptor;

    @ManyToOne
    private Simulacion simulacion;

    public Intercambio() {
    }

    public Intercambio(int numRonda, double importe, Individuo emisor, Individuo receptor) {
        this.numRonda = numRonda;
        this.importe = importe;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public long getId() {
        return id;
    }

    public int getNumRonda() {
        return numRonda;
    }

    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Individuo getEmisor() {
        return emisor;
    }

    public void setEmisor(Individuo emisor) {
        this.emisor = emisor;
    }

    public Individuo getReceptor() {
        return receptor;
    }

    public void setReceptor(Individuo receptor) {
        this.receptor = receptor;
    }

    public Simulacion getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(Simulacion sim) {
        this.simulacion = sim;
    }

    @Override
    public String toString() {
        return "Intercambio{ronda=" + numRonda
                + ", importe=" + importe
                + ", emisor=" + emisor.getNombre()
                + ", receptor=" + receptor.getNombre() + "}";
    }
}
