package logicCloseLoop;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.plot.ValueMarker;

public class Curva1 extends Curva {
	
	private double step[] = {0.0, 10.0, 20.0};
	private double ktest[] = {1, 4, 10};
	private String title[] = {	"  K = 1                                                                                      ",
								"  K = 1                                   K = 4                                           ",
								"                   K = 1                                  K = 4                                K último = 10      "}; 

	public Curva1() {
		super();
		KCritico = 10.0;
		PCritico = 1.895;
		puntoXInicio = 26.53;
		puntoXFin = puntoXInicio + PCritico;
		puntoYInicio = -0.50;
		puntoYFin = 2.25;
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
		if(t <= step[1]) {
			dibujoCurva.add(t, funcionCurva1(t));
		}
		else if(t <= step[2]){
			dibujoCurva.add(t, funcionCurva2(t-step[1]));
		}
		else {
			dibujoCurva.add(t, funcionCurva3(t-step[2]));
		}
	}
	
	public double getStep(int i){
			
		return step[i];
			
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
	
	@Override
	public double getKtest(int i){
		return ktest[i];
	}
	
	private double funcionCurva3(double t) {
		return ((10*Math.pow(Math.E, -6*t))/47 - (580*Math.cos(Math.sqrt(11)*t))/517 + (60*Math.sqrt(11)*Math.sin(Math.sqrt(11)*t))/517 + 10.0/11.0);
	}
	
	private double funcionCurva2(double t) {
		return (((8*Math.pow(Math.E, -5*t)/65)-((12*Math.pow(Math.E, -t/2))*((Math.cos((Math.sqrt(23)*t)/2))-((Math.sqrt(23)*Math.sin(Math.sqrt(23)*t/2))/69))/13)+0.8));
	}
	
	private double funcionCurva1(double t) {
		return ((Math.pow(Math.E, -4*t)/22)-(((6*Math.pow(Math.E, -1*t))*((Math.cos(Math.sqrt(2)*t))+((Math.sqrt(2))*(Math.sin(Math.sqrt(2)*t))/(3))))/(11))+0.5);
	}
	
	public void dibujarPeriodo(double t) {
		dibujoPeriodo.add(t, funcionCurva3(puntoXInicio-20.0));
	}
	

	@Override
	public int getTipoCurva() {
		// TODO Auto-generated method stub
		return 1;
	}
}
