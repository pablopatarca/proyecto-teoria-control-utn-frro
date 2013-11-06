package GUI;

import java.awt.BorderLayout;
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

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		
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

		final Ventana frame = this;
		//this.setResizable(false);
		
		setTitle("Sintonizaci\u00F3n de controladores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
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
		
		JMenu menuPrincipal = new JMenu("Men\u00FA");
		menuPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		/* Cohen y Coon - Lazo abierto */
		
		JMenu cohenCoon = new JMenu("Cohen y Coon");
		cohenCoon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelLACC panel = new PanelLACC(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		
		/* Salir */
		
		JMenu mnSalir = new JMenu("Salir");
		mnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int decision = JOptionPane.showConfirmDialog(frame, "�Seguro que desea salir?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
				if(decision == 0)
					dispose();
			}
		});
		
		
		/* Curva de Reacción */
		
		JMenu curvaR = new JMenu("Curva de reacci\u00F3n");
		curvaR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelCurvaReaccion panel = new PanelCurvaReaccion(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		
		/* Oscilaciones Sostenidas */
		
		JMenu oscilaciones = new JMenu("Oscilaciones sostenidas");
		oscilaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelOscilacionesSostenidas panel = new PanelOscilacionesSostenidas(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setLocationRelativeTo(null);
			}
		});
		
		
		/* Acerca de... */
		
		JMenu mnAcercaDe = new JMenu("Acerca de...");
		mnAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AcercaDe dialog = new AcercaDe();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(frame);
			}
		});
		
		
		
		
		/* Agrego opciones del según orden */
		
		menuBar.add(menuPrincipal);
		
		//Lazo abierto
		menuBar.add(curvaR);		//Ziegler y Nichols
		
		menuBar.add(cohenCoon);		//Cohen y Coon
		
		
		//Lazo cerrado
		menuBar.add(oscilaciones);	//Ziegler y Nichols
		
		
		
		menuBar.add(mnAcercaDe);
		menuBar.add(mnSalir);
	}
}
