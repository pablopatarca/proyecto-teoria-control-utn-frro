package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import logicaLazoAbierto.Curva;
import logicaLazoAbierto.Graficador;

public class MethodPanelZieglerNichols extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel; //Panel principal
	private Graficador graficador; 
	private JTable inputTable;
	private JTable vTableControllers;
	private JTable tableTL;
	
	
	/**
	 * Create the panel constructor.
	 */

	public MethodPanelZieglerNichols(final MainView ventana) {
		
		int marginTop = 30;
		int marginRight = 5;
		int marginRight2 = 645;
		
		
		graficador = new Graficador();
		
/**********************  Define Panels  ***************************************/
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		//Define 
		final JPanel graphicPanel = new JPanel();
		graphicPanel.setBounds(marginRight, marginTop, 625, 530);
		graphicPanel.setLayout(new BorderLayout(0, 0));
		graphicPanel.add(graficador.getDiagrama(),BorderLayout.CENTER);
		
		mainPanel.add(graphicPanel);
		
		//Define panel of controller's value
		JPanel vControllerPanel;
		//setBounds(100, 100, 1005, 637);
		
		//Reference data panle
		JPanel referencePanel = new JPanel();
		referencePanel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		referencePanel.setBounds(marginRight2, 320, 342, 86);
		mainPanel.add(referencePanel);
		
		//Aditional information panel
		JPanel aditionalInfo = new JPanel();
		aditionalInfo.setBorder(new TitledBorder(null, "Información Adicional", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		aditionalInfo.setBounds(645, 410, 342, 150);
		aditionalInfo.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(aditionalInfo);
		
		// L y T values panel  *********************************/
		JPanel panelLT = new JPanel();
		panelLT.setBorder(new TitledBorder(null, "Valores de L y T", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLT.setBounds(marginRight2, 124, 200, 70);
		panelLT.setLayout(new BorderLayout(0, 0));
		//Define scrollpane
		JScrollPane paneltableTL = new JScrollPane();
		panelLT.add(paneltableTL, BorderLayout.CENTER);
		mainPanel.add(panelLT);
		
		//Sintonization value panel *****************/
		vControllerPanel = new JPanel();
		vControllerPanel.setBorder(new TitledBorder(null, "Valores Sintonización", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		vControllerPanel.setBounds(marginRight2, 207, 349, 110);
		vControllerPanel.setLayout(new BorderLayout(0, 0));
		//Define ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		vControllerPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(vControllerPanel);
		
		//Create input value panel
		JPanel jpInput = new JPanel();
		jpInput.setBorder(new TitledBorder(null,"Valores de Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpInput.setBounds(marginRight2, marginTop, 200, 70);
		jpInput.setLayout(new BorderLayout(0, 0));
		//mainPanel.add(spInputTable);
		mainPanel.add(jpInput, BorderLayout.CENTER);
		JScrollPane spInputTable = new JScrollPane();
		jpInput.add(spInputTable);
		
/*********************  Create Tables  ******************************/
		//Create a table of controller's value
		vTableControllers = new JTable() ;
		vTableControllers.setModel( getModelValuesControllers() );
		vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
		//TablaRender miRender = new TablaRender();
		//vTableControllers.setDefaultRenderer(String.class, miRender);
		scrollPane.setViewportView(vTableControllers);
		
		// L y T table values
		tableTL = new JTable();
		tableTL.setModel( getTableLT() );
		tableTL.setRowSelectionAllowed(false);
		tableTL.setCellSelectionEnabled(true);
		paneltableTL.setViewportView(tableTL);
		
		//Definición Input Table
		inputTable = new JTable();
		inputTable.setModel( getInputTableModel() );
		//inputTable.getColumnModel().getColumn(0).setResizable(false);
		//inputTable.getColumnModel().getColumn(1).setResizable(false);
		inputTable.setRowSelectionAllowed(false);
		inputTable.setCellSelectionEnabled(true);
		//spInputTable.setBounds(20, 20, 20, 20);
		spInputTable.setViewportView(inputTable);
		
		JLabel lblMtodoDeLa = new JLabel(headTitle);
		lblMtodoDeLa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMtodoDeLa.setBounds(marginRight, 5, 500, 20);
		mainPanel.add(lblMtodoDeLa);
		
		
/**********************  Define Buttons  ******************************/
		//Define Buttons
		JButton btnDibujar = new JButton("Dibujar");
		btnDibujar.setBounds(850, marginTop, 140, 33);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(850, marginTop+40, 140, 33);
		mainPanel.setLayout(null);
		
		JButton btnAssumedModel = new JButton("Modelo asumido");
		btnAssumedModel.setBounds(10, 22, 190, 25);
		btnAssumedModel.setSize(140, 35);
		
		JButton btnEquations = new JButton("Ecuaciones");
		btnEquations.setBounds(180, 22, 190, 25);
		btnEquations.setSize(140, 35);
		
		JButton buttonValues = new JButton("Constantes");
		buttonValues.setBounds(10, 69, 190, 25);
		buttonValues.setSize(140, 35);
		
		JButton btnDescription = new JButton("Descripci\u00F3n m\u00E9todo");
		btnDescription.setBounds(180, 69, 190, 25);
		btnDescription.setSize(140, 35);
		
		
		//Add Buttons to panels		
		mainPanel.add(btnLimpiar);
		mainPanel.add(btnDibujar);
		aditionalInfo.setLayout(null);
		aditionalInfo.add(btnAssumedModel);
		aditionalInfo.add(btnEquations);
		//aditionalInfo.add(buttonValues);
		aditionalInfo.add(btnDescription);
		
		
/***********************  Define Labels  ********************************/
		//Create leabels
		JLabel lblGanancia = new JLabel("Ganancia");
		JLabel lblRespuesta = new JLabel("Respuesta");
		JLabel lblRectaTangenteAl = new JLabel("Recta tangente al punto de inflexi\u00F3n");
		JLabel lblRetardol = new JLabel("Retardo (L)");
		JLabel lblCteDeTiempo = new JLabel("Cte. de tiempo (T)");

		
		//Create label color
		JLabel blanco = new JLabel("Ganancia");
		blanco.setIcon(new ImageIcon(MainView.class.getResource("/iconos/blanco.png")));
		JLabel rojo = new JLabel("Red");
		rojo.setIcon(new ImageIcon(MainView.class.getResource("/iconos/rojo.png")));
		JLabel azul = new JLabel("Blue");
		azul.setIcon(new ImageIcon(MainView.class.getResource("/iconos/azul.png")));
		JLabel amarillo = new JLabel("Yelow");
		amarillo.setIcon(new ImageIcon(MainView.class.getResource("/iconos/amarillo.png")));
		JLabel green = new JLabel("Green");
		green.setIcon(new ImageIcon(MainView.class.getResource("/iconos/verde.png")));
	
		int widthColor = 15;
		int heightColor = 10;
		int positionColor = 20;
		int colorC1 = 15;
		int colorC2 = 191;
		//Set colors dimensions
		blanco.setBounds(colorC1, positionColor, widthColor, heightColor);
		rojo.setBounds(colorC1, positionColor*2, widthColor, heightColor);
		azul.setBounds(colorC1, positionColor*3, widthColor, heightColor);
		amarillo.setBounds(colorC2, positionColor, widthColor, heightColor);
		green.setBounds(colorC2, positionColor+25, widthColor, heightColor);
		
		
		colorC1 += 20;
		colorC2 += 20;
		//Set Label dimensions
		lblGanancia.setBounds(42, 18, 100, 14);
		lblRespuesta.setBounds(42, 38, 150, 14);
		lblCteDeTiempo.setBounds(220, 37, 100, 16);
		lblRetardol.setBounds(220, 18, 100, 14);
		lblRectaTangenteAl.setBounds(42, 57, 300, 16);
		
		//Add colors to panel
		referencePanel.setLayout(null);
		referencePanel.add(blanco);
		referencePanel.add(azul);
		referencePanel.add(rojo);
		referencePanel.add(amarillo);
		referencePanel.add(green);
		
		//Add labels to panel
		referencePanel.add(lblGanancia);
		referencePanel.add(lblRespuesta);
		referencePanel.add(lblRectaTangenteAl);
		referencePanel.add(lblRetardol);
		referencePanel.add(lblCteDeTiempo);
		
		
		
/*****************  Create Image Panel  **************************************/
		
		JLabel lblModeloAsumidoDe = new JLabel("Modelo asumido de la planta:");
		lblModeloAsumidoDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModeloAsumidoDe.setBounds(695, 243, 350, 19);
		mainPanel.add(lblModeloAsumidoDe);
		
		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(850, 112, 140, 33);
		mainPanel.add(btnSave);
		
		/*Add assumed model
		JLabel lblEcuacionImagen = new JLabel("");
		lblModeloAsumidoDe.setLabelFor(lblEcuacionImagen);
		ImageIcon img = new ImageIcon(Ventana.class.getResource("/iconos/ecuacion.png"));
		
		lblEcuacionImagen.setIcon(img);
		lblEcuacionImagen.setBounds(695, 443, 200, 200);
		mainPanel.add(lblEcuacionImagen);
		*/
		
		
/*****************  Define Listeners  ***************************************/
		
		//Constantes para ecuaciónes del metodo de Lopez   **********
		final JTable constantTable = getConstantTable();
		//Show constants method
		buttonValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JDialog dialog =  new JDialog(ventana);
				dialog.setTitle("Constantes Metodo de Lopez");
			    dialog.getContentPane().add(new JScrollPane(constantTable));
			    dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				dialog.setBounds(100, 100, 500, 200);
				
			}
		});
		
		//Show assumed model ******************************************
		btnAssumedModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ModalEquationView dialog = new ModalEquationView(ventana, "/iconos/equationFirstOrder.png");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
			}
		});
		
		//Show controller equations ********************************
		btnEquations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ModalEquationView dialog = new ModalEquationView(ventana, "/iconos/equationZieglerNichols.png");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				dialog.setBounds(100, 100, 500, 200);
				
			}
		});
		
		//Show Description
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(ventana, mensaje);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				dialog.setBounds(100, 100, 500, 200);
			}
		});
		

		/**
		 * Insertar grafica
		 */
		btnDibujar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(inputTable.isEditing()){
					inputTable.getCellEditor().stopCellEditing();
				}
				inputTable.clearSelection();
				
				TableModel modelo = inputTable.getModel();
				
				if(modelo.getValueAt(0, 1) != null && modelo.getValueAt(0, 0) != null){
					
					double k =  Double.parseDouble((String) modelo.getValueAt(0,0));
					double taw = Double.parseDouble((String) modelo.getValueAt(0,1));
				
					if(k >= 0.0 && taw >= 0.0) {
					
					graficador.insertarCurva(k, taw, 2);
					/**
					 * DIBUJO EL GRAFICO
					 */
					graphicPanel.removeAll();
					graphicPanel.add(graficador.getDiagrama(),BorderLayout.CENTER);
					graphicPanel.validate();
					
					if(graficador.getCurvaActual() != null) {
						Curva curvaActual = graficador.getCurvaActual();
						
						double vT = curvaActual.getT();
						double vL = curvaActual.getL();
						
						vTableControllers.setModel(setModelValuesControllers(vL, vT));
						//vTableControllers.getModel().getColumnClass(0).getModifiers()
						
						vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
						tableTL.setModel(setTableLT(curvaActual.getL(), curvaActual.getT()));
						
					}
				}
				else {
				JOptionPane.showMessageDialog(null, "Las constantes deben ser valores mayores que cero", 
						"Error", JOptionPane.ERROR_MESSAGE, null);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Debe ingresar constantes de tiempo y ganancia", 
						"Error", JOptionPane.ERROR_MESSAGE, null);
			}
			}
		});
		
		/**
		 * LIMPIA GRAFICA
		 */
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiaGrafica(graphicPanel);
			}
		});
		
		//Save Graphics
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					graficador.getImage();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
/**********  Application Methods ***************************************************/	
	
	private void limpiaGrafica(JPanel panel){
		inputTable.setModel( getInputTableModel() );
		graficador.limpiar();
		/**
		 * DIBUJO EL GRAFICO
		 */
		panel.removeAll();
		panel.add(graficador.getDiagrama(),BorderLayout.CENTER);
		panel.validate();
		
		vTableControllers.setModel( getModelValuesControllers() );
		vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
			
		tableTL.setModel( getTableLT() );
	}
	
	
	//Retorno Panel Principal
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
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
	private DefaultTableModel setModelValuesControllers( double vL, double vT){
		
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
