package pio.daw.ra8.mercado.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import pio.daw.ra8.mercado.model.Individuo;

import java.util.List;

public class GraficaDistribucion {

    public static void mostrar(List<Individuo> individuos, String titulo) {

        double[] saldos = new double[individuos.size()];
        for (int i = 0; i < individuos.size(); i++) {
            saldos[i] = individuos.get(i).getSaldoActual();
        }

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Individuos", saldos, 10);

        JFreeChart grafica = ChartFactory.createHistogram(
            titulo,                     
            "Saldo final (u.m.)",       
            "Número de individuos",     
            dataset,
            PlotOrientation.VERTICAL,
            false,                    
            true,                       
            false                      
        );

        ChartFrame frame = new ChartFrame(titulo, grafica);
        frame.pack();
        frame.setVisible(true);

        System.out.println(" Gráfica mostrada correctamente.");
    }
}