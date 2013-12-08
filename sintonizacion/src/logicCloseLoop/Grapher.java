package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeriesCollection;

import GUI.DataClosedZN;

public class Grapher {
	
	private XYSeriesCollection conjuntoDatos;
	private Curva curvaActual;
	private JFreeChart diagrama;
	private double t = 0.0;
	private double tiempoDeCurva;
	private Timer timer;
	private int band = 0;
	private boolean bandera;
	private JTable tabla1, tabla2;
	private JButton boton;
	private JComboBox<String> combo;
	
	public Grapher() {
		
		
		conjuntoDatos = new XYSeriesCollection();
		curvaActual = null;
		bandera = true;
		tabla1 = null;
		
		timer = new Timer (1, new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(t <= tiempoDeCurva && bandera) {
		        	curvaActual.dibujarCurva(t);
		        	if(curvaActual.getTipoCurva() != 3)
		        		t += 0.04;
		        	else
		        		t += 0.015;
		        }
		    	else {
		    		if(bandera)
			    		t = curvaActual.getPuntoXInicio();
		    			bandera = false;
		    	}
		    	
		    	if(t <= curvaActual.getStep(1) && band == 0){
		    		System.out.println(curvaActual.getStep(0));
		    		tabla1.setModel(DataClosedZN.getControllerModel(1, 1));
		    		tabla2.setModel(DataClosedZN.getKPModel(1, 1));
		    		tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
		    		band = 1;
		    	}else if( t >= curvaActual.getStep(1) && t <= curvaActual.getStep(2) && band == 1){
		    		System.out.println(curvaActual.getStep(1));
		    		tabla1.setModel(DataClosedZN.getControllerModel(2, 2));
		    		tabla2.setModel(DataClosedZN.getKPModel(2, 2));
		    		tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
		    		band = 2;
		    	}else if(t > curvaActual.getStep(2) && band == 2){
		    		System.out.println(curvaActual.getStep(2));
		    		tabla1.setModel(DataClosedZN.getControllerModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
		    		tabla2.setModel(DataClosedZN.getKPModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
		    		tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
		    		band = 3;
		    	}
		    	
		    	
	    		if(t <= curvaActual.getPuntoXFin() && !bandera) {
	    			curvaActual.dibujarPeriodo(t);
	    			if(curvaActual.getTipoCurva() != 3)
	    				t += 0.03;
	    			else
	    				t += 0.01;
	    		}
	    		else if(!bandera){
    				//tabla1.setModel(DataClosedZN.getControllerModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
					//tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
					//tabla2.setModel(DataClosedZN.getKPModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
	    			//tabla2.getColumnModel().getColumn(0).setPreferredWidth(51);
	    			//tabla2.getColumnModel().getColumn(1).setPreferredWidth(51);
	    			
					boton.setEnabled(true);
					combo.setEnabled(true);
					stop();
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

	public void insertarCurva(int curva) {
		limpiar();
		switch(curva) {
		case 1:	curvaActual = new Curva1(); tiempoDeCurva = 30.0; break;
		case 2: curvaActual = new Curva2(); tiempoDeCurva = 37.5; break;
		case 3: curvaActual = new Curva3(); tiempoDeCurva = 12.0; break;
		}
		Curva.generarEntrada(tiempoDeCurva);
		conjuntoDatos.addSeries(Curva.getEntrada());
		conjuntoDatos.addSeries(curvaActual.getDibujoCurva());
		conjuntoDatos.addSeries(curvaActual.getDibujoPeriodo());
	}
	
	public void iniciarGraficoCurva(JTable tabla1, JTable tabla2, JButton boton, JComboBox<String> combo) { 
		this.tabla1 = tabla1;
		this.tabla2 = tabla2;
		this.boton = boton;
		this.combo = combo;
		band = 0;
		diagrama.addSubtitle(curvaActual.getComentario());
		for(ValueMarker marcador : curvaActual.getMarcadores())
			diagrama.getXYPlot().addDomainMarker(marcador);
		timer.stop();
		timer.start();
	}
	
	public Curva getCurvaActual() {
		return curvaActual;
	}

	public void limpiar() {
		curvaActual = null;
		conjuntoDatos.removeAllSeries();
		t = 0.0;
		bandera = true;
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void start() {
		timer.start();
	}
	
	public void clean() {
		curvaActual = null;
		conjuntoDatos.removeAllSeries();
		t = 0;
		//timerCurve = null;
		//resetTimer();
	}
	
	public void getImage() throws IOException{
		
		JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showSaveDialog(fc);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            
            OutputStream in= new FileOutputStream(file.getPath()+".png");
            ChartUtilities.writeChartAsPNG(in,diagrama, 800,600);
            
            
        }
		
	}
}
