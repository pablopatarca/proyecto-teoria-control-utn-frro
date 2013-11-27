package logicaLazoAbierto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficadorPrueba {
	
	private XYSeriesCollection conjuntoDatos;
	private CurvaPrueba1 curvaActual;
	private JFreeChart diagrama;
	double t = 0.0;
	private double tiempoDeCurva;
	private Timer timer;
	private boolean bandera;
	
	public GraficadorPrueba() {
		conjuntoDatos = new XYSeriesCollection();
		curvaActual = null;
		bandera = true;
		timer = new Timer(1, new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(t <= tiempoDeCurva && bandera) {
		        	curvaActual.dibujarCurva(t);
		        		t += 0.04;
		        }
		    	else {
		    		if(bandera)
			    		t = curvaActual.getPuntoXInicio();
		    		bandera = false;
		    	}
	    		if(t <= curvaActual.getPuntoXFin() && !bandera) {
	    			curvaActual.dibujarPeriodo(t);
	    				t += 0.03;
	    		}
		     }
		});
	}
	
	public JPanel getDiagrama() {
		diagrama = null;
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
		diagrama.getXYPlot().getRenderer().setSeriesStroke(2, new BasicStroke(1.5f));
		diagrama.getXYPlot().getRenderer().setSeriesStroke(3, new BasicStroke(5f));
		diagrama.getXYPlot().getRenderer().setSeriesStroke(4, new BasicStroke(2.5f));
		if(curvaActual != null) {
			diagrama.getXYPlot().getRangeAxis().setLowerBound(curvaActual.getPuntoYInicio());
			diagrama.getXYPlot().getRangeAxis().setUpperBound(curvaActual.getPuntoYFin());
		}
		diagrama.getXYPlot().getRenderer().setSeriesPaint(3, Color.BLACK);
		diagrama.getXYPlot().getRenderer().setSeriesPaint(4, Color.BLACK);
		return new ChartPanel(diagrama);
	}

	
	
	
	public void insertarCurva() {
		limpiar();
		//TODO: Insertar valores K y Tau
		double k = 5;
		double tau = 2;
		curvaActual = new CurvaPrueba1(k, tau); 
		tiempoDeCurva = tau*6;
		
		curvaActual.generarEntrada(tiempoDeCurva);
		
		//curvaActual.graphI();
		
		
		//conjuntoDatos.addSeries(curvaActual.getEntrada());
		conjuntoDatos.addSeries(curvaActual.getDibujoCurva());
		conjuntoDatos.addSeries(curvaActual.getDibujoDerivada());
		conjuntoDatos.addSeries(curvaActual.getDibujoK());
		conjuntoDatos.addSeries(curvaActual.getdibujoLY());
		conjuntoDatos.addSeries(curvaActual.getdibujoTY());
		conjuntoDatos.addSeries(curvaActual.getdibujoLX());
		conjuntoDatos.addSeries(curvaActual.getdibujoTX());
		
		
		
		
		
		/*
		conjuntoDatos.addSeries(CurvaPrueba.getEntrada());
		conjuntoDatos.addSeries(curvaActual.getDibujoCurva());
		conjuntoDatos.addSeries(curvaActual.getDibujoPeriodo());
		*/
	}
	
	
	public void iniciarGraficoCurva() {
		
		//diagrama.addSubtitle(curvaActual.getComentario());
		
		for(ValueMarker marcador : curvaActual.getMarcadores())
			diagrama.getXYPlot().addDomainMarker(marcador);
		timer.stop();
		timer.start();
		
	}
	
	public CurvaPrueba1 getCurvaActual() {
		return curvaActual;
	}

	public void limpiar() {
		curvaActual = null;
		conjuntoDatos.removeAllSeries();
		t = 0.0;
		bandera = true;
	}
	
	public void pararTimer() {
		timer.stop();
	}
}
