package empire.buildings;

import java.util.LinkedHashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

public class BuildingsLoader {

	private final Set<Building> buildings;
	
	@SuppressWarnings("unchecked")
	public BuildingsLoader(final String buildingsXML) {
		final XStream xstream = new XStream();
		xstream.alias("building", Building.class);
		xstream.alias("buildings", LinkedHashSet.class);
		buildings = (Set<Building>)xstream.fromXML(buildingsXML);
	}

	public Set<Building> getBuildings() {
		return buildings;
	}

}
