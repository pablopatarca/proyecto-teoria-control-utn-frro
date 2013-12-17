package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataClosedZN {
	
	public static DefaultTableModel getKPModel(){
		
		return new DefaultTableModel(
				new Object[][] {{null, null}},
				new String[] {"K último", "P último"}){
					private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					Double.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				};
			};
	}
	
	public static DefaultTableModel getKPModel(double vK, double vP){
		
		return new DefaultTableModel(
				new Object[][] {{round(vK), round(vP)}},
				new String[] {"K último", "P último"}){
					private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					Double.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				};
			};
	}
	
	public static DefaultTableModel getControllerModel(){
		return new DefaultTableModel(
				new Object[][] {{"P", "0.5 Ku", null, null},
								{"PI", "0.45 Ku", "(1/1.2) Pu", null},
								{"PID", "0.6 Ku", "0.5 Pu", "0.125 Pu"}},
				new String[] {"Tipo de controlador", "Kc", "Ti", "Td"}){
				
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
	
	public static DefaultTableModel getControllerModel(double criticK, double criticP){
		
		return new DefaultTableModel(
				new Object[][] {
						{"P", round(0.5*criticK), null, null},
						{"PI", round(0.45*criticK), round((1.0/1.2)*criticP), null},
						{"PID", round(0.6*criticK), round(0.5*criticP), round(0.125*criticP)},
				},
				new String[] {"Tipo de controlador", "Kc", "Ti", "Td"}){
				
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
	
	private static double round(double numero) {
		return (Math.rint(numero*100)/100);
	}
	
	private static String description = "Un procedimiento de sintonización a lazo cerrado  implica deshabilitar cualquier acción integral o derivativa del controlador.\n\n"
	+"Consiste en ir aumentando la ganancia del controlador hasta que la variable de proceso empiece a oscilar. La cantidad de ganancia "
	+"necesaria para generar oscilaciones sostenidas (amplitud constante) es llamada “Ganancia última” del proceso, mientras que el tiempo "
	+"(período) entre cada pico de oscilación es llamado “Período último” del proceso.\n\n"
	+"Ziegler y Nichols indican cómo se debe configurar la ganancia del controlador con un valor de la mitad de la ganancia última.  "
	+"Kp = 0.5 Ku \n\n" 
	+"Kp: Ganancia del controlador \n\n"
	+"Ku: Ganancia última , determinada incrementando la ganancia del controlador hasta lograr oscilación auto-sostenibles.\n\n"
	+"Una ganancia del controlador a la mitad de la “Ganancia última” determinada experimentalmente resulta una razonable "
	+"respuesta rápida ante cambios de setpoint y cambios de carga.\n\n" 
	+"Las oscilaciones de la variable de proceso a cambios de setpoint y de carga son atenuadas con cada pico de onda "
	+"siendo aproximadamente un cuarto de la amplitud del pico anterior.\n\n"  
	+"Las reglas dadas por Ziegler y Nichols describen una relación real entre los parámetros de sintonización y las características "
	+"operacionales del proceso. Para que el proceso oscile constantemente la ganancia del controlador debería ser alguna fracción de "
	+"la ganancia última y la constante de tiempo integral debería ser proporcional a la constante de tiempo del proceso. \n\n";
		
	public static String getDescription(){
		return description;
	}

}
