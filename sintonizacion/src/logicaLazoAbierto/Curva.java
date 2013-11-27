package logicaLazoAbierto;

import org.jfree.data.xy.XYSeries;

public class Curva {
	
	public enum tipoCurva { PRIMER_METODO, SEGUNDO_METODO };
	
	private XYSeries dibujoCurva, dibujoDerivada, dibujoK, dibujoLY, dibujoTY, dibujoLX, dibujoTX;
	private double k, tau, tiempoFin, L, T;
	
	public Curva(double k, double tau) {
		dibujoCurva = new XYSeries(1);
		dibujoDerivada = new XYSeries(2);
		dibujoK = new XYSeries(3);
		dibujoLY = new XYSeries(4);
		dibujoTY = new XYSeries(5);
		dibujoLX = new XYSeries(6);
		dibujoTX = new XYSeries(7);
		this.k = k;
		this.tau = tau;
		tiempoFin = this.getTiempoAsentamiento() + 2;
		this.dibujarCurva();
		this.dibujarDerivada();
		this.dibujarRetardo();
		this.dibujarCteTiempo();
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
			dibujoDerivada.add(t, (m*t)+h);
		}
	}

	private void dibujarCurva() {
		for(double t = 0.00; t <= tiempoFin; t += 0.01) {
			dibujoCurva.add(t, funcionCurva(t));
		}
		for(double t = 0.00; t <= tiempoFin; t += 0.01) {
			dibujoK.add(t, k);
		}
	}
	//Primer orden mas tiempo muerto
	private double funcionCurva(double t) {
			return k*(1 - ((1+(t/tau))*Math.pow(Math.E, -1*t/tau)));
	}
	//Polo doble mas tiempo muerto
	private double funcionDerivadaPrimera(double puntoInflexionY) {
			return (k*puntoInflexionY*Math.pow(Math.E, -1*puntoInflexionY/tau))/(Math.pow(tau, 2));
	}
	
	public XYSeries getDibujoCurva() {
		return dibujoCurva;
	}
	
	public XYSeries getDibujoDerivada() {
		return dibujoDerivada;
	}
	
	public XYSeries getDibujoK() {
		return dibujoK;
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

	public double getk() {
		return k;
	}
	
	public static void detenerTiempo () { 
		try {
			System.out.println("Entro al timer");
			Thread.sleep(1);
			System.out.println("Salio del timer");
		} catch (InterruptedException e) {} 
	}
	
}
