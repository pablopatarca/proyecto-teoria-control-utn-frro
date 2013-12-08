package GUI;

import javax.swing.table.DefaultTableModel;

public abstract class DataClosedZN {
	
	public static DefaultTableModel getKPModel(){
		
		return new DefaultTableModel(
				new Object[][] {{null, null}},
				new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}){
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
				new Object[][] {{vK, vP}},
				new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}){
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
				new Object[][] {{"P", "0.5 Kcr", null, null},
								{"PI", "0.45 Kcr", "(1/1.2) Pcr", null},
								{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"}},
				new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}){
				
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
						{"P", 0.5*criticK, null, null},
						{"PI", 0.45*criticK, (1.0/1.2)*criticP, null},
						{"PID", 0.6*criticK, 0.5*criticP, 0.125*criticP},
				},
				new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}){
				
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

}
