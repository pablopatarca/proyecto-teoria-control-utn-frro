package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficador {
	
	private XYSeriesCollection conjuntoDatos;
	private Curva curvaActual;
	private JFreeChart diagrama;
	double t = 0.0;
	private double tiempoDeCurva;
	private Timer timer;
	private boolean bandera;
	private JTable tabla1, tabla2;
	private JButton boton;
	private JComboBox<String> combo;
	
	public Graficador() {
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
	    		if(t <= curvaActual.getPuntoXFin() && !bandera) {
	    			curvaActual.dibujarPeriodo(t);
	    			if(curvaActual.getTipoCurva() != 3)
	    				t += 0.03;
	    			else
	    				t += 0.01;
	    		}
	    		else if(!bandera){
    				tabla1.setModel(new DefaultTableModel(
							new Object[][] {
									{"P", 0.5*curvaActual.getKCritico(), 0.0, 0.0},
									{"PI", 0.45*curvaActual.getKCritico(), (1.0/1.2)*curvaActual.getPCritico(), 0.0},
									{"PID", 0.6*curvaActual.getKCritico(), 0.5*curvaActual.getPCritico(), 0.125*curvaActual.getPCritico()},
							},
							new String[] {
								"Tipo de controlador", "Kp", "Ti", "Td"
							}
						) {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
							Class[] columnTypes = new Class[] {
								String.class, Double.class, Double.class, Double.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
								false, false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						tabla1.getColumnModel().getColumn(0).setPreferredWidth(106);
						tabla2.setModel(new DefaultTableModel(
								new Object[][] {
									{curvaActual.getKCritico(), curvaActual.getPCritico()},
								},
								new String[] {
									"K cr\u00EDtica", "P cr\u00EDtico"
								}
							) {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;
								Class[] columnTypes = new Class[] {
									Double.class, Double.class
								};
								public Class getColumnClass(int columnIndex) {
									return columnTypes[columnIndex];
								}
								boolean[] columnEditables = new boolean[] {
									false, false
								};
								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							});
							tabla2.getColumnModel().getColumn(0).setPreferredWidth(51);
							tabla2.getColumnModel().getColumn(1).setPreferredWidth(51);
						boton.setEnabled(true);
						combo.setEnabled(true);
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
	
	public void pararTimer() {
		timer.stop();
	}
}
