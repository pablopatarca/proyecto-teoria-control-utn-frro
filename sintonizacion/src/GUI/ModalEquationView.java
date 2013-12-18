package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ModalEquationView extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JLabel source = null;
	private String sourceText = "";
	double minW;
	double minH;

	/**
	 * Create the dialog.
	 */
	public ModalEquationView(final JFrame frame, final String title, final String url) {
		
		super(frame,true);
		
		ImageIcon img = new ImageIcon(ModalEquationView.class.getResource(url));
		minW = img.getIconWidth();
		minH =img.getIconHeight();
		
		setTitle(title);
		
		setBounds(100, 100, (int)minW+30, (int)minH+50);

		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel label = new JLabel();
		label.setIcon(img);
		label.setBounds(10, 10, (int) minW, (int) minH);
		contentPanel.add(label);
		
	}
	
	public void setSource(String sourceText) {
		
		this.sourceText = sourceText;
		
		this.source = new JLabel("Fuente: "+ this.sourceText);
		source.setBounds(10,(int) minH+10, (int) minW, 15);
		
		source.setForeground(Color.BLUE);
        source.setCursor(new Cursor(Cursor.HAND_CURSOR));
        source.addMouseListener(new URLOpenAdapter());
		
		
		
		contentPanel.add(source);
		this.setBounds(100, 100, (int)minW+30, (int)minH+60);
		
	}
	
	private class URLOpenAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(sourceText));
                } catch (Throwable t) {
                    //
                }
            }
        }
    }
	
}
