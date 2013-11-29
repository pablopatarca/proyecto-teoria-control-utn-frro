package GUI;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class dataZieglerNichols {
	
	
	//Retorno Table Model de valores de entrada
	private DefaultTableModel getInputTableModel() {
		return new DefaultTableModel(
				new Object[][] {{null, null}},
				new String[] {"Ganancia", "Cte. Tiempo"});
	}
	
	private double redondear(double numero) {
		return (Math.rint(numero*100)/100);
	}
	
	private JTable getConstantTable(){
		JTable constantesMetodo;
		double [][] constantesM = {{1.435,-0.921,0.878,-0.749,0.482,1.137},
				{1.357,-0.947,0.842,-0.738,0.381,0.995},
				{1.495,-0.945,1.101,-0.771,0.560,1.006}};
		constantesMetodo = new JTable();
		constantesMetodo.setModel(new DefaultTableModel(
			new Object[][] { 
				{"IAE", constantesM[0][0], constantesM[0][1], constantesM[0][2], constantesM[0][3], constantesM[0][4], constantesM[0][5]},
				{"ITAE", constantesM[1][0], constantesM[1][1], constantesM[1][2], constantesM[1][3], constantesM[1][4], constantesM[1][5]},
				{"ISE", constantesM[2][0], constantesM[2][1], constantesM[2][2], constantesM[2][3], constantesM[2][4], constantesM[2][5]} },
				
				new String[] {"Criterios","a", "b", "c", "d", "e", "f"} ));
		
		constantesMetodo.setRowSelectionAllowed(false);
		constantesMetodo.setCellSelectionEnabled(true);
		return constantesMetodo;
		}
	
	//Retorno table model de L y T 
	private DefaultTableModel getTableLT(){
		return new DefaultTableModel(
				new Object[][] {{null, null}},
				new String[] {"L", "T"}){
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
	
	//Retorno table model de L y T seteados
	private DefaultTableModel setTableLT(double vL, double vT){
		return new DefaultTableModel(
				new Object[][] {{redondear(vL), redondear(vT)}},
				new String[] {"L", "T"});
	}
	
	private DefaultTableModel getModelValuesControllers(){

		return new DefaultTableModel( new Object[][] {
						{"P", null, null, null},
						{"PI", null, null, null},
						{"PID", null, null, null},},
				new String[] {"Tipo controlador", "Kp", "Ti", "Td"}){
	
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
				}
			
		};

	}
	
	//Devuelvo valores calculados
	public DefaultTableModel setModelValuesControllers( double vL, double vT){
		
		double[][] result = new double[3][3];
		result[0][0] = redondear(vT/vL);
		result[1][0] = redondear(0.9*(vT/vL));
		result[1][1] = redondear(vL/0.03);
		result[2][0] = redondear(1.2*(vT/vL));
		result[2][1] = redondear(2*vL);
		result[2][2] = redondear(0.5*vL);
				
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
	
	private String headTitle = "Método de Ziengler y Nichols - Sistema de lazo abierto";
	
	private String mensaje = "El Método consiste en obtener la respuesta de la señal medida "
			+ "a una entrada escalón en un sistema de lazo abierto. Si la planta no "
			+ "contiene integradores ni polos dominantes complejos conjugados, la curva "
			+ "puede tener la forma de una S (si la respuesta no exhibe esta forma de S, "
			+ "este método no es pertinente)."
			+ "\n\n"
			+ "Tales curvas de respuesta se generan experimentalmente o a partir de una "
			+ "simulación dinámina de la planta y están caracterizadas por dos parámetros: "
			+ "el tiempo de retardo L y la constante de tiempo T. Estos parámetros se "
			+ "determinan dibujando una recta tangente en el punto de inflexión de la curva "
			+ "y determinando las intersecciones de esta tangente con el eje del tiempo "
			+ "y la línea Y(t) = K, es decir, la ganancia aplicada.";

}
