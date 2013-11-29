package logicOpenLoop;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;

public class CurvaPrueba1 {


	private double KCritico, PCritico, puntoXInicio, puntoXFin, puntoYInicio, puntoYFin;
	private TextTitle comentario;
	
	
	private XYSeries entrada;
	private XYSeries dibujoCurva, rectaTangente, dibujoK, tiempoMuerto, retardo;
	
	private XYSeries dibujoPeriodo, dibujoLY, dibujoTY, dibujoLX, dibujoTX;
	private ValueMarker[] marcadoresCurva,marcadoresInd;
	private double k, tau, tiempoFin, L, T;

	public CurvaPrueba1(double k, double tau) {

		KCritico = 20.0;
		PCritico = 1.895;
		//puntoXInicio = 26.53;
		puntoXInicio = 0;
		//puntoXFin = puntoXInicio + PCritico;
		puntoXFin = puntoXInicio + tau;
		puntoYInicio = 0.0;
		puntoYFin = k+k*.3;
		//Set Markers		
		
		dibujoCurva = new XYSeries(0);
		rectaTangente = new XYSeries(1);
		dibujoK = new XYSeries(2);
		tiempoMuerto = new XYSeries(3);
		retardo = new XYSeries(4);
		
		
		//Metodo lazo abierto
		//dibujoCurva = new XYSeries(2);
		//dibujoPeriodo = new XYSeries(3);
		
		//dibujoLY = new XYSeries(5);
		//dibujoTY = new XYSeries(6);
		//dibujoLX = new XYSeries(7);
		//dibujoTX = new XYSeries(8);
		this.k = k;
		this.tau = tau;
		tiempoFin = this.getTiempoAsentamiento() + 2;
		
		
		this.setMarkers(k, tau);

		
	}
	
	/*public void graphI(){
		this.dibujarCurva();
		this.dibujarDerivada();
		this.dibujarRetardo();
		this.dibujarCteTiempo();
	}*/
	
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

	//return plot markers
	public ValueMarker[] getMarcadoresCurva() {
		return marcadoresCurva;
	}
	//return plot markers
	public ValueMarker[] getMarcadoresInd() {
		return marcadoresInd;
	}
	
	public XYSeries getEntrada() {
		return entrada;
	}
	
	public TextTitle getComentario() {
		return comentario;
	}
	
	public double getKCritico() {
		return KCritico;
	}
	
	public double getPCritico() {
		return PCritico;
	}
	
/*************************  Equations *************************************/
	
	
	//Primer orden mas tiempo muerto
		public double funcionCurva(double t) {
				
			return k*(1 - ((1+(t/tau))*Math.pow(Math.E, -1*t/tau)));
		}
		
	//Polo doble mas tiempo muerto
	private double funcionDerivadaPrimera(double puntoInflexionY) {
			return (k*puntoInflexionY*Math.pow(Math.E, -1*puntoInflexionY/tau))/(Math.pow(tau, 2));
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

	public int getTipoCurva() {
		return 1;
	}
	
/******************************************************************/
	
	/********* DIBUJA GRAFICA ******************/
	
	public XYSeries getDibujoCurva() {
		return dibujoCurva;
	}
	
	public XYSeries getDibujoTangente() {
		return rectaTangente;
	}
	
	public void dibujarCurva(double t) {
		/*
		if(t <= 10.0) {
			dibujoCurva.add(t, funcionCurva1(t));
		}
		else if(t <= 20.0){
			dibujoCurva.add(t, funcionCurva2(t-10.0));
		}
		else {
			dibujoCurva.add(t, funcionCurva3(t-20.0));
		}*/
		
		dibujoCurva.add(t, funcionCurva(t));
		
	}
	
	public void dibujarTangente(double t) {
		
		double m = funcionDerivadaPrimera(tau);
		double h = funcionCurva(tau) - (tau*m);
		
		T = (k-h)/m;
		L = (-1*h)/m;
		
		double esCeroEn = tau*(3-Math.E);
			rectaTangente.add(t, (m*t)+h);
		//}
	}
	
	public void dibujarK(double t) {
				
		dibujoK.add(t, k);
		
	}
	
	public XYSeries getDibujoK() {
		return dibujoK;
	}
	
	public void dibujaLT(double t) {
		
		if(t < L){
			tiempoMuerto.add(t, 0);
		}
		else if(t > L && t < T){
			retardo.add(t, 0);
		}
	}
	
	public XYSeries getTiempoMuerto(){
		return tiempoMuerto;
	}
	public XYSeries getRetardo(){
		return retardo;
	}
	
	
	
	public void setMarkers(double k, double tau){
		
		marcadoresCurva = new ValueMarker[2];
		
		double m = funcionDerivadaPrimera(tau);
		double h = funcionCurva(tau) - (tau*m);
		
		T = (k-h)/m;
		L = (-1*h)/m;
		
		double esCeroEn = tau*(3-Math.E);
		
		System.out.println(esCeroEn);
		System.out.println(T-L);
		
		marcadoresCurva[0] = new ValueMarker(esCeroEn);
		marcadoresCurva[1] = new ValueMarker(T);
		
		for(ValueMarker marcador : marcadoresCurva) {
			marcador.setStroke(new BasicStroke(1.0f));
			marcador.setPaint(Color.BLACK);
		}
		
	}
	
	
	

	
/**************************************************************/
	
	
	
	
	
	public void generarEntrada(double t) {
		entrada = new XYSeries("Entrada");
		for(double x = 0.0; x <=t; x += 0.01)
			entrada.add(x, 1);
	}
	
	private void dibujarCteTiempo() {
		for(double x = 0.0; x <= k; x += 0.001)
			dibujoTY.add(T, x);
		for(double x = (L+0.00001); x <= T; x += 0.001)
			dibujoTX.add(x, 0);
	}

	private void dibujarRetardo() {
		for(double x = 0.0; x <= k; x += 0.001)
			dibujoLY.add(L, x);
		for(double x = 0.0; x <= L; x += 0.001)
			dibujoLX.add(x, 0);
	}

	private void dibujarDerivada() {
		double m = funcionDerivadaPrimera(tau);
		double h = funcionCurva(tau) - (tau*m);
		T = (k-h)/m;
		L = (-1*h)/m;
		double esCeroEn = tau*(3-Math.E);
		for(double t = esCeroEn; ((m*t)+h) <= (k+(k*0.15)); t += 0.01) {
			rectaTangente.add(t, (m*t)+h);
		}
	}
	
	public XYSeries getTangente() {
		return rectaTangente;
	}
	
	public double getT() {
		return T-L;
	}
	
	public double getL() {
		return L;
	}
	
	private double getTiempoAsentamiento() {
		double valorMinimo = 0, valorMaximo = 0;
		for(double x = 0.0; x <= 200; x += 0.0001) {
			if(funcionCurva(x) > (k-0.0005)) {
				valorMinimo = x;
				break;
			}
		}
		for(double x = 0.0; x <= 200; x += 0.0001) {
			if(funcionCurva(x) > (k+0.0005)) {
				valorMaximo = x;
				break;
			}
		}
		return (valorMinimo+valorMaximo)/2;
	}

	public XYSeries getdibujoLY() {
		return dibujoLY;
	}

	public XYSeries getdibujoTY() {
		return dibujoTY;
	}
	
	public XYSeries getdibujoLX() {
		return dibujoLX;
	}
	
	public XYSeries getdibujoTX() {
		return dibujoTX;
	}
}
