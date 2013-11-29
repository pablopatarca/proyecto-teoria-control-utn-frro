package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.plot.ValueMarker;

public class Curva2 extends Curva {

	public Curva2() {
		super();
		KCritico = 8.0;
		PCritico = 3.63;
		puntoXInicio = 30.745;
		puntoXFin = puntoXInicio + PCritico;
		puntoYInicio = 0.0;
		puntoYFin = 1.80;
		comentario.setText("    K = 1                                                          K = 5                                             K cr�tica = 8");
		marcadores = new ValueMarker[2];
		marcadores[0] = new ValueMarker(12.50);
		marcadores[1] = new ValueMarker(25.0);
		for(ValueMarker marcador : marcadores) {
			marcador.setStroke(new BasicStroke(1.0f));
			marcador.setPaint(Color.BLACK);
		}
	}

	public void dibujarCurva(double t) {
		if(t <= 12.50)
			dibujoCurva.add(t, funcionCurva1(t));
		else if (t <= 25.0)
			dibujoCurva.add(t, funcionCurva2(t-12.50));
		else
			dibujoCurva.add(t, funcionCurva3(t-25.0));
	}
	
	private double funcionCurva3(double t) {
		return (8.0/9.0 - (2*Math.cos(Math.sqrt(3)*t))/3 - (2*Math.sqrt(3)*Math.sin(Math.sqrt(3)*t))/9 - (2*Math.pow(Math.E, -3*t))/9.0);
	}
	
	private double funcionCurva2(double t) {
		return (1 - ((1/(Math.sqrt(1-Math.pow(0.4, 2))))*(Math.exp(-0.4*t))*(Math.sin((Math.sqrt(1-Math.pow(0.4, 2))*t)+(Math.atan(Math.sqrt(1-Math.pow(0.4, 2))/0.4))))));
	}
	
	private double funcionCurva1(double t) {
		return (0.5 - (((Math.pow(Math.E, -0.5*t))*((Math.cos((Math.sqrt(3.0)*t)/2.0))+(Math.sqrt(3.0))*(Math.sin((Math.sqrt(3)*t)/2.0)))/3)) - (Math.pow(Math.E, -2*t)/6.0));
	}

	@Override
	public void dibujarPeriodo(double t) {
		dibujoPeriodo.add(t, funcionCurva3(puntoXInicio-25.0));
	}

	@Override
	public int getTipoCurva() {
		// TODO Auto-generated method stub
		return 2;
	}

}
