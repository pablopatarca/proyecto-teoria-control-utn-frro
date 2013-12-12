package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JTable;

import org.jfree.chart.plot.ValueMarker;

public class Curva3 extends Curva{
	
	double step[] = {0.0, 4.0, 8.0};
	private double ktest[] = {1, 6, 10};
	private String title[] = {	"  K = 1                                                                                      ",
			"  K = 1                                   K = 6                                           ",
			"                   K = 1                                  K = 6                                K crítica = 10      "};
	
	public Curva3() {
		super();
		KCritico = 10.0;
		PCritico = 0.9472;
		puntoXInicio = 9.495;
		puntoXFin = puntoXInicio + PCritico;
		puntoYInicio = 0.0;
		puntoYFin = 1.80;
		comment.setText(title[0]);
		marcadores = new ValueMarker[2];
		marcadores[0] = new ValueMarker(step[1]);
		marcadores[1] = new ValueMarker(step[2]);
		for(ValueMarker marcador : marcadores) {
			marcador.setStroke(new BasicStroke(1.0f));
			marcador.setPaint(Color.BLACK);
		}
	}

	public void dibujarCurva(double t) {
		if(t <= step[1])
			dibujoCurva.add(t, funcionCurva1(t));
		else if(t <= step[2])
			dibujoCurva.add(t, funcionCurva2(t-step[1]));
		else {
			dibujoCurva.add(t, funcionCurva3(t-step[2]));
		}
	}
	
	public double getStep(int i){
		
		return step[i];
			
	}
	
	@Override
	public double getKtest(int i){
		return ktest[i];
	}
	
	public void setComment(int i) {
		if(i==0){
		comment.setText(title[0]);
		}else if(i==1){
			comment.setText(title[1]);
		}else if(i==2){
			comment.setText(title[2]);
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
