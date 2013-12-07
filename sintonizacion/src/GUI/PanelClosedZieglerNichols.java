package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logicCloseLoop.Grapher;

public class PanelClosedZieglerNichols extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int band=0;
	private JPanel mainPanel;
	private Grapher graficador;
	private JTable valoresZieglerNichols;
	private JTable valoresKP;
	private String mensaje = "El Método consiste en obtener la respuesta de la señal medida "
							+ "a una perturbación (por ejemplo un pulso en el "
							+ "set point), quitando la acción del controlador derivativo "
							+ "y la del integral, es decir, aplicando solamente control "
							+ "proporcional."
							+ "\n\n"
							+ "Se observa la respuesta y si es amortiguada, se incrementa "
							+ "la ganancia hasta lograr OSCILACIONES SOSTENIDAS "
							+ "(oscilación con amplitud constante)."
							+ "\n\n"
							+ "Una vez alcanzadas estas oscilaciones, se toman los valores "
							+ "de la ganancia para la cual se llego a dichas oscilaciones "
							+ "(ganancia crítica Kc) y del período de las mismas (período "
							+ "crítico Pc).";
	/**
	 * Create the panel.
	 */
	public PanelClosedZieglerNichols(final MainView ventana) {
		
		int marginTop = 30;
		int marginRight = 5;
		int marginRight2 = 635;
		
		String headTitle = "M\u00E9todo de Ziegler y Nichols de oscilaciones sostenidas";
		
		graficador = new Grapher();

		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		
/****** Define Panels************************/
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(647, 386, 349, 47);
		mainPanel.add(panel);
		
		final JPanel grafico = new JPanel();
		grafico.setBounds(5, marginTop, 625, 530);
		grafico.setLayout(new BorderLayout(0, 0));
		grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
		mainPanel.add(grafico);
		
		JPanel ftPanel = new JPanel();
		ftPanel.setBorder(new TitledBorder(null, "Funci\u00F3n de transferencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ftPanel.setBounds(marginRight2, 70, 250, 90);
		mainPanel.add(ftPanel);
		ftPanel.setLayout(null);
		
		JPanel ziegerynichols = new JPanel();
		ziegerynichols.setBorder(new TitledBorder(null, "Valores propuestos por Ziegler y Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ziegerynichols.setBounds(marginRight2, 206, 349, 178);
		mainPanel.add(ziegerynichols);
		ziegerynichols.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 329, 81);
		ziegerynichols.add(scrollPane);
		
		valoresZieglerNichols = new JTable();
		valoresZieglerNichols.setModel(new DefaultTableModel(
			new Object[][] {{"P", "0.5 Kcr", null, null},
							{"PI", "0.45 Kcr", "(1/1.2) Pcr", null},
							{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"}},
			new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}));
		valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
		
		scrollPane.setViewportView(valoresZieglerNichols);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(99, 23, 154, 50);
		ziegerynichols.add(scrollPane_1);
		
		valoresKP = new JTable();
		valoresKP.setModel(new DefaultTableModel(
			new Object[][] {{null, null}},
			new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}){
			
			private static final long serialVersionUID = 1L;
			
			Class[] columnTypes = new Class[] {
				String.class, String.class
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
		});
		valoresKP.setFocusTraversalKeysEnabled(false);
		valoresKP.setFocusable(false);
		valoresKP.setRowSelectionAllowed(false);
		valoresKP.setCellSelectionEnabled(true);
		valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
		valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
		
		scrollPane_1.setViewportView(valoresKP);
		
		
		
/****** Define JLabels **********************/
		
		
		
		final JLabel ftLabel = new JLabel("");
		ftLabel.setBounds(0, 15, 250, 70);
		ftPanel.add(ftLabel);
		
		JLabel labelTitle = new JLabel(headTitle);
		labelTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelTitle.setBounds(marginRight, 5, 500, 20);
		mainPanel.add(labelTitle);
		
		JLabel rojo = new JLabel("New label");
		rojo.setBounds(120, 26, 24, 7);
		rojo.setIcon(new ImageIcon(MainView.class.getResource("/icons/azul.png")));
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(152, 18, 51, 23);
		panel.setLayout(null);
		panel.add(rojo);
		panel.add(lblRespuesta);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainView.class.getResource("/icons/rojo.png")));
		label.setBounds(10, 26, 24, 7);
		panel.add(label);
		
		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setBounds(44, 22, 46, 14);
		panel.add(lblEntrada);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainView.class.getResource("/icons/verde.png")));
		label_1.setBounds(238, 26, 24, 7);
		panel.add(label_1);
		
		JLabel lblPerodoCrtico = new JLabel("Per\u00EDodo cr\u00EDtico");
		lblPerodoCrtico.setBounds(272, 22, 67, 14);
		panel.add(lblPerodoCrtico);
		
		
/****** JButtons **************/
		
		String[] valoresComboBox = {"Seleccionar FT", "Función transf. 1", "Función transf. 2", "Función transf. 3"};
		
		final JComboBox<String> funcionesTransferencias = new JComboBox<String>(valoresComboBox);
		funcionesTransferencias.setBounds(marginRight2, marginTop, 170, 30);
		mainPanel.add(funcionesTransferencias);
		
		final JButton btnDibujar = new JButton("  Graficar",new ImageIcon(MethodPanelView.class.getResource("/icons/icon_graficar.png")));
		btnDibujar.setHorizontalAlignment(SwingConstants.LEFT);
		btnDibujar.setBounds(marginRight2+250, marginTop, 140, 40);
		mainPanel.setLayout(null);
		mainPanel.add(btnDibujar);
		
		final JButton btnSave = new JButton("  Guardar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_guardar.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setBounds(marginRight2+250, marginTop+45, 140, 40);
		mainPanel.setLayout(null);
		mainPanel.add(btnSave);
		
		final JButton btnStop = new JButton("  Parar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_cancelar.png")));
		btnStop.setHorizontalAlignment(SwingConstants.LEFT);
		btnStop.setBounds(marginRight2+250, marginTop+89, 140, 40);
		mainPanel.setLayout(null);
		mainPanel.add(btnStop);
		
		
		JButton btnDescripcinDelMtodo = new JButton("Descripci\u00F3n m\u00E9todo", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnDescripcinDelMtodo.setHorizontalAlignment(SwingConstants.LEFT);
		btnDescripcinDelMtodo.setBounds(647, 452, 240, 40);
		mainPanel.add(btnDescripcinDelMtodo);
		
		
		btnDescripcinDelMtodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(ventana, mensaje);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
			}
		});
		
		/**
		 * CAMBIAR IMAGEN DE FT
		 */


		funcionesTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				graficador.stop();
				switch(funcionesTransferencias.getSelectedIndex()) {
				case 0: ftLabel.setIcon(null); 
					valoresZieglerNichols.setModel(new DefaultTableModel(
							new Object[][] {{"P", "0.5 Kcr", null, null},
									{"PI", "0.45 Kcr", "(1/1.2) Pcr", null},
									{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"}},
							new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}));
						valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
						valoresKP.setModel(new DefaultTableModel(
								new Object[][] {null, null},
								new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}));
							valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
							valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
						break;
						
				case 1: ftLabel.setIcon(new ImageIcon(MainView.class.getResource("/icons/ft1.png"))); break;
				case 2: ftLabel.setIcon(new ImageIcon(MainView.class.getResource("/icons/ft2.png"))); break;
				case 3: ftLabel.setIcon(new ImageIcon(MainView.class.getResource("/icons/ft3.png"))); break;
				}
			}
		});

		/**
		 * INSERTAR UNA CURVA
		 */
		btnDibujar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(funcionesTransferencias.getSelectedIndex() != 0) {
					
					band = 1;
					
					valoresZieglerNichols.setModel(new DefaultTableModel(
							new Object[][] {{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null}},
							new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}));
						valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
						valoresKP.setModel(new DefaultTableModel(
								new Object[][] {{null, null}},
								new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}));
							valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
							valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
					btnDibujar.setEnabled(false);
					funcionesTransferencias.setEnabled(false);
					graficador.insertarCurva(funcionesTransferencias.getSelectedIndex());
					/**
					 * DIBUJO EL GRAFICO
					 */
					grafico.removeAll();
					grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
					grafico.validate();
	  				graficador.iniciarGraficoCurva(valoresZieglerNichols, valoresKP, btnDibujar, funcionesTransferencias);
				}
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
		
		//Stop button
		btnStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(band == 1){
						btnStop.setIcon(new ImageIcon(MethodPanelView.class.getResource("/icons/icon_aceptar.png")));
						btnStop.setText(" Continuar");
						graficador.stop();
						band = 2;
					}else if(band == 2){
						btnStop.setIcon(new ImageIcon(MethodPanelView.class.getResource("/icons/icon_cancelar.png")));
						btnStop.setText(" Parar");
						graficador.start();
						band = 1;
					}
			}
		});
		
	};
	
	
			
	public JPanel getMainPanel() {
		// TODO Auto-generated method stub
		return mainPanel;
	}

}
