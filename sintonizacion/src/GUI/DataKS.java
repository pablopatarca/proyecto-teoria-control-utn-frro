package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataKS {
	
	
	public static DefaultTableModel getConstants(){
			
			return new DefaultTableModel(
					new Object[][] { 
						{"IAE", constantesM[0][0], constantesM[0][1], constantesM[0][2], constantesM[0][3], constantesM[0][4], constantesM[0][5]},
						{"ITAE", constantesM[1][0], constantesM[1][1], constantesM[1][2], constantesM[1][3], constantesM[1][4], constantesM[1][5]},
						{"ISE", constantesM[2][0], constantesM[2][1], constantesM[2][2], constantesM[2][3], constantesM[2][4], constantesM[2][5]} },
						
						new String[] {"Criterios","a", "b", "c", "d", "e", "f"} ){
				
				private static final long serialVersionUID = 1L;
				
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				};
			};
		}

	
	//Devuelvo valores calculados
	public static DefaultTableModel getModelValuesControllers( double vL, double vT, double kp, double tau){
		
		double[][] result = new double[3][6];
		result[0][0] = redondear( (constantesM[0][0]/kp)*(Math.pow((vL/tau),constantesM[0][1])) );
		result[0][1] = redondear( (tau/constantesM[0][2])*(Math.pow((vL/tau),-constantesM[0][3])) );
		result[0][2] = redondear( (tau*constantesM[0][4])*(Math.pow((vL/tau),constantesM[0][5])) );
		result[1][0] = redondear( (constantesM[1][0]/kp)*(Math.pow((vL/tau),constantesM[1][1])) );
		result[1][1] = redondear( (tau/constantesM[1][2])*(Math.pow((vL/tau),-constantesM[1][3])) );
		result[1][2] = redondear( (tau*constantesM[1][4])*(Math.pow((vL/tau),constantesM[1][5])) );
		result[2][0] = redondear( (constantesM[2][0]/kp)*(Math.pow((vL/tau),constantesM[2][1])) );
		result[2][1] = redondear( (tau/constantesM[2][2])*(Math.pow((vL/tau),-constantesM[2][3])) );
		result[2][2] = redondear( (tau*constantesM[2][4])*(Math.pow((vL/tau),constantesM[2][5])) );
		
		return new DefaultTableModel( new Object[][] {
		{"PID-IAE", result[0][0],result[0][1], result[0][2]},
		{"PID-ITAE", result[1][0], result[1][1], result[1][2]},
		{"PID-ISE", result[2][0], result[2][1], result[2][2]}},
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
		return "/icons/equationLopez.png";
	}
	
	public static String getTitle(){
		return headTitle;
	}
	public static String getDescription(){
		return description;
	}
	
	private static String headTitle = "Método de Kaya y Sheib - Sistema de lazo abierto";
	
	private static double [][] constantesM = {{0.98089,-0.76167,0.91032,-1.05211,0.59974,0.89819},
			{0.77902,-1.06401,1.14311,-0.70949,0.57137,1.03826},
			{1.11907,-0.89711,0.79870,-0.95480,0.54766,0.87798}};
	
	private static String description = "Mientras López desarrolló el método de sintonización para un controlador PID-Ideal, Kaya y Sheib realizaron lo mismo para "
			+ "controladores que denominaron PID-Clásico (PID-Serie), PID- No Interactuante (una variación del PID-Paralelo) y PID-Industrial. El procedimiento de "
			+ "sintonización está basado en el mejor modelo de primer orden más tiempo muerto que se pueda obtener para lazos de control que funcionan como reguladores. "
			+ "El criterio de desempeño corresponde a la minimización de alguno de los criterios integrales y el controlador a uno de los indicados anteriormente.";
	
	private static double redondear(double numero) {
		return (Math.rint(numero*100)/100);
	}

}
