package GUI;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelCompare extends JPanel {
	
	JPanel containerPanel;
	JPanel panelData;
	InformationExpert infoExpert;
	JCheckBox checkBoxCurvaReaccinZN;
	JCheckBox checkBoxCC;
	JCheckBox checkBoxLopez;
	JCheckBox checkBoxKS;

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public PanelCompare(final MainView ventana) {
		
		infoExpert = new InformationExpert();
		
		setLayout(null);
		
		JLabel lblComparacinDeMetodos = new JLabel("Comparación de metodos - Sistemas de lazo abierto");
		lblComparacinDeMetodos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblComparacinDeMetodos.setBounds(12, 0, 578, 34);
		add(lblComparacinDeMetodos);
		
		panelData = new JPanel();
		panelData.setToolTipText("");
		panelData.setBorder(new TitledBorder(null, "Datos de Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		btnGenerator.setBounds(12, 291, 175, 30);
		panelData.add(btnGenerator);
		
		containerPanel = new JPanel();
		
		containerPanel.setBorder(new TitledBorder(null, "Methods", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		containerPanel.setBounds(223, 33, 754, 451);
		add(containerPanel);
		
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
		
		JPanel panelZN,panelCC,panelL,panelKS;
		panelZN = null;
		panelCC = null;
		panelL = null;
		panelKS = null;
		
		int top = 20;
		int left = 5;
		int height = 150;
		int width = 730;
		
		containerPanel.removeAll();
		
		if(checkBoxCurvaReaccinZN.isSelected() ){
			panelZN = new JPanel();
			panelZN.setBorder(new TitledBorder(null, "Ziegler - Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelZN.setBounds(left, top, width, height);
			
			JTable table = infoExpert.getTableControllers("zn", 5, 2, 2);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,100);
			panelZN.add(panelTable);
			panelTable.setViewportView(table);
			
			panelZN.repaint();
			
			containerPanel.add(panelZN);
			top += height;
		}
		if(checkBoxCC.isSelected() ){
			panelCC = new JPanel();
			panelCC.setBorder(new TitledBorder(null, "Cohen - Coon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCC.setBounds(left, top, width, height);
			
			JTable table = infoExpert.getTableControllers("cc", 5, 2, 2);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,100);
			panelCC.add(panelTable);
			panelTable.setViewportView(table);
			
			panelCC.repaint();
			
			containerPanel.add(panelCC);
			top += height;
		}
		
		if(checkBoxLopez.isSelected() ){
			panelL = new JPanel();
			panelL.setBorder(new TitledBorder(null, "Lopez", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelL.setBounds(left, top, width, height);
			
			JTable tableL = infoExpert.getTableControllers("lopez", 5, 2, 2);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(tableL);
			panelTable.setBounds(10,20,400,100);
			//panelL.add(panelTableL, BorderLayout.CENTER);
			panelL.add(panelTable);
			panelTable.setViewportView(tableL);
			containerPanel.add(panelL);
			
			panelL.repaint();
			
			
			top += height;
		}
		
		if(checkBoxKS.isSelected() ){
			panelKS = new JPanel();
			panelKS.setToolTipText("");
			panelKS.setBorder(new TitledBorder(null, "Kaya - Sheib", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelKS.setBounds(left, top, width, height);
			
			JTable table = infoExpert.getTableControllers("cc", 5, 2, 2);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,100);
			panelKS.add(panelTable);
			panelTable.setViewportView(table);
			
			panelKS.repaint();
			
			containerPanel.add(panelKS);
			
			top += height;
		}
		
		
		containerPanel.repaint();
		
		
	}
}
