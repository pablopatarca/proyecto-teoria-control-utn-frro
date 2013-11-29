package logicOpenLoop;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;

public class Graficador {
	
	private XYSeriesCollection conjuntoDatos;
	private Curva curvaActual;
	private JFreeChart diagrama;
	
	public Graficador() {
		conjuntoDatos = new XYSeriesCollection();
		curvaActual = null;
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
