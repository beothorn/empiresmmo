package swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EmpiresCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
	
		setBorder(BorderFactory.createEmptyBorder());
		
		
		if (value.equals(".")){
			setBackground(Color.green);
		} else {
			setBackground(Color.white);
		}
		
		return this;
		
	}
	
}
