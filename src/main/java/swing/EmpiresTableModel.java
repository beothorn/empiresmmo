package swing;

import java.awt.Point;

import javax.swing.table.AbstractTableModel;

import empire.EmpireMap;
import empire.buildings.Building;

public class EmpiresTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private final EmpireMap map;

	public EmpiresTableModel(EmpireMap map) {
		this.map = map;
	}
	
	@Override
	public int getColumnCount() {
		return (int) map.mapDimension().getWidth();
	}
	
	@Override
	public String getColumnName(int column) {
		return "" + column;
	}
	
	@Override
	public int getRowCount() {		
		return (int) map.mapDimension().getHeight();
	}

	@Override
	public Object getValueAt(int x, int y) {
		
		
		Point pointOnMap = new Point(x, y);
		pointOnMap.translate(map.getOriginOffset().width, map.getOriginOffset().height);
		
		Building buildingInPointIfAnyOrNull = map.getBuildingInPointIfAnyOrNull(pointOnMap);
		
		if (buildingInPointIfAnyOrNull != null){
			return buildingInPointIfAnyOrNull.getName().charAt(0);			
		}
		
		if (map.isPointInSight(pointOnMap)){
			return ".";
		}
		
		return "";
		
	}
	
	
}
