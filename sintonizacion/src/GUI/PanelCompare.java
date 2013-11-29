package GUI;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelCompare extends JPanel {
	
	JCheckBox checkBoxCurvaReaccinZN;
	JCheckBox checkBoxCC;
	JCheckBox checkBoxLopez;
	JCheckBox checkBoxKS;

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
		
		JPanel panelData = new JPanel();
		panelData.setToolTipText("");
		panelData.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Datos de Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelData.setBounds(12, 33, 199, 451);
		add(panelData);
		panelData.setLayout(null);
		
		checkBoxCurvaReaccinZN = new JCheckBox("Curva Reacción Z-N");
		checkBoxCurvaReaccinZN.setBounds(8, 137, 161, 23);
		panelData.add(checkBoxCurvaReaccinZN);
		
		checkBoxCC = new JCheckBox("Cohen-Coon");
		checkBoxCC.setBounds(8, 165, 161, 23);
		panelData.add(checkBoxCC);
		
		checkBoxLopez = new JCheckBox("Lopez");
		checkBoxLopez.setBounds(8, 192, 161, 23);
		panelData.add(checkBoxLopez);
		
		checkBoxKS = new JCheckBox("Kaya-Sheib");
		checkBoxKS.setBounds(8, 219, 161, 23);
		panelData.add(checkBoxKS);
		
		JButton btnGenerator = new JButton("Generar");
		btnGenerator.setBounds(12, 291, 175, 25);
		panelData.add(btnGenerator);
		
		btnGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateComp();
			}
		});

	}
	public JPanel getMainPanel() {
		return this;
	}
	
	public void generateComp(){
		
		
		if(checkBoxCurvaReaccinZN.isSelected() ){
			JPanel panelZN = new JPanel();
			panelZN.setBorder(new TitledBorder(null, "Ziegler - Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelZN.setToolTipText("");
			panelZN.setBounds(223, 33, 752, 150);
			this.add(panelZN);
		}
		if(checkBoxCC.isSelected() ){
			JPanel panelCC = new JPanel();
			panelCC.setToolTipText("");
			panelCC.setBorder(new TitledBorder(null, "Cohen - Coon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCC.setBounds(223, 183, 752, 150);
			this.add(panelCC);
		}
		
		if(checkBoxLopez.isSelected() ){
			JPanel panelL = new JPanel();
			panelL.setToolTipText("");
			panelL.setBorder(new TitledBorder(null, "Lopez", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelL.setBounds(223, 334, 752, 150);
			this.add(panelL);
		}
		
		this.repaint();
		
	}
}
