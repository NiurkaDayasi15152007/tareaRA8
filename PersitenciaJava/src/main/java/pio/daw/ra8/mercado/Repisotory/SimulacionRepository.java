package pio.daw.ra8.mercado.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import pio.daw.ra8.mercado.model.Individuo;
import pio.daw.ra8.mercado.model.Intercambio;
import pio.daw.ra8.mercado.model.Simulacion;

public class SimulacionRepository {

    private final EntityManager em;

    public SimulacionRepository(EntityManager em) {
        this.em = em;
    }


    public List<Individuo> rankingIndividuos(Simulacion sim) {
        return em.createQuery(
            "SELECT i FROM Individuo i WHERE i.simulacion = :sim " +
            "ORDER BY i.saldoActual DESC", Individuo.class)
            .setParameter("sim", sim)
            .getResultList();
    }


    public Individuo masRico(Simulacion sim) {
        return em.createQuery(
            "SELECT i FROM Individuo i WHERE i.simulacion = :sim " +
            "ORDER BY i.saldoActual DESC", Individuo.class)
            .setParameter("sim", sim)
            .setMaxResults(1)
            .getSingleResult();
    }


    public Individuo masPobre(Simulacion sim) {
        return em.createQuery(
            "SELECT i FROM Individuo i WHERE i.simulacion = :sim " +
            "ORDER BY i.saldoActual ASC", Individuo.class)
            .setParameter("sim", sim)
            .setMaxResults(1)
            .getSingleResult();
    }


    public double saldoMedio(Simulacion sim) {
        return em.createQuery(
            "SELECT AVG(i.saldoActual) FROM Individuo i WHERE i.simulacion = :sim",
            Double.class)
            .setParameter("sim", sim)
            .getSingleResult();
    }


    public double saldoMaximo(Simulacion sim) {
        return em.createQuery(
            "SELECT MAX(i.saldoActual) FROM Individuo i WHERE i.simulacion = :sim",
            Double.class)
            .setParameter("sim", sim)
            .getSingleResult();
    }


    public double saldoMinimo(Simulacion sim) {
        return em.createQuery(
            "SELECT MIN(i.saldoActual) FROM Individuo i WHERE i.simulacion = :sim",
            Double.class)
            .setParameter("sim", sim)
            .getSingleResult();
    }


    public long contarRicos(Simulacion sim) {
        return em.createQuery(
            "SELECT COUNT(i) FROM Individuo i WHERE i.simulacion = :sim " +
            "AND i.saldoActual > (i.saldoInicial * 0.5)", Long.class)
            .setParameter("sim", sim)
            .getSingleResult();
    }


    public List<Intercambio> top10Intercambios(Simulacion sim) {
        return em.createQuery(
            "SELECT i FROM Intercambio i WHERE i.simulacion = :sim " +
            "ORDER BY i.importe DESC", Intercambio.class)
            .setParameter("sim", sim)
            .setMaxResults(10)
            .getResultList();
    }
}