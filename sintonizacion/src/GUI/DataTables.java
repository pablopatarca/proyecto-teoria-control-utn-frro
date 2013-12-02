package GUI;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataTables {
	
	DefaultTableModel globalModelLT, globalModelConstants;
	
	
	public void dataZieglerNichols(){
		
	}
	
	public void dataZieglerNichols(String typeModel){
		

	}
	
	
	public JTable getConstantTable(String typeModel){
		
		JTable constantsTable = new JTable();
		
		if(typeModel=="lopez"){
			constantsTable.setModel( DataLopez.getConstants() );
		}else if(typeModel=="ks"){
			constantsTable.setModel( DataKS.getConstants() );
		}else{
			constantsTable.setModel(null);
		}
		
		constantsTable.setRowSelectionAllowed(false);
		constantsTable.setCellSelectionEnabled(true);
		return constantsTable;
		}
	
	public JTable getTableLT(String typeModel, double vL, double vT){
		
		JTable tableLT = new JTable();
		
		if(typeModel=="zn"){
			tableLT.setModel( getModelLT(vL, vT) );
		}else if(typeModel=="cc"){
			tableLT.setModel( getModelLT(vL, vT) );
		}else if(typeModel=="lopez"){
			tableLT.setModel( getModelLT(vL, vT) );
		}else if(typeModel=="ks"){
			tableLT.setModel( getModelLT(vL, vT) );
		}
		
		tableLT.setRowSelectionAllowed(false);
		tableLT.setCellSelectionEnabled(true);
		return tableLT;
	}
	
	public JTable getTableLT(){
		JTable tableLT = new JTable();
		tableLT.setModel( getModelLT() );
		
		tableLT.setRowSelectionAllowed(false);
		tableLT.setCellSelectionEnabled(true);
		
		return tableLT;
	}
	
	//Returns model L y T 
	public static DefaultTableModel getModelLT(){
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
	//Retorno table model de L y T 
	public DefaultTableModel getModelLT(double vL, double vT){
		
		return new DefaultTableModel(
				new Object[][] {{round(vL), round(vT)}},
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
	
	//Returns model
	public DefaultTableModel getModelValuesControllers(){
		
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
	
	public JTable getTableControllers(String typeModel, double vL, double vT, double tau){
			
			JTable tableControllers = new JTable();
			
			if(typeModel=="zn"){
				tableControllers.setModel( DataZN.getModelValuesControllers(vL, vT) );
			}else if(typeModel=="cc"){
				tableControllers.setModel( DataCC.getModelValuesControllers(vL, vT, tau) );
			}else if(typeModel=="lopez"){
				tableControllers.setModel( DataLopez.getModelValuesControllers(vL, vT, tau) );
			}else if(typeModel=="ks"){
				tableControllers.setModel( DataKS.getModelValuesControllers(vL, vT, tau) );
			}
			
			tableControllers.setRowSelectionAllowed(false);
			tableControllers.setCellSelectionEnabled(true);
			return tableControllers;
		}
	
	public DefaultTableModel getModelControllers(String typeModel, double vL, double vT, double tau){
		
		if(typeModel=="zn"){
			return DataZN.getModelValuesControllers(vL, vT);
		}else if(typeModel=="cc"){
			return DataCC.getModelValuesControllers(vL, vT, tau);
		}else if(typeModel=="lopez"){
			return DataLopez.getModelValuesControllers(vL, vT, tau);
		}else if(typeModel=="ks"){
			return DataKS.getModelValuesControllers(vL, vT, tau);
		}else{
			return null;
		}
		
	}
	
	public String getTitle(String typeModel){
		
		if(typeModel=="zn"){
			return DataZN.getTitle();
		}else if(typeModel=="cc"){
			return DataCC.getTitle();
		}else if(typeModel=="lopez"){
			return DataLopez.getTitle();
		}else if(typeModel=="ks"){
			return DataKS.getTitle();
		}else{
			return null;
		}
		
	}
	
	public String getDescription(String typeModel){
			
		if(typeModel=="zn"){
			return DataZN.getDescription();
		}else if(typeModel=="cc"){
			return DataCC.getDescription();
		}else if(typeModel=="lopez"){
			return DataLopez.getDescription();
		}else if(typeModel=="ks"){
			return DataKS.getDescription();
		}else{
			return null;
		}
		
	}
	
	public String getURLEquationImage(String typeModel){
		
		if(typeModel=="zn"){
			return DataZN.getURLEquationImage();
		}else if(typeModel=="cc"){
			return DataCC.getURLEquationImage();
		}else if(typeModel=="lopez"){
			return DataLopez.getURLEquationImage();
		}else if(typeModel=="ks"){
			return DataKS.getURLEquationImage();
		}else{
			return null;
		}
		
	}
	
	public String getAssumedModel(){
		return "/icons/equationFirstOrder.png";
	}
	
	private double round(double numero) {
		return (Math.rint(numero*100)/100);
	}

}
