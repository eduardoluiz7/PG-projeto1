package projetopg.eduardo;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Classe que implementa o gr�fico da aplica��o
 */
public class Grafico extends javax.swing.JFrame{
	
	private static final long serialVersionUID = 1L;
	private XYDataset dataxy;
	XYSeries grafico;
	JFreeChart chart;
	public Grafico() {
		
		grafico = new XYSeries("res");
		//calcula e adiciona valores da norma acumulada dos vetores 
		//resultantes da derivada ao gr�fico para cada valor de t
		grafico.add(0.001, TelaCurvas.getCurva().derivadaDeBezier(0.001));
		grafico.add(0.05, TelaCurvas.getCurva().derivadaDeBezier(0.05));
		grafico.add(0.08, TelaCurvas.getCurva().derivadaDeBezier(0.08));
		grafico.add(0.1, TelaCurvas.getCurva().derivadaDeBezier(0.1));
		grafico.add(0.2, TelaCurvas.getCurva().derivadaDeBezier(0.2));
		grafico.add(0.3, TelaCurvas.getCurva().derivadaDeBezier(0.3));
		grafico.add(0.4, TelaCurvas.getCurva().derivadaDeBezier(0.4));
		grafico.add(0.5, TelaCurvas.getCurva().derivadaDeBezier(0.5));
		grafico.add(1, TelaCurvas.getCurva().derivadaDeBezier(1));
		
		/*
		grafico.add(0.8, TelaCurvas.getCurva().derivadaDeBezier(0.8));
		grafico.add(0.9, TelaCurvas.getCurva().derivadaDeBezier(0.9));
		**/
		
		dataxy = new XYSeriesCollection(grafico);
		chart = ChartFactory.createXYLineChart("Norma acumulada em fun��o de t", "Valores de t", "Norma acumulada", dataxy, 
				PlotOrientation.VERTICAL, false, false, false);

		ChartPanel cp = new ChartPanel(chart) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 700);
            }
        };

        cp.setMouseWheelEnabled(true);
        cp.setMouseZoomable(true);
        add(cp);
        pack();
        setTitle("Gr�fico");
		
	}
	
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Grafico().setVisible(true);
            }
        });
    }

}
