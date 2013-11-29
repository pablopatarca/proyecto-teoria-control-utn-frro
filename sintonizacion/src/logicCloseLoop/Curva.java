package logicCloseLoop;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public abstract class Curva {
	
	protected XYSeries dibujoCurva, dibujoPeriodo;
	
	public XYSeries getDibujoPeriodo() {
		return dibujoPeriodo;
	}

	protected double KCritico, PCritico, puntoXInicio, puntoXFin, puntoYInicio, puntoYFin;
	
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

	protected TextTitle comentario;
	private static XYSeries entrada;
	
	public Curva() {
		dibujoCurva = new XYSeries(0);
		dibujoPeriodo = new XYSeries(1);
		comentario = new TextTitle();
	}
	
	public static void generarEntrada(double t) {
		entrada = new XYSeries("Entrada");
		for(double x = 0.0; x <=t; x += 0.01)
			entrada.add(x, 1);
	}
	
	public static XYSeries getEntrada() {
		return entrada;
	}
	
	public TextTitle getComentario() {
		return comentario;
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
	
	public abstract void dibujarCurva(double t);
	
	public abstract void dibujarPeriodo(double t);

	public abstract int getTipoCurva();
}
