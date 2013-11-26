package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {
	
	private double minW = 1000;
	private double minH = 630;


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
					//frame.setLocationRelativeTo(null);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		
		Dimension d = new Dimension();
		d.setSize(minW, minH);
		this.setMinimumSize(d);
		
		
		try
		{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}

		final MainView frame = this;
		//this.setResizable(false);
		
		setTitle("Sintonizaci\u00F3n de controladores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1010, 637);//M\u00E9todos de Ziegler y Nichols
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		/* Menú Principal */
		
		JMenu menuPrincipal = new JMenu("Inicio");
		menuPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		/*  Ziegler and Nichols Method */
		
		JMenu curvaR = new JMenu("Ziengler-Nichols");
		curvaR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				MethodPanelZieglerNichols panel = new MethodPanelZieglerNichols(frame);
				contentPane.add(panel.getMainPanel(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		/* Cohen y Coon - Lazo abierto */
		
		JMenu cohenCoon = new JMenu("Cohen - Coon");
		cohenCoon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				MethodPanelCohenCoon panel = new MethodPanelCohenCoon(frame);
				contentPane.add(panel.getMainPanel(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		/* Lopez - Lazo abierto */
		
		JMenu lopez = new JMenu("Lopez");
		lopez.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				MethodPanelLopez panel = new MethodPanelLopez(frame);
				contentPane.add(panel.getMainPanel(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		/* Kaya and Sheib Method */
		
		JMenu kayaSheib = new JMenu("Kasha-Sheib");
		kayaSheib.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				contentPane.removeAll();
				MethodPanelKayaSheib panel = new MethodPanelKayaSheib(frame);
				contentPane.add(panel.getMainPanel(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		}
				);
		
		
		/* Oscilaciones Sostenidas */
		
		JMenu oscilaciones = new JMenu("Ziegler-Nichols");
		oscilaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelClosedZieglerNichols panel = new PanelClosedZieglerNichols(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		JMenu compare = new JMenu("Comparación");
		compare.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				contentPane.removeAll();
				PanelCompare panel = new PanelCompare(frame);
				contentPane.add(panel.getMainPane(), BorderLayout.CENTER);
				contentPane.updateUI();
			}
		});
		
		
		/* Salir */
		
		JMenu mnSalir = new JMenu("Salir");
		mnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int decision = JOptionPane.showConfirmDialog(frame, "¿Seguro que desea salir?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
				if(decision == 0)
					System.exit(0);
			}
		});
		
		
		
		
		/* Agrego opciones del según orden */
		
		menuBar.add(menuPrincipal);
		//Lazo abierto
		menuBar.add(curvaR);		//Ziegler-Nichols
		menuBar.add(cohenCoon);		//Cohen-Coon
		menuBar.add(lopez);			//Lopez
		menuBar.add(kayaSheib);		//Kaya-Sheib
		//Lazo cerrado
		menuBar.add(oscilaciones);	//Ziegler-Nichols
		menuBar.add(compare);

		menuBar.add(mnSalir);
	}
}
