package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JPanel grafico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
		this.setResizable(false);
		
		setTitle("Sintonizaci\u00F3n de controladores - M\u00E9todos de Ziegler y Nichols");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/iconos/imagen de respuessta transitoria.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 637);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenu mnSalir = new JMenu("Salir");
		mnSalir.setMargin(new Insets(0, 10, 0, 10));
		mnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int decision = JOptionPane.showConfirmDialog(frame, "¿Seguro que desea salir?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
				if(decision == 0)
					dispose();
			}
		});
		
		JMenu mnMtodoDeLa = new JMenu("M\u00E9todo de la curva de reacci\u00F3n");
		mnMtodoDeLa.setMargin(new Insets(0, 10, 0, 10));
		mnMtodoDeLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelCurvaReaccion panel = new PanelCurvaReaccion(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setBounds(panel.getBounds());
				setLocationRelativeTo(null);
				//pack();
			}
		});
		menuBar.add(mnMtodoDeLa);
		
		JMenu mnMtodoDeLas = new JMenu("M\u00E9todo de las oscilaciones sostenidas");
		mnMtodoDeLas.setMargin(new Insets(0, 10, 0, 10));
		mnMtodoDeLas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				PanelOscilacionesSostenidas panel = new PanelOscilacionesSostenidas(frame);
				contentPane.add(panel.getContentPane(), BorderLayout.CENTER);
				contentPane.updateUI();
				setBounds(panel.getBounds());
				setLocationRelativeTo(null);
				//pack();
			}
		});
		menuBar.add(mnMtodoDeLas);
		
		JMenu mnVolverAlMen = new JMenu("Volver al men\u00FA");
		mnVolverAlMen.setMargin(new Insets(0, 10, 0, 10));
		mnVolverAlMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.updateUI();
				setBounds(100, 100, 1010, 637);
				setLocationRelativeTo(null);
			}
		});
		menuBar.add(mnVolverAlMen);
		
		JMenu mnAcercaDe = new JMenu("Acerca de...");
		mnAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AcercaDe dialog = new AcercaDe();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(frame);
			}
		});
		mnAcercaDe.setMargin(new Insets(0, 10, 0, 10));
		menuBar.add(mnAcercaDe);
		menuBar.add(mnSalir);
	}
}
