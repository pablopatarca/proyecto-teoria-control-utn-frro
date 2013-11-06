package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import logicaLazoCerrado.Graficador;
import logicaLazoCerrado.TablaRender;

import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class PanelOscilacionesSostenidas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Graficador graficador;
	private JTable valoresZieglerNichols;
	private JTable valoresKP;
	private String mensaje = "El M�todo consiste en obtener la respuesta de la se�al medida "
							+ "a una perturbaci�n (por ejemplo un pulso en el "
							+ "set point), quitando la acci�n del controlador derivativo "
							+ "y la del integral, es decir, aplicando solamente control "
							+ "proporcional."
							+ "\n\n"
							+ "Se observa la respuesta y si es amortiguada, se incrementa "
							+ "la ganancia hasta lograr OSCILACIONES SOSTENIDAS "
							+ "(oscilaci�n con amplitud constante)."
							+ "\n\n"
							+ "Una vez alcanzadas estas oscilaciones, se toman los valores "
							+ "de la ganancia para la cual se llego a dichas oscilaciones "
							+ "(ganancia cr�tica Kc) y del per�odo de las mismas (per�odo "
							+ "cr�tico Pc).";
	/**
	 * Create the panel.
	 */
	public PanelOscilacionesSostenidas(final Ventana ventana) {

				setBounds(100, 100, 1010, 632);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				graficador = new Graficador();
				
				final JPanel grafico = new JPanel();
				grafico.setBounds(10, 42, 625, 504);
				grafico.setLayout(new BorderLayout(0, 0));
				grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
				
				final JButton btnDibujar = new JButton("Dibujar");
				btnDibujar.setBounds(857, 49, 75, 33);
				contentPane.setLayout(null);
				contentPane.add(grafico);
				contentPane.add(btnDibujar);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(645, 239, 349, 47);
				contentPane.add(panel);
				
				JLabel rojo = new JLabel("New label");
				rojo.setBounds(120, 26, 24, 7);
				rojo.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/azul.png")));
				
				JLabel lblRespuesta = new JLabel("Respuesta");
				lblRespuesta.setBounds(152, 18, 51, 23);
				panel.setLayout(null);
				panel.add(rojo);
				panel.add(lblRespuesta);
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/rojo.png")));
				label.setBounds(10, 26, 24, 7);
				panel.add(label);
				
				JLabel lblEntrada = new JLabel("Entrada");
				lblEntrada.setBounds(44, 22, 46, 14);
				panel.add(lblEntrada);
				
				JLabel label_1 = new JLabel("");
				label_1.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/verde.png")));
				label_1.setBounds(238, 26, 24, 7);
				panel.add(label_1);
				
				JLabel lblPerodoCrtico = new JLabel("Per\u00EDodo cr\u00EDtico");
				lblPerodoCrtico.setBounds(272, 22, 67, 14);
				panel.add(lblPerodoCrtico);
				
				JPanel ziegerynichols = new JPanel();
				ziegerynichols.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Valores propuestos por Ziegler y Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				ziegerynichols.setBounds(645, 314, 349, 188);
				contentPane.add(ziegerynichols);
				ziegerynichols.setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 98, 329, 79);
				ziegerynichols.add(scrollPane);
				
				valoresZieglerNichols = new JTable();
				valoresZieglerNichols.setModel(new DefaultTableModel(
					new Object[][] {{"P", "0.5 Kcr", "0", "0"},
									{"PI", "0.45 Kcr", "(1/1.2) Pcr", "0"},
									{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"} },
					new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}) {

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
				});
				valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
				
				TablaRender miRender = new TablaRender();
				valoresZieglerNichols.setDefaultRenderer(Double.class, miRender);
				valoresZieglerNichols.setDefaultRenderer(String.class, miRender);
				
				scrollPane.setViewportView(valoresZieglerNichols);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(99, 27, 154, 46);
				ziegerynichols.add(scrollPane_1);
				
				valoresKP = new JTable();
				valoresKP.setModel(new DefaultTableModel(
					new Object[][] {null, null},
					new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}) {

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
					}
				});
				valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
				valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
				
				valoresKP.setDefaultRenderer(Double.class, miRender);
				
				scrollPane_1.setViewportView(valoresKP);
				
				String[] valoresComboBox = {"Seleccionar FT", "Funci�n transf. 1", "Funci�n transf. 2", "Funci�n transf. 3"};
				
				final JComboBox<String> funcionesTransferencias = new JComboBox<String>(valoresComboBox);
				funcionesTransferencias.setBounds(715, 55, 116, 20);
				contentPane.add(funcionesTransferencias);
				
				JPanel ftPanel = new JPanel();
				ftPanel.setBorder(new TitledBorder(null, "Funci\u00F3n de transferencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				ftPanel.setBounds(645, 116, 348, 112);
				contentPane.add(ftPanel);
				ftPanel.setLayout(null);
				
				final JLabel ftLabel = new JLabel("");
				ftLabel.setBounds(55, 23, 251, 79);
				ftPanel.add(ftLabel);
				
				JLabel lblSoftwareEducativo = new JLabel("Software educativo");
				lblSoftwareEducativo.setBounds(10, 561, 94, 14);
				contentPane.add(lblSoftwareEducativo);
				
				JLabel lblVersin = new JLabel("Versi\u00F3n 1.2");
				lblVersin.setBounds(940, 561, 54, 14);
				contentPane.add(lblVersin);
				
				JLabel lblMtodoDeLas = new JLabel("M\u00E9todo de las oscilaciones sostenidas");
				lblMtodoDeLas.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblMtodoDeLas.setBounds(390, 11, 271, 20);
				contentPane.add(lblMtodoDeLas);
				
				JButton btnDescripcinDelMtodo = new JButton("Descripci\u00F3n del m\u00E9todo");
				btnDescripcinDelMtodo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DescripcionMetodo dialog = new DescripcionMetodo(mensaje);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(ventana);
					}
				});
				btnDescripcinDelMtodo.setBounds(851, 513, 143, 33);
				contentPane.add(btnDescripcinDelMtodo);
				
				/**
				 * CAMBIAR IMAGEN DE FT
				 */
				funcionesTransferencias.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						graficador.pararTimer();
						switch(funcionesTransferencias.getSelectedIndex()) {
						case 0: ftLabel.setIcon(null); 
							valoresZieglerNichols.setModel(new DefaultTableModel(
									new Object[][] {{"P", "0.5 Kcr", "0", "0"},
											{"PI", "0.45 Kcr", "(1/1.2) Pcr", "0"},
											{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"}},
									new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}) {

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
								});
								valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
								valoresKP.setModel(new DefaultTableModel(
										new Object[][] {null, null},
										new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}) {

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
										}
									});
									valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
									valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
								break;
						case 1: ftLabel.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/ft1.png"))); break;
						case 2: ftLabel.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/ft2.png"))); break;
						case 3: ftLabel.setIcon(new ImageIcon(Ventana.class.getResource("/iconos/ft3.png"))); break;
						}
					}
				});

				/**
				 * INSERTAR UNA CURVA
				 */
				btnDibujar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(funcionesTransferencias.getSelectedIndex() != 0) {
							valoresZieglerNichols.setModel(new DefaultTableModel(
									new Object[][] {{null, null, "0", "0"},
											{null, null, null, "0"},
											{null, null, null, null}},
									new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}) {
									/**
									 * 
									 */
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
								});
								valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
								valoresKP.setModel(new DefaultTableModel(
										new Object[][] {{null, null}},
										new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}) {
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
										}
									});
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
	}
	public JPanel getContentPane() {
		// TODO Auto-generated method stub
		return contentPane;
	}

}
