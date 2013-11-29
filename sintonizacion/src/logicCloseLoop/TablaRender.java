package logicCloseLoop;


import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.TableCellRenderer;
import GUI.MainView;

public class TablaRender implements TableCellRenderer {
	
	private NumberFormat formato = new DecimalFormat("0.00");

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
	        boolean hasFocus, int row, int column) {

		// Si el objeto que nos pasan es un String, lo ponemos en el JLabel.
		if(value instanceof Double) {
			if((double)value != 0.0) {
				JTextField campo = new JTextField();
				campo.setText(formato.format((double)value));
				campo.setHorizontalAlignment(JTextField.RIGHT);
				campo.setBorder(BorderFactory.createEmptyBorder());
				return (Component)campo;
			}
			else {
				JLabel etiqueta = new JLabel();
				etiqueta.setIcon(new ImageIcon(MainView.class.getResource("/iconos/rojo oscuro.png")));
				return (Component)etiqueta;
			}
		}
		else if(value instanceof String) {
			if(value.equals("0")) {
				JLabel etiqueta = new JLabel();
				etiqueta.setIcon(new ImageIcon(MainView.class.getResource("/iconos/rojo oscuro.png")));
				return (Component)etiqueta;
			}
			else {
				JTextField campo = new JTextField();
				campo.setText((String)value);
				campo.setHorizontalAlignment(JTextField.CENTER);
				campo.setBorder(BorderFactory.createEmptyBorder());
				return (Component)campo;
			}
		}
		return null;
	}
}
