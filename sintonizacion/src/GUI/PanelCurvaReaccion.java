package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import logicOpenLoop.Curva;
import logicOpenLoop.Graficador;

public class PanelCurvaReaccion extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Graficador graficador;
	private JTable tablaGraficos;
	private JTable valoresZieglerNichols;
	private JTable valoresTL;
	private String mensaje = "El M�todo consiste en obtener la respuesta de la se�al medida "
			+ "a una entrada escal�n en un sistema de lazo abierto. Si la planta no "
			+ "contiene integradores ni polos dominantes complejos conjugados, la curva "
			+ "puede tener la forma de una S (si la respuesta no exhibe esta forma de S, "
			+ "este m�todo no es pertinente)."
			+ "\n\n"
			+ "Tales curvas de respuesta se generan experimentalmente o a partir de una "
			+ "simulaci�n din�mina de la planta y est�n caracterizadas por dos par�metros: "
			+ "el tiempo de retardo L y la constante de tiempo T. Estos par�metros se "
			+ "determinan dibujando una recta tangente en el punto de inflexi�n de la curva "
			+ "y determinando las intersecciones de esta tangente con el eje del tiempo "
			+ "y la l�nea Y(t) = K, es decir, la ganancia aplicada.";
	/**
	 * Create the panel.
	 */
	
	public PanelCurvaReaccion(final MainView ventana) {
		
		setBounds(100, 100, 1005, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		graficador = new Graficador();
		
		final JPanel grafico = new JPanel();
		grafico.setBounds(10, 41, 625, 510);
		grafico.setLayout(new BorderLayout(0, 0));
		grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
		
		final JButton btnDibujar = new JButton("Dibujar");
		btnDibujar.setBounds(896, 41, 75, 33);
		
		final JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(898, 80, 73, 33);
		contentPane.setLayout(null);
		contentPane.add(grafico);
		contentPane.add(btnLimpiar);
		contentPane.add(btnDibujar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(645, 320, 342, 86);
		contentPane.add(panel);
		
		JLabel blanco = new JLabel("Ganancia");
		blanco.setBounds(16, 27, 24, 8);
		blanco.setIcon(new ImageIcon(MainView.class.getResource("/icons/blanco.png")));
		
		JLabel rojo = new JLabel("New label");
		rojo.setBounds(16, 46, 24, 8);
		rojo.setIcon(new ImageIcon(MainView.class.getResource("/icons/rojo.png")));
		
		JLabel azul = new JLabel("New label");
		azul.setBounds(16, 65, 24, 8);
		azul.setIcon(new ImageIcon(MainView.class.getResource("/icons/azul.png")));
		
		JLabel lblGanancia = new JLabel("Ganancia");
		lblGanancia.setBounds(50, 24, 44, 14);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(50, 39, 51, 23);
		
		JLabel lblRectaTangenteAl = new JLabel("Recta tangente al punto de inflexi\u00F3n");
		lblRectaTangenteAl.setBounds(50, 62, 175, 14);
		panel.setLayout(null);
		panel.add(blanco);
		panel.add(azul);
		panel.add(rojo);
		panel.add(lblGanancia);
		panel.add(lblRespuesta);
		panel.add(lblRectaTangenteAl);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainView.class.getResource("/icons/amarillo.png")));
		label.setBounds(191, 27, 24, 8);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainView.class.getResource("/icons/verde.png")));
		label_1.setBounds(191, 46, 24, 8);
		panel.add(label_1);
		
		JLabel lblRetardol = new JLabel("Retardo (L)");
		lblRetardol.setBounds(225, 24, 62, 14);
		panel.add(lblRetardol);
		
		JLabel lblCteDeTiempo = new JLabel("Cte. de tiempo (T)");
		lblCteDeTiempo.setBounds(225, 43, 88, 14);
		panel.add(lblCteDeTiempo);
		
		JPanel ziegerynichols = new JPanel();
		ziegerynichols.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Valores propuestos por Ziegler y Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ziegerynichols.setBounds(645, 207, 349, 99);
		contentPane.add(ziegerynichols);
		ziegerynichols.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		ziegerynichols.add(scrollPane, BorderLayout.CENTER);
		
		valoresZieglerNichols = new JTable();
		valoresZieglerNichols.setModel(new DefaultTableModel(
			new Object[][] {
					{"P", "T/L", "0", "0"},
					{"PI", "0.9(T/L)", "L/0.03", "0"},
					{"PID", "1.2(T/L)", "2L", "0.5L"},
			},
			new String[] {
				"Tipo de controlador", "Kp", "Ti", "Td"
			}
		) {
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
		
		scrollPane.setViewportView(valoresZieglerNichols);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Valores de L y T", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(645, 124, 173, 66);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane panelValoresTL = new JScrollPane();
		panel_1.add(panelValoresTL, BorderLayout.CENTER);
		
		valoresTL = new JTable();
		valoresTL.setModel(new DefaultTableModel(
			new Object[][] {null, null},new String[] {"L", "T"} ) {
			
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
		panelValoresTL.setViewportView(valoresTL);
		valoresTL.setRowSelectionAllowed(false);
		valoresTL.setCellSelectionEnabled(true);
		
		JLabel lblModeloAsumidoDe = new JLabel("Modelo asumido de la planta:");
		lblModeloAsumidoDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModeloAsumidoDe.setBounds(695, 460, 190, 19);
		contentPane.add(lblModeloAsumidoDe);
		
		JLabel lblEcuacionImagen = new JLabel("");
		lblModeloAsumidoDe.setLabelFor(lblEcuacionImagen);
		//lblEcuacionImagen.setIcon(new ImageIcon(MainView.class.getResource("/icons/ecuacion.png")));
		lblEcuacionImagen.setBounds(894, 443, 58, 51);
		contentPane.add(lblEcuacionImagen);
		
		JLabel lblSoftwareEducativo = new JLabel("Software educativo");
		lblSoftwareEducativo.setBounds(10, 568, 94, 14);
		contentPane.add(lblSoftwareEducativo);
		
		JLabel lblVersin = new JLabel("Versi\u00F3n 1.2");
		lblVersin.setBounds(936, 568, 58, 14);
		contentPane.add(lblVersin);
		
		final JScrollPane graficos = new JScrollPane();
		graficos.setBounds(700, 49, 186, 45);
		contentPane.add(graficos);
		
		tablaGraficos = new JTable();
		tablaGraficos.setModel(new DefaultTableModel(
			new Object[][] {null, null},new String[] {"Ganancia", "Cte. de tiempo"}) {
			
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaGraficos.getColumnModel().getColumn(0).setResizable(false);
		tablaGraficos.getColumnModel().getColumn(0).setPreferredWidth(58);
		tablaGraficos.getColumnModel().getColumn(1).setResizable(false);
		tablaGraficos.getColumnModel().getColumn(1).setPreferredWidth(98);
		
		tablaGraficos.setRowSelectionAllowed(false);
		tablaGraficos.setCellSelectionEnabled(true);
		
		graficos.setViewportView(tablaGraficos);
		
		JLabel lblMtodoDeLa = new JLabel("M\u00E9todo de la curva de reacci\u00F3n");
		lblMtodoDeLa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtodoDeLa.setBounds(402, 11, 226, 19);
		contentPane.add(lblMtodoDeLa);
		
		JButton btnDescripcinDelMtodo = new JButton("Descripci\u00F3n del m\u00E9todo");
		btnDescripcinDelMtodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(ventana, mensaje);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(ventana);
			}
		});
		btnDescripcinDelMtodo.setBounds(844, 518, 143, 33);
		contentPane.add(btnDescripcinDelMtodo);
				
		/**
		 * INSERTAR UNA CURVA
		 */
		btnDibujar.addActionListener(new ActionListener() {
			
			private double redondear(double numero) {
				return (Math.rint(numero*100)/100);
			}
			
			public void actionPerformed(ActionEvent arg0) {
					if(tablaGraficos.isEditing()){
						tablaGraficos.getCellEditor().stopCellEditing();
					}
					tablaGraficos.clearSelection();
					TableModel modelo = tablaGraficos.getModel();
					
					if(modelo.getValueAt(0, 1) != null && modelo.getValueAt(0, 0) != null && (double)modelo.getValueAt(0, 1) >= 0.0 && (double)modelo.getValueAt(0, 0) >= 0.0) {
						double k = (double)modelo.getValueAt(0, 0);
						double tau = (double)modelo.getValueAt(0, 1);
							//graficador.insertarCurva(k, tau, 1);
							/**
							 * DIBUJO EL GRAFICO
							 */
							grafico.removeAll();
							grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
							grafico.validate();
						if(graficador.getCurvaActual() != null) {
							Curva curvaActual = graficador.getCurvaActual();
							
							valoresZieglerNichols.setModel(new DefaultTableModel(
									new Object[][] {
											{"P", redondear(curvaActual.getT()/curvaActual.getL()), 0.0, 0.0},
											{"PI", redondear(0.9*(curvaActual.getT()/curvaActual.getL())), redondear(curvaActual.getL()/0.03), 0.0},
											{"PID", redondear(1.2*(curvaActual.getT()/curvaActual.getL())), redondear(2*curvaActual.getL()), redondear(0.5*curvaActual.getL())}},
									new String[] {"Tipo de controlador", "Kp", "Ti", "Td"} ) {
									/**
									 * 
									 */
									private static final long serialVersionUID = 1L;
									Class[] columnTypes = new Class[] {
										String.class, Double.class, Double.class, Double.class
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
								
								valoresTL.setModel(new DefaultTableModel(
										new Object[][] {{redondear(curvaActual.getL()), redondear(curvaActual.getT())}},
										new String[] {"L", "T"}) {

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
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Es necesario ingresar un valor de constante de tiempo y de ganancia distintos de cero", "Error", JOptionPane.ERROR_MESSAGE, null);
					}
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaGraficos.getModel().setValueAt(null, 0, 0);
				tablaGraficos.getModel().setValueAt(null, 0, 1);
				graficador.limpiar();
				/**
				 * DIBUJO EL GRAFICO
				 */
				grafico.removeAll();
				grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
				grafico.validate();
				
				
				valoresZieglerNichols.setModel(new DefaultTableModel(
						new Object[][] {{"P", "T/L", "0", "0"},
								{"PI", "0.9(T/L)", "L/0.03", "0"},
								{"PID", "1.2(T/L)", "2L", "0.5L"}},
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
					
					valoresTL.setModel(new DefaultTableModel(
							new Object[][] {{null, null}},
							new String[] {"L", "T"}) {
							/**
							 * 
							 */
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
			}
		});
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
}
