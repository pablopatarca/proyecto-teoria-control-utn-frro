package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logicaLazoAbierto.GraficadorPrueba;

import java.awt.Font;

public class MethodPanelPrueba extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GraficadorPrueba graficador;
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
	public MethodPanelPrueba(final MainView ventana) {

				setBounds(100, 100, 1010, 632);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				graficador = new GraficadorPrueba();
				
				final JPanel grafico = new JPanel();
				grafico.setBounds(10, 42, 625, 504);
				grafico.setLayout(new BorderLayout(0, 0));
				grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
				
				final JButton btnDibujar = new JButton("Dibujar");
				btnDibujar.setBounds(659, 42, 162, 33);
				contentPane.setLayout(null);
				contentPane.add(grafico);
				contentPane.add(btnDibujar);
				
				JLabel lblMtodoDeLas = new JLabel("M\u00E9todo de las oscilaciones sostenidas");
				lblMtodoDeLas.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblMtodoDeLas.setBounds(36, 11, 597, 20);
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
				 * INSERTAR UNA CURVA
				 */
				btnDibujar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						btnDibujar.setEnabled(false);
						
						//Inserta el grafico
						/**
						 * DIBUJO EL GRAFICO
						 */
						
						graficador.insertCurve(5.3 , 2.3);
						
						grafico.removeAll();
						grafico.add(graficador.getDiagrama(),BorderLayout.CENTER);
						grafico.validate();
						
						//Inicia el ploteo
		  				graficador.iniciarGraficoCurva();
		  				
		  				graficador.insertarDerivada();
		  				
		  				btnDibujar.setEnabled(true);
					}
				});
	}
	public JPanel getMainPanel() {
		// TODO Auto-generated method stub
		return contentPane;
	}

}
