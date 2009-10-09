package empire.buildings;

import java.awt.Dimension;

public class Building {

	private final String buildingType;
	private final int sight;
	private final Dimension dimension;

	Building(final String buildingType, final Dimension dimension, final int sight) {
		this.buildingType = buildingType;
		this.dimension = dimension;
		this.sight = sight;
	}

	public int getSight() {
		return sight;
	}

	public double getWidth() {
		return dimension.getWidth();
	}

	public double getHeight() {
		return dimension.getHeight();
	}
	
	public Dimension getDimension(){
		return dimension;
	}

	public String getName() {
		return buildingType;
	}

	public Dimension getSightSize() {
		return new Dimension((int)(getSight()*2+getWidth()),(int)(getSight()*2+getHeight()));
	}
	
	@Override
	public String toString() {
		return "Building: "+buildingType;
	}
}
