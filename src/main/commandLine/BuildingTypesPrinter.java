package commandLine;

import java.util.Set;

import empire.buildings.Builder;
import empire.buildings.Building;

public class BuildingTypesPrinter {

	public static String printBuildingsList(final Builder builder) {
		final Set<Building> buildings = builder.getBuildings();
		String buildingsList = "";
		boolean first = true;
		for (final Building building : buildings) {
			if(first){
				buildingsList += building.getName();
				first = false;
			}else{
				buildingsList += ", " + building.getName();
			}
		}
		return buildingsList;
	}

}
