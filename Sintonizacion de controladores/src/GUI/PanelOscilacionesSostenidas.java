package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import lógicaSegundoMetodo.TablaRender;
import lógicaSegundoMetodo.Curva3;
import lógicaSegundoMetodo.Graficador;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class PanelOscilacionesSostenidas extends JPanel {

	private JPanel contentPane;
	private Graficador graficador;
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
					new Object[][] {
							{"P", "0.5 Kcr", "0", "0"},
							{"PI", "0.45 Kcr", "(1/1.2) Pcr", "0"},
							{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"},
					},
					new String[] {
						"Tipo de controlador", "Kp", "Ti", "Td"
					}
				) {
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
					new Object[][] {
						{null, null},
					},
					new String[] {
						"K cr\u00EDtica", "P cr\u00EDtico"
					}
				) {
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
				
				String[] valoresComboBox = {"Seleccionar FT", "Función transf. 1", "Función transf. 2", "Función transf. 3"};
				
				final JComboBox<String> funcionesTransferencias = new JComboBox<String>(/*valoresComboBox*/);
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
									new Object[][] {
											{"P", "0.5 Kcr", "0", "0"},
											{"PI", "0.45 Kcr", "(1/1.2) Pcr", "0"},
											{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"},
									},
									new String[] {
										"Tipo de controlador", "Kp", "Ti", "Td"
									}
								) {
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
										new Object[][] {
											{null, null},
										},
										new String[] {
											"K cr\u00EDtica", "P cr\u00EDtico"
										}
									) {
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
									new Object[][] {
											{null, null, "0", "0"},
											{null, null, null, "0"},
											{null, null, null, null},
									},
									new String[] {
										"Tipo de controlador", "Kp", "Ti", "Td"
									}
								) {
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
										new Object[][] {
											{null, null},
										},
										new String[] {
											"K cr\u00EDtica", "P cr\u00EDtico"
										}
									) {
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
