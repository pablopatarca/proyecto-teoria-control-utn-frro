package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class DescripcionMetodo extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DescripcionMetodo(String mensaje) {
		setTitle("Descripci\u00F3n del m\u00E9todo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DescripcionMetodo.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
		setResizable(false);
		setBounds(100, 100, 429, 384);
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
		btnAceptar.setBounds(165, 321, 89, 23);
		contentPanel.add(btnAceptar);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setBackground(SystemColor.control);
		textPane.setText(mensaje);
		textPane.setBounds(62, 24, 317, 286);
		contentPanel.add(textPane);
	}
}
