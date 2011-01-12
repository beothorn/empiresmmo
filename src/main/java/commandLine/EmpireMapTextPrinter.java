package commandLine;

import java.awt.Dimension;
import java.awt.Point;

import commons.TextUtils;

import empire.EmpireMap;
import empire.buildings.Building;

public class EmpireMapTextPrinter {
	
	private static final String EMPTY_VISIBLE_MAP_POINT = ".";
	private static final String EMPTY_NOT_VISIBLE_MAP_POINT = "-";
	
	private final EmpireMap map;

	public EmpireMapTextPrinter(final EmpireMap map) {
		this.map = map;
	}

	
	
	public String print() {
		final StringBuilder mapPrint = new StringBuilder();
				
		boolean firstline = true;
		
		for(int y = 0; y < map.mapDimension().height; y++){
			
			if (!firstline){
				mapPrint.append("\n");
			}
			
			firstline = false;
			
			for(int x = 0; x < map.mapDimension().width; x++){
				final Point pointOnMap = new Point(x,y);
				pointOnMap.translate(map.getOriginOffset().width, map.getOriginOffset().height);
				mapPrint.append(getCharFor(pointOnMap));
			}
		}			
		
		return mapPrint.toString();
	}

	private String getCharFor(final Point pointOnMap) {
		
		final Building buildingInPointIfAnyOrNull = map.getBuildingInPointIfAnyOrNull(pointOnMap);
		if (buildingInPointIfAnyOrNull != null)
				return buildingInPointIfAnyOrNull.getName().substring(0,1).toUpperCase();
		
		if(map.isPointInSight(pointOnMap))
			return EMPTY_VISIBLE_MAP_POINT;
		
		return EMPTY_NOT_VISIBLE_MAP_POINT;
	}

	public static String printMapWithCoords(final EmpireMap map2) {
		return TextUtils.withCoordsStartingPoint(new EmpireMapTextPrinter(map2).print(), map2.getAbsoluteCenterPoint());
	}	
	
}
