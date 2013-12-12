package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataLopez {
	
	
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
	public static DefaultTableModel getModelValuesControllers( double vL, double vT, double Kp, double tau){
		
		double[][] result = new double[3][6];
		result[0][0] = redondear( (constantesM[0][0]/Kp)*(Math.pow((vL/tau),constantesM[0][1])) );
		result[0][1] = redondear( (tau/constantesM[0][2])*(Math.pow((vL/tau),-constantesM[0][3])) );
		result[0][2] = redondear( (tau*constantesM[0][4])*(Math.pow((vL/tau),constantesM[0][5])) );
		result[1][0] = redondear( (constantesM[1][0]/Kp)*(Math.pow((vL/tau),constantesM[1][1])) );
		result[1][1] = redondear( (tau/constantesM[1][2])*(Math.pow((vL/tau),-constantesM[1][3])) );
		result[1][2] = redondear( (tau*constantesM[1][4])*(Math.pow((vL/tau),constantesM[1][5])) );
		result[2][0] = redondear( (constantesM[2][0]/Kp)*(Math.pow((vL/tau),constantesM[2][1])) );
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
	
	private static String headTitle = "Método de Lopez - Sistema de lazo abierto";
	
	static double [][] constantesM = {{1.435,-0.921,0.878,-0.749,0.482,1.137},
			{1.357,-0.947,0.842,-0.738,0.381,0.995},
			{1.495,-0.945,1.101,-0.771,0.560,1.006}};
	
	private static String description = "El primer método basado en criterios integrales que presentó ecuaciones para el cálculo de los parámetros del controlador fue desarrollado por López "
	+"y es conocido como el método de López./n" 
	+"López Define una función de costo de la forma: Φ=F[ e(t),t] dt ; Donde F es una función del error y del tiempo. Se obtiene un valor que caracteriza "
	+"la respuesta del sistema./n" 
	+"Entre menor sea el valor de Φ, mejor será el desempeño del sistema de control, por ello, un desempeño óptimo se obtiene cuando Φ es mínimo./n" 
	+"Los criterios de desempeño utilizados por López fueron: Integral del error absoluto (IAE),  Integral del error absoluto por el tiempo (ITAE) y Integral "
	+"del error cuadrático (ISE)./n" 
	+"La optimización de los criterios de desempeño integrales de López está basada en el mejor modelo de primer orden más tiempo muerto que se pueda obtener, "
	+"para lazos de control que funcionan como reguladores con un controlador PID-Ideal./n" 
	+"Según el criterio de López los parámetros del PID se encuentran en base a la minimización de los índices de funcionamiento. Para esto se asume que la "
	+"respuesta se aproxima por una función de transferencia de primer orden con retardo.";
	
	private static double redondear(double numero) {
		return (Math.rint(numero*100)/100);
	}

}
