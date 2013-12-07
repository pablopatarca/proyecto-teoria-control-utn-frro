package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.plot.ValueMarker;

public class Curva3 extends Curva{
	
	public Curva3() {
		super();
		KCritico = 10.0;
		PCritico = 0.9472;
		puntoXInicio = 9.495;
		puntoXFin = puntoXInicio + PCritico;
		puntoYInicio = 0.0;
		puntoYFin = 1.80;
		comentario.setText("    K = 1                                                          K = 6                                             K crítica = 10");
		marcadores = new ValueMarker[2];
		marcadores[0] = new ValueMarker(4.0);
		marcadores[1] = new ValueMarker(8.0);
		for(ValueMarker marcador : marcadores) {
			marcador.setStroke(new BasicStroke(1.0f));
			marcador.setPaint(Color.BLACK);
		}
	}

	public void dibujarCurva(double t) {
		if(t <= 4.0)
			dibujoCurva.add(t, funcionCurva1(t));
		else if(t <= 8.0)
			dibujoCurva.add(t, funcionCurva2(t-4.0));
		else {
			dibujoCurva.add(t, funcionCurva3(t-8.0));
		}
	}
	
	
	private double funcionCurva3(double t) {
		return (10.0/11.0 - (360*Math.cos(2*Math.sqrt(11)*t))/517 - (60*Math.sqrt(11)*Math.sin(2*Math.sqrt(11)*t))/517 - (10*Math.exp(-12*t))/47);
	}
	
	private double funcionCurva2(double t) {
		return (1 - ((1/(Math.sqrt(1-Math.pow(0.115, 2))))*(Math.exp(-0.115*t/0.1))*(Math.sin((Math.sqrt(1-Math.pow(0.115, 2))*(t/0.1))+(Math.atan(Math.sqrt(1-Math.pow(0.115, 2))/0.115))))));
	}
	
	private double funcionCurva1(double t) {
		return (0.5 - ((4*Math.exp(-2*t)*(Math.cos(2*Math.sqrt(2)*t) + (5*Math.sqrt(2)*Math.sin(2*Math.sqrt(2)*t))/4))/11) - ((3*Math.exp(-8*t))/22));
	}

	@Override
	public void dibujarPeriodo(double t) {
		dibujoPeriodo.add(t, funcionCurva3(puntoXInicio-8.0));
	}

	@Override
	public int getTipoCurva() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
