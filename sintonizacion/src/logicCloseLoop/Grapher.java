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
import javax.swing.filechooser.FileFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeriesCollection;

import GUI.DataClosedZN;
import GUI.PanelClosedZieglerNichols;

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
	private PanelClosedZieglerNichols view;
	
	public Grapher(final PanelClosedZieglerNichols view) {
		
		this.view = view;
		conjuntoDatos = new XYSeriesCollection();
		curvaActual = null;
		bandera = true;
		tabla1 = null;
		
		
		timer = new Timer (0, new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		    	if(t <= tiempoDeCurva && bandera) {
		        	curvaActual.dibujarCurva(t);
		        	if(curvaActual.getTipoCurva() != 3)
		        		t += 0.03;
		        	else
		        		t += 0.01;
		        }
		    	else {
		    		if(bandera)
			    		t = curvaActual.getPuntoXInicio();
		    			bandera = false;
		    	}
		    	
		    	if(t <= curvaActual.getStep(1) && band == 0){
		    		/*System.out.println(curvaActual.getStep(0));
		    		tabla1.setModel(DataClosedZN.getControllerModel(curvaActual.getKtest(0), curvaActual.getKtest(0)));
		    		tabla2.setModel(DataClosedZN.getTransientKPModel(curvaActual.getKtest(0), curvaActual.getKtest(0)));
		    		tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);*/
		    		band = 1;
		    	}else if( t >= curvaActual.getStep(1) && t <= curvaActual.getStep(2) && band == 1){
		    		curvaActual.setComment(1);
		    		/*System.out.println(curvaActual.getStep(1));
		    		tabla1.setModel(DataClosedZN.getControllerModel(curvaActual.getKtest(1), curvaActual.getKtest(1)));
		    		tabla2.setModel(DataClosedZN.getTransientKPModel(curvaActual.getKtest(1), curvaActual.getKtest(1)));
		    		tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);*/
		    		band = 2;
		    	}else if(t > curvaActual.getStep(2) && band == 2){
		    		curvaActual.setComment(2);
		    		band = 3;
		    	}
		    	
	    		if(t <= curvaActual.getPuntoXFin() && !bandera) {
	    			
	    			curvaActual.dibujarPeriodo(t);
	    			
	    			if(curvaActual.getTipoCurva() != 3)
	    				t += 0.03;
	    			else
	    				t += 0.03;
	    		}
	    		else if(!bandera && t<tiempoDeCurva){
	    			
	    			tabla2.setModel(DataClosedZN.getKPModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
	    			
	    			boton.setEnabled(true);
	    			combo.setEnabled(true);
	    			
	    			timer.setDelay(500);
	    			
	    			
	    			//loader += "|||||||";
	    			//view.getProgress().setText(loader);
	    			
	    			band += 1;
	    			
	    			
				}
	    		if(band == 14){
	    			
	    			System.out.println("STOP");
	    			tabla1.setModel(DataClosedZN.getControllerModel(curvaActual.getKCritico(), curvaActual.getPCritico()));
	    			tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
	    			
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
		diagrama.addSubtitle(curvaActual.getComment());
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
	
	public void getImage() throws IOException{
		
		JFileChooser fc = new JFileChooser();
		
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "*.png, *.jpg";
			}
			@Override
			public boolean accept(File f) {
				
				return (f.isDirectory()||f.getName().endsWith(".png")||f.getName().endsWith(".jpg"));
			}
		});
		stop();

		String fileDefaultName = "Graphic"+  System.currentTimeMillis() / 1000L;
		
		File defaultFile = new File(fileDefaultName);
		
		fc.setSelectedFile(defaultFile);
		int returnVal = fc.showSaveDialog(fc);
		start();
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println(fc.getSelectedFile().getPath());
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            
            OutputStream in= new FileOutputStream(file.getPath()+".png");
            ChartUtilities.writeChartAsPNG(in,diagrama, 800,600);
            
        }
		
	}
	private static double round(double numero) {
		return (Math.rint(numero*100)/100);
	}
}
