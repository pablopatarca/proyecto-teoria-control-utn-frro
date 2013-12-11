package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import logicOpenLoop.CurveGenerator;
import logicOpenLoop.Grapher;


public class PanelCompare extends JPanel {
	
	JPanel containerPanel;
	JPanel panelData;
	JTable tableTL;
	InformationExpert infoExpert;
	JCheckBox checkBoxCurvaReaccinZN;
	JCheckBox checkBoxCC;
	JCheckBox checkBoxLopez;
	JCheckBox checkBoxKS;
	JFrame mainView1;

	private static final long serialVersionUID = 1L;
	private JTextField inputA;
	private JTextField inputB;
	/**
	 * Create the panel.
	 */
	public PanelCompare(final MainView mainView) {
		
		mainView1 = mainView;
		
		infoExpert = new InformationExpert();
		
		setLayout(null);
		
		JLabel lblComparacinDeMetodos = new JLabel("Comparación de metodos - Sistemas de lazo abierto");
		lblComparacinDeMetodos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblComparacinDeMetodos.setBounds(12, 0, 578, 34);
		add(lblComparacinDeMetodos);
		
		panelData = new JPanel();
		panelData.setToolTipText("");
		panelData.setBorder(new TitledBorder(null, "Datos de Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelData.setBounds(12, 33, 199, 545);
		add(panelData);
		panelData.setLayout(null);
		
		inputA = new JTextField();
		inputA.setBounds(12, 50, 175, 25);
		panelData.add(inputA);
		inputA.setColumns(10);
		
		inputB = new JTextField();
		inputB.setBounds(12, 90, 175, 25);
		panelData.add(inputB);
		inputB.setColumns(10);
		
		JLabel lblKc = new JLabel("Ganancia");
		lblKc.setBounds(12, 30, 70, 20);
		panelData.add(lblKc);
		
		JLabel lblConstateTimpo = new JLabel("Constate de Timpo");
		lblConstateTimpo.setBounds(12, 74, 179, 15);
		panelData.add(lblConstateTimpo);
		
		checkBoxCurvaReaccinZN = new JCheckBox("Curva Reacción Z-N");
		checkBoxCurvaReaccinZN.setBounds(8, 264, 161, 23);
		panelData.add(checkBoxCurvaReaccinZN);
		
		checkBoxCC = new JCheckBox("Cohen-Coon");
		checkBoxCC.setBounds(8, 292, 161, 23);
		panelData.add(checkBoxCC);
		
		checkBoxLopez = new JCheckBox("Lopez");
		checkBoxLopez.setBounds(8, 319, 161, 23);
		panelData.add(checkBoxLopez);
		
		checkBoxKS = new JCheckBox("Kaya-Sheib");
		checkBoxKS.setBounds(8, 346, 161, 23);
		panelData.add(checkBoxKS);
		
		JButton btnGenerator = new JButton("   Generar", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_aceptar.png")));
		btnGenerator.setBounds(12, 377, 175, 40);
		btnGenerator.setHorizontalAlignment(SwingConstants.LEFT);
		panelData.add(btnGenerator);
		
		
		//Define scrollpane
		JScrollPane paneltableTL = new JScrollPane();
		paneltableTL.setRequestFocusEnabled(false);
		paneltableTL.setBounds(12, 142, 175, 68);
		paneltableTL.setBorder(new TitledBorder(null, "Valores de L y T", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelData.add(paneltableTL, BorderLayout.CENTER);
		// L y T table values
		tableTL = new JTable();
		tableTL.setFocusTraversalKeysEnabled(false);
		tableTL.setFocusable(false);
		tableTL.setRequestFocusEnabled(false);
		tableTL.setModel( infoExpert.getModelLT() );
		tableTL.setRowSelectionAllowed(false);
		tableTL.setCellSelectionEnabled(true);
		paneltableTL.setViewportView(tableTL);
		
		//Create comparer container Panel
		containerPanel = new JPanel();
		containerPanel.setBorder(new TitledBorder(null, "Methods", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		containerPanel.setBounds(223, 33, 800, 545);
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
	
	private void generateComp(){
		
		if(inputA.getText() != "" && inputB.getText() != ""){
			
			Pattern pat = Pattern.compile("^\\d+|^\\d+\\.?\\d+");
			Matcher mat1 = pat.matcher(inputA.getText());
		    Matcher mat2 = pat.matcher(inputB.getText());
		     
		     //Number Validate
		     if (mat1.matches() && mat2.matches()) {
		         System.out.println("Are Numbers");
		         
  /**********                                   *****************/
		
				JPanel panelZN,panelCC,panelL,panelKS;
				
				int top = 20;
				int left = 5;
				int height = 120;
				int width = 770;
				
				containerPanel.removeAll();
				
				double Kp = Double.parseDouble(inputA.getText());
				double tau = Double.parseDouble(inputB.getText());
				
				if(Kp > 0 && tau > 0){
				
				//Obtain L and T values
				Grapher graph = new Grapher();
				graph.insertCurve(Kp, tau);
				CurveGenerator actualCurve = graph.getCurvaActual();
				double vL = actualCurve.getL();
				double vT = actualCurve.getT();
				
				tableTL.getModel().setValueAt(infoExpert.round(vL), 0, 0);
				tableTL.getModel().setValueAt(infoExpert.round(vT), 0, 1);
			
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
					
					//Set Buttons
					generateButtons("zn", panelZN);
					
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
					
					//Set Buttons
					generateButtons("cc", panelCC);
					
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
					
					//Set Buttons
					generateButtons("lopez", panelL);
					
					
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
					
					//Set Buttons
					generateButtons("ks", panelKS);
					
					panelKS.repaint();
					containerPanel.add(panelKS);
					top += height;
					
				}
				
				containerPanel.repaint();
			
				}else{
					JOptionPane.showMessageDialog(null, "Las constantes deben ser valores mayores que cero", 
							"Error", JOptionPane.ERROR_MESSAGE, null);
				}
		     }else{
		    	 JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos", 
							"Error", JOptionPane.ERROR_MESSAGE, null);
		     }
		}else{
			JOptionPane.showMessageDialog(null, "Debe ingresar constantes de tiempo y ganancia", 
					"Error", JOptionPane.ERROR_MESSAGE, null);
		}
		
	}
	
	private void generateButtons(final String method, JPanel panel){
		
		JButton btnEquations = new JButton("Ecuaciones", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnEquations.setHorizontalAlignment(SwingConstants.LEFT);
		btnEquations.setBounds(420, 15, 150, 40);
		
		JButton btnDescription = new JButton("Descripci\u00F3n m\u00E9todo", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
		btnDescription.setHorizontalAlignment(SwingConstants.LEFT);
		btnDescription.setBounds(580, 15, 150, 40);
		
		if(method=="lopez" || method=="ks"){
			
			JButton btnConstants = new JButton("Constantes", new ImageIcon(MethodPanelView.class.getResource("/icons/icon_formula.png")));
			btnConstants.setHorizontalAlignment(SwingConstants.LEFT);
			btnConstants.setBounds(420, 65, 150, 40);
			
			//Constantes para ecuaciónes del metodo de Lopez o Kaya-sheib  **********
			//Show constants method
			btnConstants.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JDialog dialog =  new JDialog(mainView1);
					dialog.setTitle("Constantes Metodo de Lopez");
				    dialog.getContentPane().add(new JScrollPane( infoExpert.getConstantTable(method) ));
				    dialog.setVisible(true);
					dialog.setLocationRelativeTo(mainView1);
					dialog.setBounds(100, 100, 500, 200);
				}
			});
			
			panel.add(btnConstants);
		}
		
		btnEquations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String title = "Ecuaciones del método";
				
				ModalEquationView dialog = new ModalEquationView(mainView1, title, infoExpert.getURLEquationImage(method));
				dialog.setVisible(true);
				dialog.setBounds(100, 100, 500, 200);
				dialog.setLocationRelativeTo(mainView1);
			}
		});
		
		//Show Description
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalMethodDescription dialog = new ModalMethodDescription(mainView1, infoExpert.getDescription(method));
				dialog.setVisible(true);
				dialog.setBounds(100, 100, 500, 200);
				dialog.setLocationRelativeTo(mainView1);
			}
		});
		
		
		panel.add(btnEquations);
		panel.add(btnDescription);
		
	}
	
	
	public JTextField getInputField(){
		return inputA;
	}
	
}
