package empire.buildings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class LoadBuildingsFromXmlTest {

	@Test
	public void loadCastle() throws IOException{
		final InputStream resourceAsStream = LoadBuildingsFromXmlTest.class.getResourceAsStream("buildings.xml");
		final String buildingsXML = IOUtils.toString(resourceAsStream, "UTF-8");
		final Set<Building> buildings = new BuildingsLoader(buildingsXML).getBuildings();
		for (final Building building : buildings) {
			if(building.getName().equals("castle"))
				continue;
			if(building.getName().equals("mine"))
				continue;
			throw new RuntimeException("Odd building name on xml?");
		}
	}
}
