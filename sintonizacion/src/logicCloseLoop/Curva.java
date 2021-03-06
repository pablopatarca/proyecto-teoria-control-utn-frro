package logicCloseLoop;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public abstract class Curva {
	
	protected XYSeries dibujoCurva, dibujoPeriodo;
	
	protected double KCritico, PCritico, puntoXInicio, puntoXFin, puntoYInicio, puntoYFin;
	protected String [] title;
	protected TextTitle comment;
	private static XYSeries entrada;
	
	protected double step[];
	protected double ktest[];
	
	public XYSeries getDibujoPeriodo() {
		return dibujoPeriodo;
	}
	
	public double getPuntoYInicio() {
		return puntoYInicio;
	}

	public double getPuntoYFin() {
		return puntoYFin;
	}

	public double getPuntoXInicio() {
		return puntoXInicio;
	}

	public double getPuntoXFin() {
		return puntoXFin;
	}
	
	protected ValueMarker[] marcadores;

	public ValueMarker[] getMarcadores() {
		return marcadores;
	}
	
	public Curva() {
		dibujoCurva = new XYSeries(0);
		dibujoPeriodo = new XYSeries(1);
		comment = new TextTitle();
	}
	
	public static void generarEntrada(double t) {
		entrada = new XYSeries("Entrada");
		for(double x = 0.0; x <=t; x += 0.01)
			entrada.add(x, 1);
	}
	
	public static XYSeries getEntrada() {
		return entrada;
	}
	
	public TextTitle getComment() {
		return comment;
	}
	public void setComment(int i) {
	}
	public void setComment() {
		comment.setText(title[0]);
	}
	
	public XYSeries getDibujoCurva() {
		return dibujoCurva;
	}
	
	public double getKCritico() {
		return KCritico;
	}
	
	public double getPCritico() {
		return PCritico;
	}
	
	public double getStep(int i){
		return step[i];
	}
	public double getKtest(int i){
		return ktest[i];
	}
	
	public abstract void dibujarCurva(double t);
	
	public abstract void dibujarPeriodo(double t);

	public abstract int getTipoCurva();
}
