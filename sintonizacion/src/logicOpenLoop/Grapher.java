package logicOpenLoop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import GUI.MethodPanelView;

public class Grapher {
	
	private XYSeriesCollection conjuntoDatos;
	private CurveGenerator curvaActual;
	private JFreeChart diagrama;
	double t = 0;
	private double curveTime = 0.0;
	private double kPlant = 0.0;
	private double systemTime = curveTime*7;
	private Timer timerCurve;
	int flag = 0;
	public MethodPanelView globalPanelView;
	private Grapher graph = this;
	
public Grapher() {
		
		conjuntoDatos = new XYSeriesCollection();
		resetTimer();
	}
	
	
	public Grapher(MethodPanelView view) {
		
		globalPanelView = view;
		
		conjuntoDatos = new XYSeriesCollection();
		resetTimer();
	}

	public JPanel getDiagrama() {
		
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
		 XYItemRenderer renderer = plot.getRenderer();
	        renderer.setSeriesPaint(0, Color.WHITE);
	        renderer.setSeriesPaint(1, Color.RED);
	        renderer.setSeriesPaint(2, Color.BLUE);
	        renderer.setSeriesPaint(3, Color.YELLOW);
	        renderer.setSeriesPaint(4, Color.GREEN);
	        renderer.setSeriesPaint(5, Color.BLACK);
	        renderer.setSeriesPaint(6, Color.BLACK);
		
		//Defino Valor maximo y minimo de eje Y
		if(curvaActual != null) {
			diagrama.getXYPlot().getRangeAxis().setLowerBound(curvaActual.getPuntoYInicio());
			diagrama.getXYPlot().getRangeAxis().setUpperBound(curvaActual.getPuntoYFin());
		}
		
		return new ChartPanel(diagrama);
	}
	
	public void resetTimer(){
		
		flag = 0;
		t = 0;
		
		timerCurve = new Timer(20, new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	//Draw curve
		    	if(t <= curveTime*7 && flag==0) {
		    		curvaActual.dibujarK(t);
		        	
		        		t += 0.5;
		        }else{
		    		if(flag==0){
		    			t = 0;
		    			flag = 1;
		    		}
		    	}
		    	
		    	if(t < curveTime*7 && flag==1) {
		    		curvaActual.dibujarCurva(t);
	    			t += 0.05;
	    		}else{
	    			if(flag==1){
	    				t=0;
	    				flag = 2;
	    			}
	    		}
		    	//Draw Tangent
		    	if(t < curveTime*7 && flag==2){

	    			curvaActual.dibujarTangente(t);
	    			t += 0.2;
	    			
		    	}else{
		    		if(flag==2){
		    			t=0;
	    				flag = 3;
	    				//Add dead times markers
	    				for(ValueMarker marcador : curvaActual.getMarcadoresCurva()){
		    				diagrama.getXYPlot().addDomainMarker(marcador);}
		    		}
		    	}
		    	//Draw L and T
		    	if(t < curvaActual.getRealT() && flag==3){
		    		
		    		curvaActual.dibujaLT(t);
		    		t += 0.05;
		    	
		    	}else{
		    		if(flag==3){
		    			t=0;
	    				flag = 4;
		    		}
		    	}
		    	
		    	if(flag==4){
		    		
		    		globalPanelView.completeTables(graph, kPlant, curveTime);
		    				    		
		    	}else{
		    		if(flag==4){
		    			t=0;
	    				flag = 5;
		    		}
		    	}
	    		
		     }
		});
	}

	
	
	
	public void insertCurve(double k, double tau) {
		
		curveTime = tau;
		kPlant = k;
		
		curvaActual = new CurveGenerator(k, tau); 
		
		
		//curvaActual.generarEntrada(curveTime);
		
		conjuntoDatos.addSeries(curvaActual.getDibujoK());
		conjuntoDatos.addSeries(curvaActual.getDibujoCurva());
		conjuntoDatos.addSeries(curvaActual.getDibujoTangente());
		conjuntoDatos.addSeries(curvaActual.getTiempoMuerto());
		conjuntoDatos.addSeries(curvaActual.getRetardo());
		
	}
	
	public void insertarDerivada(){
		//conjuntoDatos.addSeries(curvaActual.getTangente());
	}
	
	//Inicia a graficar valores curva
	public void iniciarGraficoCurva() {
		
		//diagrama.addSubtitle(curvaActual.getComentario());
		
		diagrama.getXYPlot().getRangeAxis().setLowerBound(-0.5);
		
		timerCurve.stop();
		timerCurve.start();
			
	}

	
	public CurveGenerator getCurvaActual() {
		return curvaActual;
	}

	public void clean() {
		curvaActual = null;
		conjuntoDatos.removeAllSeries();
		t = 0;
		timerCurve = null;
		resetTimer();
	}
	
	public void stop() {
		timerCurve.stop();
	}
	public void start() {
		timerCurve.start();
	}
	
	public void getImage() throws IOException{
		
		JFileChooser fc = new JFileChooser();
		stop();
		int returnVal = fc.showSaveDialog(fc);
		start();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            
            OutputStream in= new FileOutputStream(file.getPath()+".png");
            ChartUtilities.writeChartAsPNG(in,diagrama, 800,600);
            
        }
		
	}
	
}
