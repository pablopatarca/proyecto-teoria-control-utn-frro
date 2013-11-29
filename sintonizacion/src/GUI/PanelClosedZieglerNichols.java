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
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

import logicCloseLoop.Graficador;
import logicCloseLoop.TablaRender;

public class PanelClosedZieglerNichols extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Graficador graficador;
	private JTable valoresZieglerNichols;
	private JTable valoresKP;
	private String mensaje = "El Método consiste en obtener la respuesta de la se�al medida "
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

				setBounds(100, 100, 1010, 632);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				graficador = new Graficador();
				
				final JPanel grafico = new JPanel();
				grafico.setBounds(10, 42, 625, 504);
				grafico.setLayout(new BorderLayout(0, 0));
				grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
				
				final JButton btnDibujar = new JButton("Dibujar");
				btnDibujar.setBounds(832, 49, 162, 33);
				contentPane.setLayout(null);
				contentPane.add(grafico);
				contentPane.add(btnDibujar);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Referencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(647, 386, 349, 47);
				contentPane.add(panel);
				
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
				
				JPanel ziegerynichols = new JPanel();
				ziegerynichols.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Valores propuestos por Ziegler y Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				ziegerynichols.setBounds(647, 206, 349, 178);
				contentPane.add(ziegerynichols);
				ziegerynichols.setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 85, 329, 81);
				ziegerynichols.add(scrollPane);
				
				valoresZieglerNichols = new JTable();
				valoresZieglerNichols.setModel(new DefaultTableModel(
					new Object[][] {{"P", "0.5 Kcr", "0", "0"},
									{"PI", "0.45 Kcr", "(1/1.2) Pcr", "0"},
									{"PID", "0.6 Kcr", "0.5 Pcr", "0.125 Pcr"} },
					new String[] {"Tipo de controlador", "Kp", "Ti", "Td"}));
				valoresZieglerNichols.getColumnModel().getColumn(0).setPreferredWidth(106);
				
				TablaRender miRender = new TablaRender();
				valoresZieglerNichols.setDefaultRenderer(Double.class, miRender);
				valoresZieglerNichols.setDefaultRenderer(String.class, miRender);
				
				scrollPane.setViewportView(valoresZieglerNichols);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(99, 23, 154, 50);
				ziegerynichols.add(scrollPane_1);
				
				valoresKP = new JTable();
				valoresKP.setModel(new DefaultTableModel(
					new Object[][] {null, null},
					new String[] {"K cr\u00EDtica", "P cr\u00EDtico"}));
				valoresKP.getColumnModel().getColumn(0).setPreferredWidth(51);
				valoresKP.getColumnModel().getColumn(1).setPreferredWidth(51);
				
				valoresKP.setDefaultRenderer(Double.class, miRender);
				
				scrollPane_1.setViewportView(valoresKP);
				
				String[] valoresComboBox = {"Seleccionar FT", "Función transf. 1", "Función transf. 2", "Función transf. 3"};
				
				final JComboBox<String> funcionesTransferencias = new JComboBox<String>(valoresComboBox);
				funcionesTransferencias.setBounds(650, 55, 170, 20);
				contentPane.add(funcionesTransferencias);
				
				JPanel ftPanel = new JPanel();
				ftPanel.setBorder(new TitledBorder(null, "Funci\u00F3n de transferencia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				ftPanel.setBounds(646, 94, 348, 112);
				contentPane.add(ftPanel);
				ftPanel.setLayout(null);
				
				final JLabel ftLabel = new JLabel("");
				ftLabel.setBounds(55, 23, 251, 79);
				ftPanel.add(ftLabel);
				
				JLabel lblMtodoDeLas = new JLabel("M\u00E9todo de Ziegler y Nichols de oscilaciones sostenidas");
				lblMtodoDeLas.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblMtodoDeLas.setBounds(5, 5, 597, 20);
				contentPane.add(lblMtodoDeLas);
				
				JButton btnDescripcinDelMtodo = new JButton("Descripci\u00F3n del m\u00E9todo");
				btnDescripcinDelMtodo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModalMethodDescription dialog = new ModalMethodDescription(ventana, mensaje);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(ventana);
					}
				});
				btnDescripcinDelMtodo.setBounds(647, 452, 200, 33);
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
							valoresZieglerNichols.setModel(new DefaultTableModel(
									new Object[][] {{null, null, "0", "0"},
											{null, null, null, "0"},
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
	}
	public JPanel getMainPanel() {
		// TODO Auto-generated method stub
		return contentPane;
	}

}
