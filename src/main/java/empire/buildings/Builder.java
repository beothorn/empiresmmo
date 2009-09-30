package empire.buildings;

import java.awt.Dimension;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Builder {
	
	private final Set<Building> buildings = new LinkedHashSet<Building>();

	public Builder(){
		buildings.add(new Castle());
		buildings.add(new Building("mine", new Dimension(1,1), 1));
	}

	public Building createCastle(){
		return buildingByName("castle");
	}


	public Building createMine() {
		return buildingByName("mine");
	}

	private Building buildingByName(final String name) {
		for (final Building building : buildings){
			if (building.getName().equals(name))
				return building;
		}
		
		throw new IllegalArgumentException("Building " + name + " is not on the list.");
	}

	public Building createBuilding(final String buildingName) {
		return buildingByName(buildingName);
	}

	public boolean isBuildingType(final String buildingType) {
		for (final Building b : buildings)
			if (b.getName().equals(buildingType))
				return true;
		
		return false;
		
	}

	public Set<Building> getBuildings() {
		return Collections.unmodifiableSet(buildings);
	}
	
}
