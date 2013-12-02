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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import logicOpenLoop.CurveGenerator;
import logicOpenLoop.Grapher;

public class MethodPanelView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private String method;
	private JPanel mainPanel; //Panel principal
	private Grapher graficador;
	private InformationExpert dataTables;
	private JTable inputTable;
	private JTable vTableControllers;
	private JTable tableTL;
	boolean band=true;
	
	/**
	 * Create the panel constructor.
	 */

	public MethodPanelView(final MainView ventana, String paramM) {
		
		method = paramM;
		
		dataTables = new InformationExpert();
		
		int marginTop = 30;
		int marginRight = 5;
		int marginRight2 = 645;
		
		
		graficador = new Grapher();
		
		String headTitle = dataTables.getTitle(method);
		final String description = dataTables.getDescription(method);
		
		
		
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
		vTableControllers.setModel( dataTables.getModelValuesControllers() );
		vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
		//TablaRender miRender = new TablaRender();
		//vTableControllers.setDefaultRenderer(String.class, miRender);
		scrollPane.setViewportView(vTableControllers);
		
		// L y T table values
		tableTL = new JTable();
		tableTL.setModel( dataTables.getModelLT() );
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
		
		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(850, marginTop+80, 140, 33);
		
		JButton btnStop = new JButton("Parar");
		btnStop.setBounds(850, marginTop+120, 140, 33);
		
		JButton btnAssumedModel = new JButton("Modelo asumido");
		btnAssumedModel.setBounds(10, 22, 190, 25);
		btnAssumedModel.setSize(140, 35);
		
		JButton btnEquations = new JButton("Ecuaciones");
		btnEquations.setBounds(180, 22, 190, 25);
		btnEquations.setSize(140, 35);
		
		JButton btnConstants = new JButton("Constantes");
		btnConstants.setBounds(10, 69, 190, 25);
		btnConstants.setSize(140, 35);
		
		JButton btnDescription = new JButton("Descripci\u00F3n m\u00E9todo");
		btnDescription.setBounds(180, 69, 190, 25);
		btnDescription.setSize(140, 35);
		
		
		//Add Buttons to panels		
		mainPanel.add(btnLimpiar);
		mainPanel.add(btnDibujar);
		mainPanel.add(btnSave);
		mainPanel.add(btnStop);
		aditionalInfo.setLayout(null);
		aditionalInfo.add(btnAssumedModel);
		aditionalInfo.add(btnEquations);
		if(method=="lopez" || method=="ks"){
			aditionalInfo.add(btnConstants);
		}
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
		blanco.setIcon(new ImageIcon(MainView.class.getResource("/icons/blanco.png")));
		JLabel rojo = new JLabel("Red");
		rojo.setIcon(new ImageIcon(MainView.class.getResource("/icons/rojo.png")));
		JLabel azul = new JLabel("Blue");
		azul.setIcon(new ImageIcon(MainView.class.getResource("/icons/azul.png")));
		JLabel amarillo = new JLabel("Yelow");
		amarillo.setIcon(new ImageIcon(MainView.class.getResource("/icons/amarillo.png")));
		JLabel green = new JLabel("Green");
		green.setIcon(new ImageIcon(MainView.class.getResource("/icons/verde.png")));
	
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
		
		
		
		/*Add assumed model
		JLabel lblEcuacionImagen = new JLabel("");
		lblModeloAsumidoDe.setLabelFor(lblEcuacionImagen);
		ImageIcon img = new ImageIcon(Ventana.class.getResource("/icons/ecuacion.png"));
		
		lblEcuacionImagen.setIcon(img);
		lblEcuacionImagen.setBounds(695, 443, 200, 200);
		mainPanel.add(lblEcuacionImagen);
		*/
		
		
/*****************  Define Listeners  ***************************************/
		
		if(method=="lopez" || method=="ks"){
			//Constantes para ecuaciónes del metodo de Lopez o Kaya-sheib  **********
			//Show constants method
			btnConstants.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JDialog dialog =  new JDialog(ventana);
					dialog.setTitle("Constantes Metodo de Lopez");
				    dialog.getContentPane().add(new JScrollPane( dataTables.getConstantTable(method) ));
				    dialog.setVisible(true);
					dialog.setLocationRelativeTo(ventana);
					dialog.setBounds(100, 100, 500, 200);
					
				}
			});
		}
		
		//Show assumed model ******************************************
		btnAssumedModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ModalEquationView dialog = new ModalEquationView(ventana, "/icons/equationFirstOrder.png");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
			}
		});
		
		//Show controller equations ********************************
		btnEquations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ModalEquationView dialog = new ModalEquationView(ventana, "/icons/equationZieglerNichols.png");
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				dialog.setBounds(100, 100, 500, 200);
				
			}
		});
		
		//Show Description
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(ventana, description);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
				dialog.setBounds(100, 100, 500, 200);
			}
		});
		

		/**
		 * Insert Graphic
		 */
		btnDibujar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(inputTable.isEditing()){
					inputTable.getCellEditor().stopCellEditing();
				}
				inputTable.clearSelection();
				
				TableModel modelo = inputTable.getModel();
				
				System.out.println("a value:"+modelo.getValueAt(0, 0));
				System.out.println("b value:"+modelo.getValueAt(0, 1));
				
				//Empty Validate
				if(modelo.getValueAt(0, 1) != null && modelo.getValueAt(0, 0) != null){
				
					Pattern pat = Pattern.compile("^\\d+|^\\d+\\.?\\d+");
				     Matcher mat1 = pat.matcher((String) modelo.getValueAt(0, 0));
				     Matcher mat2 = pat.matcher((String) modelo.getValueAt(0, 1));
				     
				     //Number Validate
				     if (mat1.matches() && mat2.matches()) {
				         System.out.println("Are Numbers");
				         
				        double k = Double.parseDouble((String) "0"+modelo.getValueAt(0,0));
						double tau = Double.parseDouble((String) "0"+modelo.getValueAt(0,1));
					
					//Validate
					if(k > 0.0 && tau > 0.0) {
					
					
					/**
					 * Draw Graphic
					 */
					
					/*
					graficador.insertarCurva(k, tau);
					graphicPanel.removeAll();
					graphicPanel.add(graficador.getDiagrama(),BorderLayout.CENTER);
					graphicPanel.validate();
					*/
					
					graficador.insertCurve(k , tau);
					
					
					/**
					 * DIBUJO EL GRAFICO
					 */
					
					graphicPanel.removeAll();
					graphicPanel.add(graficador.getDiagrama(),BorderLayout.CENTER);
					graphicPanel.validate();
					
					//Inicia el ploteo
	  				graficador.iniciarGraficoCurva();
	  				
	  				graficador.insertarDerivada();
	  				
/****************************************************************************************/
					
					if(graficador.getCurvaActual() != null) {
						CurveGenerator curvaActual = graficador.getCurvaActual();
						
						double vT = curvaActual.getT();
						double vL = curvaActual.getL();
						
						
						vTableControllers.setModel(dataTables.getModelControllers(method, vL, vT, tau));
						vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
						
						tableTL.setModel(dataTables.getModelLT(vL, vT));
						
					}
				}
				else {
				JOptionPane.showMessageDialog(null, "Las constantes deben ser valores mayores que cero", 
						"Error", JOptionPane.ERROR_MESSAGE, null);
				}
			
				}else{
					JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos", 
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
		
		btnStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(band==true){
						graficador.stop();
						band=false;
					}else{
						graficador.start();
						band=true;
					}
			}
		});
		
		
	}
	
/**********  Application Methods ***************************************************/	
	
	private void limpiaGrafica(JPanel panel){
		inputTable.setModel( getInputTableModel() );
		graficador.clean();
		/**
		 * DIBUJO EL GRAFICO
		 */
		panel.removeAll();
		panel.add(graficador.getDiagrama(),BorderLayout.CENTER);
		panel.validate();
		
		vTableControllers.setModel( dataTables.getModelValuesControllers() );
		vTableControllers.getColumnModel().getColumn(0).setPreferredWidth(106);
			
		tableTL.setModel( dataTables.getModelLT() );
	}
	
	//Retorno Table Model de valores de entrada
		private DefaultTableModel getInputTableModel() {
			return new DefaultTableModel(
					new Object[][] {{null, null}},
					new String[] {"Ganancia", "Cte. Tiempo"});
		}
	
	
	//Retorno Panel Principal
	public JPanel getMainPanel() {
		return mainPanel;
	}

}