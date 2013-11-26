package GUI;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class PanelCompare extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public PanelCompare(final MainView ventana) {
		setLayout(null);
		
		JLabel lblComparacinDeMetodos = new JLabel("Comparación de metodos - Sistemas de lazo abierto");
		lblComparacinDeMetodos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblComparacinDeMetodos.setBounds(12, 0, 578, 34);
		add(lblComparacinDeMetodos);
		
		JPanel panelZN = new JPanel();
		panelZN.setBorder(new TitledBorder(null, "Ziegler - Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelZN.setToolTipText("");
		panelZN.setBounds(12, 34, 965, 150);
		add(panelZN);
		
		JPanel panelCC = new JPanel();
		panelCC.setToolTipText("");
		panelCC.setBorder(new TitledBorder(null, "Cohen - Coon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCC.setBounds(12, 184, 965, 150);
		add(panelCC);
		
		JPanel panelL = new JPanel();
		panelL.setToolTipText("");
		panelL.setBorder(new TitledBorder(null, "Lopez", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelL.setBounds(12, 335, 965, 150);
		add(panelL);

	}
	public JPanel getMainPane() {
		return this;
	}
}
