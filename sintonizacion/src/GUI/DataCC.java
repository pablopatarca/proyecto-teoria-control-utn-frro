package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataCC {
	
	//Devuelvo valores calculados
	public static DefaultTableModel getModelValuesControllers(double vL, double vT, double kp, double tau ){
		
		double[][] result = new double[3][3];
		result[0][0] = redondear((vT/vL)*(1 + (vL/(3*tau))));
		result[1][0] = redondear((vT/vL)*(0.9 + (vL/(12*tau))));
		result[1][1] = redondear(vL*((32 + 6*(vL/tau))/(9 + 20*(vL/tau))));
		result[2][0] = redondear((4*vT/3*vL) + (1/(4*kp)));
		result[2][1] = redondear(vL*((32 + 6*(vL/tau))) / (13 + 8*(vL/tau)));
		result[2][2] = redondear(vL*(4/(11 + 2*(vL/tau))));
		return new DefaultTableModel( new Object[][] {
		{"P", result[0][0], null, null},
		{"PI", result[1][0], result[1][1], null},
		{"PID", result[2][0], result[2][1], result[2][2]}},
		new String[] {"Tipo controlador", "Kc", "Ti", "Td"}) {
			
			private static final long serialVersionUID = 1L;
			
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};
		};
		
	}
	
	
	
	public static String getURLEquationImage(){
		return "/icons/equationCohenCoon.png";
	}
	
	public static String getTitle(){
		return headTitle;
	}
	
	public static String getDescription(){
		return description;
	}
	
	
	
	private static String headTitle = "Método de Cohen y Coon - Sistema de lazo abierto";
	
	private static String description = "Cohen y Coon introdujeron un índice de auto regulación definido como μ = tm/τ y plantearon nuevas ecuaciones de sintonización, "
			+ "basadas en el mejor modelo de primer orden más tiempo muerto que se pueda obtener para lazos de control que funcionan como regulador, "
			+ "con el criterio de desempeño de decaimiento de ¼ con sobrepaso mínimo, y con mínima área bajo la curva de respuesta, y un controlador PID-Ideal.";
	
	private static double redondear(double numero) {
		return (Math.rint(numero*100)/100);
	}

}
