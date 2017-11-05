package projetopg.eduardo;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico extends javax.swing.JFrame{
	private XYDataset dataxy;
	XYSeries grafico;
	JFreeChart chart;
	public Grafico() {
		grafico = new XYSeries("res");
		grafico.add(10.0, 11.0);
		grafico.add(5, 9.0);
		dataxy = new XYSeriesCollection(grafico);
		chart = ChartFactory.createXYLineChart("Norma Acumulada em função de t", "Valores de t", "Norma acumulada", dataxy, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel cp = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 300);
            }
        };
        
        add(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
		
	}
	
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Grafico().setVisible(true);
            }
        });
    }
	public void desenhaGrafico() {
		
	}
}
