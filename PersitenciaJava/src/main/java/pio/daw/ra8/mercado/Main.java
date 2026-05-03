package pio.daw.ra8.mercado;

import jakarta.persistence.*;
import pio.daw.ra8.mercado.model.*;
import pio.daw.ra8.mercado.repository.SimulacionRepository;
import pio.daw.ra8.mercado.service.SimulacionService;
import pio.daw.ra8.mercado.ui.GraficaDistribucion;
import pio.daw.ra8.util.JPAUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = JPAUtil.crearEMFWithoutDelete("mercado.odb");
        EntityManager em = emf.createEntityManager();

        try {
            String nombre = "Simulacion Mercado Libre";
            int numIndividuos = 100;
            double saldoInicial = 100.0;
            int numRondas = 10000;

            System.out.println(" Iniciando simulación...");
            SimulacionService service = new SimulacionService(em);
            Simulacion sim = service.ejecutar(nombre, numIndividuos, saldoInicial, numRondas);

            System.out.println("\n ── RESULTADOS ──────────────────────────────");
            SimulacionRepository repo = new SimulacionRepository(em);

            List<Individuo> ranking = repo.rankingIndividuos(sim);
            System.out.println("\n TOP 5 más ricos:");
            ranking.stream().limit(5).forEach(System.out::println);

            System.out.println("\n TOP 5 más pobres:");
            ranking.stream().skip(ranking.size() - 5).forEach(System.out::println);

            Individuo masRico = repo.masRico(sim);
            Individuo masPobre = repo.masPobre(sim);
            System.out.println("\n Más rico:  " + masRico);
            System.out.println(" Más pobre: " + masPobre);

            System.out.printf("%n Saldo medio:   %.2f u.m.%n", repo.saldoMedio(sim));
            System.out.printf(" Saldo máximo:  %.2f u.m.%n", repo.saldoMaximo(sim));
            System.out.printf(" Saldo mínimo:  %.2f u.m.%n", repo.saldoMinimo(sim));

            long ricos = repo.contarRicos(sim);
            System.out.println("\n Individuos con más del 50% del saldo inicial: " + ricos
                    + " de " + numIndividuos);

            System.out.println("\n Top 10 intercambios de mayor importe:");
            repo.top10Intercambios(sim).forEach(System.out::println);

            GraficaDistribucion.mostrar(ranking, "Distribución de Riqueza — " + nombre);

        } finally {
            em.close();
            JPAUtil.cerrar(emf);
        }
    }
}
