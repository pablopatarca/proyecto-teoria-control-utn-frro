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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ModalMethodDescription extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ModalMethodDescription(JFrame frame, String mensaje) {
		
		super(frame,true);
		
		setTitle("Descripci\u00F3n del m\u00E9todo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModalMethodDescription.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
		setResizable(false);
		setBounds(100, 100, 429, 384);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ModalMethodDescription.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
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
