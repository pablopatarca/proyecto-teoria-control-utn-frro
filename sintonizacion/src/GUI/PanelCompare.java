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

import javax.swing.JTextField;

import logicOpenLoop.CurveGenerator;
import logicOpenLoop.Grapher;


public class PanelCompare extends JPanel {
	
	JPanel containerPanel;
	JPanel panelData;
	InformationExpert infoExpert;
	JCheckBox checkBoxCurvaReaccinZN;
	JCheckBox checkBoxCC;
	JCheckBox checkBoxLopez;
	JCheckBox checkBoxKS;

	private static final long serialVersionUID = 1L;
	private JTextField inputA;
	private JTextField inputB;
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
		panelData.setBounds(12, 33, 199, 545);
		add(panelData);
		panelData.setLayout(null);
		
		inputA = new JTextField();
		inputA.setBounds(12, 50, 180, 25);
		panelData.add(inputA);
		inputA.setColumns(10);
		
		inputB = new JTextField();
		inputB.setBounds(12, 90, 180, 25);
		panelData.add(inputB);
		inputB.setColumns(10);
		
		JLabel lblKc = new JLabel("Ganancia");
		lblKc.setBounds(12, 30, 70, 20);
		panelData.add(lblKc);
		
		JLabel lblConstateTimpo = new JLabel("Constate Timpo");
		lblConstateTimpo.setBounds(12, 74, 179, 15);
		panelData.add(lblConstateTimpo);
		
		checkBoxCurvaReaccinZN = new JCheckBox("Curva Reacción Z-N");
		checkBoxCurvaReaccinZN.setBounds(8, 223, 161, 23);
		panelData.add(checkBoxCurvaReaccinZN);
		
		checkBoxCC = new JCheckBox("Cohen-Coon");
		checkBoxCC.setBounds(8, 251, 161, 23);
		panelData.add(checkBoxCC);
		
		checkBoxLopez = new JCheckBox("Lopez");
		checkBoxLopez.setBounds(8, 278, 161, 23);
		panelData.add(checkBoxLopez);
		
		checkBoxKS = new JCheckBox("Kaya-Sheib");
		checkBoxKS.setBounds(8, 305, 161, 23);
		panelData.add(checkBoxKS);
		
		JButton btnGenerator = new JButton("Generar");
		btnGenerator.setBounds(12, 377, 175, 30);
		panelData.add(btnGenerator);
		
		containerPanel = new JPanel();
		
		containerPanel.setBorder(new TitledBorder(null, "Methods", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		containerPanel.setBounds(223, 33, 754, 545);
		add(containerPanel);
		containerPanel.setLayout(null);
		
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
		
		int top = 20;
		int left = 5;
		int height = 120;
		int width = 730;
		
		containerPanel.removeAll();
		
		double Kp = Double.parseDouble(inputA.getText());
		double tau = Double.parseDouble(inputB.getText());
		
		//Obtain L and T values
		Grapher graph = new Grapher();
		graph.insertCurve(Kp, tau);
		CurveGenerator actualCurve = graph.getCurvaActual();
		double vL = actualCurve.getL();
		double vT = actualCurve.getT();
		
		if(checkBoxCurvaReaccinZN.isSelected() ){
			panelZN = new JPanel();
			panelZN.setBorder(new TitledBorder(null, "Ziegler - Nichols", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelZN.setBounds(left, top, width, height);
			
			JTable table = infoExpert.getTableControllers("zn", vL, vT, Kp, tau);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,81);
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
			
			JTable table = infoExpert.getTableControllers("cc", vL, vT, Kp, tau);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,81);
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
			
			JTable tableL = infoExpert.getTableControllers("lopez", vL, vT, Kp, tau);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(tableL);
			panelTable.setBounds(10,20,400,81);
			//panelL.add(panelTableL, BorderLayout.CENTER);
			panelL.add(panelTable);
			panelTable.setViewportView(tableL);
			containerPanel.add(panelL);
			
			panelL.repaint();
			
			
			top += height;
		}
		
		if(checkBoxKS.isSelected() ){
			panelKS = new JPanel();
			panelKS.setBorder(new TitledBorder(null, "Kaya - Sheib", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelKS.setBounds(left, top, width, height);
			
			JTable table = infoExpert.getTableControllers("ks", vL, vT, Kp, tau);
			
			JScrollPane panelTable = new JScrollPane();
			panelTable.add(table);
			panelTable.setBounds(10,20,400,81);
			panelKS.add(panelTable);
			panelTable.setViewportView(table);
			
			panelKS.repaint();
			
			containerPanel.add(panelKS);
			
			top += height;
		}
		
		
		containerPanel.repaint();
		
		
	}
}
