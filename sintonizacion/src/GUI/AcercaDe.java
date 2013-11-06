package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String mensaje = "Este software educativo de c�digo libre surge "
			+ "con el prop�sito de ayudar al dictado de las "
			+ "clases de la materia de Teor�a de Control, "
			+ "del 4� a�o de la carrera de Ingenier�a en "
			+ "Sistemas de Informaci�n de la Universidad "
			+ "Tecnol�gica Nacional de Rosario, Santa Fe. "
			+ "\n\n"
			+ "Con un dise�o completamente implementado por "
			+ "el alumno Adri�n Benjuya (legajo: 38569), �sta "
			+ "aplicaci�n pretende asistir a los cursantes de "
			+ "Teor�a de Control a comprender de manera m�s "
			+ "eficiente los conceptos de sintonizaci�n de controladores "
			+ "\n\n"
			+ "El surgimiento y puesta en marcha de la idea "
			+ "de incluir un software como material de "
			+ "aprendizaje fue del Ing. Dar�o Weitz "
			+ "(hoy jefe de c�tedra de Teor�a de "
			+ "Control) al ver que el correcto dictamen de "
			+ "la materia se ve�a afectado por el lapso de "
			+ "tiempo con el que cuenta para desarrollar todos "
			+ "los temas que abarca la misma. "
			+ "\n\n"
			+ "Con la ayuda de varios alumnos con conocimientos "
			+ "en programaci�n, actualmente se cuenta con la "
			+ "mayor�a de los temas implementados en un paquete "
			+ "software, desarrollado en C#, y se pretende seguir "
			+ "agrandando el mismo hasta cumplir con el objetivo "
			+ "de ense�ar �ntegramente la materia y permitir al "
			+ "alumno finalizar el cursado con un conocimiento "
			+ "m�s amplio sobre los fundamentos de control. "
			+ "\n\n"
			+ "\t\t\t\tOctubre 2013";

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setTitle("Acerca del software educativo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DescripcionMetodo.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
		setResizable(false);
		setBounds(100, 100, 491, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(DescripcionMetodo.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		label.setBounds(20, 21, 32, 43);
		contentPanel.add(label);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptar.setBounds(197, 398, 89, 23);
		contentPanel.add(btnAceptar);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPane.setBackground(SystemColor.control);
		textPane.setText(mensaje);
		textPane.setBounds(62, 24, 384, 366);
		contentPanel.add(textPane);

	}

}
