package logicaLazoAbierto;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficador {
	
	private XYSeriesCollection conjuntoDatos;
	private Curva curvaActual;
	
	public Graficador() {
		conjuntoDatos = new XYSeriesCollection();
		curvaActual = null;
	}
	
	public JPanel getDiagrama() {
		JFreeChart diagrama = null;
		diagrama = ChartFactory.createXYLineChart(
                "", //Titulo Grafica
                "t", // Leyenda eje X
                "Y(t)", // Leyenda eje Y
                conjuntoDatos, // Los datos
                PlotOrientation.VERTICAL, //orientacion
                false, // ver titulo de linea
                false, //tooltips
                false  //URL
				);
		XYPlot plot = diagrama.getXYPlot();
		if(curvaActual != null)
			plot.getRangeAxis().setLowerBound(-0.05*(curvaActual.getk()));
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.WHITE);
        renderer.setSeriesPaint(3, Color.BLACK);
        renderer.setSeriesPaint(4, Color.BLACK);
        renderer.setSeriesPaint(5, Color.YELLOW);
        renderer.setSeriesPaint(6, Color.GREEN);
		return new ChartPanel(diagrama);
	}

	
	//Genera los datos para graficar
	public void insertarCurva(double k, double tau) {
		limpiar();
		curvaActual = new Curva(k, tau);
		conjuntoDatos.addSeries(curvaActual.getDibujoCurva());
		conjuntoDatos.addSeries(curvaActual.getDibujoDerivada());
		conjuntoDatos.addSeries(curvaActual.getDibujoK());
		conjuntoDatos.addSeries(curvaActual.getdibujoLY());
		conjuntoDatos.addSeries(curvaActual.getdibujoTY());
		conjuntoDatos.addSeries(curvaActual.getdibujoLX());
		conjuntoDatos.addSeries(curvaActual.getdibujoTX());
	}
	
	public Curva getCurvaActual() {
		return curvaActual;
	}

	public void limpiar() {
		curvaActual = null;
		conjuntoDatos.removeAllSeries();
	}
}
