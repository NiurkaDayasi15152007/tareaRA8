package pio.daw.ra8.mercado.service;

import java.util.List;
import java.util.Random;

import jakarta.persistence.EntityManager;
import pio.daw.ra8.mercado.model.Individuo;
import pio.daw.ra8.mercado.model.Intercambio;
import pio.daw.ra8.mercado.model.Simulacion;

public class SimulacionService {

    private final EntityManager em;
    private final Random random = new Random();

    public SimulacionService(EntityManager em) {
        this.em = em;
    }

    public Simulacion ejecutar(String nombre, int numIndividuos, double saldoInicial, int numRondas) {

        Simulacion sim = new Simulacion(nombre, numRondas, numIndividuos, saldoInicial);

        for (int i = 1; i <= numIndividuos; i++) {
            Individuo ind = new Individuo("Individuo_" + i, saldoInicial);
            sim.addIndividuo(ind);
        }

        em.getTransaction().begin();
        em.persist(sim);
        em.getTransaction().commit();

        List<Individuo> individuos = sim.getIndividuos();

        em.getTransaction().begin();

        for (int ronda = 0; ronda < numRondas; ronda++) {

            int idxA = random.nextInt(numIndividuos);
            int idxB;
            do {
                idxB = random.nextInt(numIndividuos);
            } while (idxB == idxA);

            Individuo individuoA = individuos.get(idxA);
            Individuo individuoB = individuos.get(idxB);

            double saldoMinimo = Math.min(individuoA.getSaldoActual(), individuoB.getSaldoActual());
            if (saldoMinimo <= 0) continue;

            double importe = 1 + random.nextDouble() * (saldoMinimo - 1);

            Individuo emisor;
            Individuo receptor;
            if (random.nextBoolean()) {
                emisor   = individuoA;
                receptor = individuoB;
            } else {
                emisor   = individuoB;
                receptor = individuoA;
            }

            emisor.setSaldoActual(emisor.getSaldoActual() - importe);
            receptor.setSaldoActual(receptor.getSaldoActual() + importe);

            Intercambio intercambio = new Intercambio(ronda + 1, importe, emisor, receptor);
            sim.addIntercambio(intercambio);
            em.persist(intercambio);

            if ((ronda + 1) % 100 == 0) {
                em.getTransaction().commit();
                em.clear(); 
                em.getTransaction().begin();

                sim = em.find(Simulacion.class, sim.getId());
                individuos = sim.getIndividuos();

                System.out.println("  Ronda " + (ronda + 1) + " completada...");
            }
        }

        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }

        System.out.println(" Simulación completada: " + sim);
        return sim;
    }
}