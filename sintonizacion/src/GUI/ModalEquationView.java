package GUI;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ModalEquationView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ModalEquationView(JFrame frame, String title, String url) {
		
		super(frame,true);
		
		ImageIcon img = new ImageIcon(ModalEquationView.class.getResource(url));
		double minW = img.getIconWidth();
		double minH =img.getIconHeight();
		
		setTitle(title);
		
		setBounds(100, 100, (int)minW+20, (int)minH+40);
		
		/*Dimension d = new Dimension();
		d.setSize(minW+20, minH+40);
		this.setMinimumSize(d);*/
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel label = new JLabel();
		label.setIcon(img);
		label.setBounds(10, 10, (int) minW, (int) minH);
		contentPanel.add(label);
		
		/*JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptar.setBounds(165, 321, 89, 23);
		contentPanel.add(btnAceptar);*/
		
		/*
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setBackground(SystemColor.control);
		textPane.setText(mensaje);
		textPane.setBounds(62, 24, 317, 286);
		contentPanel.add(textPane);
		*/
	}
}
