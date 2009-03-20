package empire;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import empire.buildings.Builder;


public class BuildingsManagementTest {

	private Builder builder;
	private EmpireMap map;

	@Before
	public void setup(){
		builder = new Builder();
		map = new EmpireMap();
	}
	
	@Test
	public void testMapStartsWithACastle() throws IOException{
		
		final String mapWithACastle = resourceAsString(BuildingsManagementTest.class, "mapWithACastle.txt");
		Assert.assertEquals(mapWithACastle, printMap(map));
	}

	@Test
	public void testMapStartsWithACastleAndMine() throws IOException, OccupiedPointException{
		map.addBuilding(builder.createMine(), new Point(14,14));
		final String mapWithACastleAndMine = resourceAsString(BuildingsManagementTest.class, "mapWithACastleAndMine.txt");
		Assert.assertEquals(mapWithACastleAndMine, printMap(map));
	}
	
	@Test
	public void testAddBuildingInOccupiedPoint() throws IOException{

		try{
			map.addBuilding(builder.createMine(), new Point(0,0));
			throw new IllegalStateException("Must throw OccupiedPointException");
		} catch (final OccupiedPointException ope){
			//success
		}
	}
	
	@Test
	public void testReturnAbsoluteCenterPoint(){
		final Point centerPoint = map.getAbsoluteCenterPoint();
		Assert.assertEquals(new Point(10,10), centerPoint);
	}

	private String printMap(final EmpireMap map) {
		return new EmpireMapTextPrinter(map).print();
	}
	
	public static String resourceAsString(final Class<?> clazz, final String resourceName) throws IOException {
		final InputStream resourceStream = clazz.getResourceAsStream(resourceName);
		
		if (resourceStream == null)
			throw new IllegalArgumentException("Resource " + resourceName + " for class " + clazz + " was not found" );
		
		try {
			return IOUtils.toString(resourceStream, "UTF-8");
		} finally {
			resourceStream.close();
		}
	} 

	//command line
	
}
