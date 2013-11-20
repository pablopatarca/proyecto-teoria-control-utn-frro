package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import logicaLazoAbierto.Curva;
import logicaLazoAbierto.Graficador;
import logicaLazoAbierto.TablaRender;

public class PanelLALopez extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel; //Panel principal
	private Graficador graficador;
	private JTable inputTable;
	private JTable vTableControllers;
	private JTable tableTL;
	
	
	/**
	 * Create the panel.
	 */
	
	public PanelLALopez(final Ventana ventana) {
		
		JPanel vControllerPanel;

		setBounds(100, 100, 1005, 637);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		graficador = new Graficador();
		
		final JPanel graphicPanel = new JPanel();
		graphicPanel.setBounds(10, 41, 625, 510);
		graphicPanel.setLayout(new BorderLayout(0, 0));
		graphicPanel.add(graficador.getDiagrama(),BorderLayout.CENTER);
		
		mainPanel.add(graphicPanel);		
		
		//Panel de datos de referencia
		JPanel referencePanel = new JPanel();
		referencePanel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		referencePanel.setBounds(645, 320, 342, 86);
		mainPanel.add(referencePanel);
		
		//Panel de Información adicional
		JPanel aditionalInfo = new JPanel();
		aditionalInfo.setBorder(new TitledBorder(null, "Información Adicional", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		aditionalInfo.setBounds(645, 410, 342, 86);
		mainPanel.add(aditionalInfo);
		
		//Define Buttons
		JButton btnDibujar = new JButton("Dibujar");
		btnDibujar.setBounds(850, 50, 140, 35);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(850, 90, 140, 35);
		mainPanel.setLayout(null);
		
		JButton btnDescription = new JButton("Descripci\u00F3n del m\u00E9todo");
		btnDescription.setBounds(850, 410, 140, 35);
		
		JButton buttonValues = new JButton("Tabla Constantes");
		buttonValues.setBounds(850, 450, 140, 35);
		
		//Add Buttons		
		mainPanel.add(btnLimpiar);
		mainPanel.add(btnDibujar);
		aditionalInfo.add(btnDescription);
		aditionalInfo.add(buttonValues);
		
		JLabel blanco = new JLabel("Ganancia");
		blanco.setBounds(16, 27, 24, 8);
		blanco.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/blanco.png")));
		
		JLabel rojo = new JLabel("New label");
		rojo.setBounds(16, 46, 24, 8);
		rojo.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/rojo.png")));
		
		JLabel azul = new JLabel("New label");
		azul.setBounds(16, 65, 24, 8);
		azul.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/azul.png")));
		
		JLabel lblGanancia = new JLabel("Ganancia");
		lblGanancia.setBounds(50, 24, 44, 14);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(50, 39, 51, 23);
		
		JLabel lblRectaTangenteAl = new JLabel("Recta tangente al punto de inflexi\u00F3n");
		lblRectaTangenteAl.setBounds(50, 62, 175, 14);
		referencePanel.setLayout(null);
		referencePanel.add(blanco);
		referencePanel.add(azul);
		referencePanel.add(rojo);
		referencePanel.add(lblGanancia);
		referencePanel.add(lblRespuesta);
		referencePanel.add(lblRectaTangenteAl);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/amarillo.png")));
		label.setBounds(191, 27, 24, 8);
		referencePanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/verde.png")));
		label_1.setBounds(191, 46, 24, 8);
		referencePanel.add(label_1);
		
		JLabel lblRetardol = new JLabel("Retardo (L)");
		lblRetardol.setBounds(225, 24, 62, 14);
		referencePanel.add(lblRetardol);
		
		JLabel lblCteDeTiempo = new JLabel("Cte. de tiempo (T)");
		lblCteDeTiempo.setBounds(225, 43, 88, 14);
		referencePanel.add(lblCteDeTiempo);
		
		/*************** Panel de valores de sintonización *****************/
		vControllerPanel = new JPanel();
		vControllerPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Valores Sintonización", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		vControllerPanel.setBounds(645, 207, 349, 110);
		vControllerPanel.setLayout(new BorderLayout(0, 0));
		
		vTableControllers = new JTable() ;
		vTableControllers.setModel( getModelValuesControllers() );
		vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
		TablaRender miRender = new TablaRender();
		vTableControllers.setDefaultRenderer(String.class, miRender);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(vTableControllers);
		
		vControllerPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.add(vControllerPanel);
		
		/************** Panel Valores L y T  *********************************/
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Valores de L y T", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(645, 124, 200, 70);
		panel_1.setLayout(new BorderLayout(0, 0));
		mainPanel.add(panel_1);
		
		JScrollPane paneltableTL = new JScrollPane();
		panel_1.add(paneltableTL, BorderLayout.CENTER);
		// Tabla de valores L y T
		tableTL = new JTable();
		tableTL.setModel( getTableLT() );
		tableTL.setRowSelectionAllowed(false);
		tableTL.setCellSelectionEnabled(true);
		paneltableTL.setViewportView(tableTL);
		
		
		//Constantes para ecuaciónes del metodo de Lopez
		final JTable constantTable = getConstantTable();
		
		buttonValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JDialog dialog =  new JDialog();
				dialog.setTitle("Constantes Metodo de Lopez");
				dialog.setBounds(100, 100, 500, 200);
			    dialog.add(new JScrollPane(constantTable));
			    dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				
			}
		});
		
		JLabel lblModeloAsumidoDe = new JLabel("Modelo asumido de la planta:");
		lblModeloAsumidoDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModeloAsumidoDe.setBounds(695, 243, 190, 19);
		mainPanel.add(lblModeloAsumidoDe);
		
		JLabel lblEcuacionImagen = new JLabel("");
		lblModeloAsumidoDe.setLabelFor(lblEcuacionImagen);
		ImageIcon img = new ImageIcon(Ventana.class.getResource("/iconos/ecuacion.png"));
		
		lblEcuacionImagen.setIcon(img);
		lblEcuacionImagen.setBounds(695, 443, 200, 200);
		mainPanel.add(lblEcuacionImagen);
		
		JLabel lblSoftwareEducativo = new JLabel("Software educativo");
		lblSoftwareEducativo.setBounds(10, 568, 94, 14);
		mainPanel.add(lblSoftwareEducativo);
		
		
		//Definición Input Table
		inputTable = new JTable();
		inputTable.setModel( getInputTableModel() );
		inputTable.getColumnModel().getColumn(0).setResizable(false);
		inputTable.getColumnModel().getColumn(1).setResizable(false);
		inputTable.setRowSelectionAllowed(false);
		inputTable.setCellSelectionEnabled(true);
		
		JScrollPane spInputTable = new JScrollPane();
		//spInputTable.setBounds(20, 20, 20, 20);
		
		JPanel jpInput = new JPanel();
		jpInput.setBorder(new TitledBorder(null,"Valores de Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpInput.setBounds(645, 40, 200, 70);
		jpInput.setLayout(new BorderLayout(0, 0));
		jpInput.add(spInputTable);
		
		//mainPanel.add(spInputTable);
		mainPanel.add(jpInput, BorderLayout.CENTER);
		
		spInputTable.setViewportView(inputTable);
		
		JLabel lblMtodoDeLa = new JLabel("M\u00E9todo de la curva de reacci\u00F3n");
		lblMtodoDeLa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtodoDeLa.setBounds(402, 11, 226, 20);
		mainPanel.add(lblMtodoDeLa);
		
		
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DescripcionMetodo dialog = new DescripcionMetodo(mensaje);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
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
						
						vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
						TablaRender miRender = new TablaRender();
						vTableControllers.setDefaultRenderer(String.class, miRender);
						vTableControllers.setDefaultRenderer(Double.class, miRender);
						
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
	}
	
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
		TablaRender miRender = new TablaRender();
		vTableControllers.setDefaultRenderer(String.class, miRender);
			
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
				
				new String[] {"Criterios","a", "b", "c", "d", "e", "f"} ) {
					private static final long serialVersionUID = 1L;
			});
		
		constantesMetodo.setRowSelectionAllowed(false);
		constantesMetodo.setCellSelectionEnabled(true);
		return constantesMetodo;
		}
	
	//Retorno table model de L y T 
	private DefaultTableModel getTableLT(){
		return new DefaultTableModel(
				new Object[][] {{null, null}},
				new String[] {"L", "T"});
	}
	
	//Retorno table model de L y T seteados
	private DefaultTableModel setTableLT(double vL, double vT){
		return new DefaultTableModel(
				new Object[][] {{redondear(vL), redondear(vT)}},
				new String[] {"L", "T"});
	}
	
	private DefaultTableModel getModelValuesControllers(){

		return new DefaultTableModel( new Object[][] {
						{"P", "0", "0", "0"},
						{"PI", "0", "0", "0"},
						{"PID", "0", "0", "0"},},
				new String[] {"Tipo de controlador", "Kp", "Ti", "Td"});

	}
	
	//Devuelvo valores calculados
	private DefaultTableModel setModelValuesControllers( double vL, double vT){
		
		return new DefaultTableModel( new Object[][] {
				{"P", redondear((vT/vL) * (1 + (vL/(3*vT)))), 0.0, 0.0},
				{"PI", redondear((vT/vL) * (0.9 + (vL/(12*vT)))), redondear((vL*(30*vT + 3*vL))/(9*vT + 20*vL)), 0.0},
				{"PID", redondear((vT/vL) * (4/3 + (vL/(4*vT)))), redondear((vL*(32*vT + 6*vL))/(13*vT + 8*vL)), redondear((4*vL*vT)/(11*vT+2*vL))}},
		new String[] {"Tipo de controlador", "Kp", "Ti", "Td"} );
		
	}
	
	
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
