package empire;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import commandLine.GameCommandLine;

import empire.buildings.Builder;
import empire.buildings.Building;

public class CommandLineInterfaceTest {
	
	private EmpireMap map;
	private Builder builder;

	@Before
	public void setup(){
		
		builder = new Builder();
		map = new EmpireMap();
	}
	
	@Test
	public void testAddBuilding(){
		final GameCommandLine commandLine = new GameCommandLine(map, builder, new Output() {
			
			@Override
			public void writeLine(final String message) {
				//ignore it
			}
		});
		final Point minePoint = new Point(-8,-8);
		commandLine.command("add mine "+minePoint.x+" "+minePoint.y);
		final Building buildingInPointIfAnyOrNull = map.getBuildingInPointIfAnyOrNull(minePoint);
		
		if (buildingInPointIfAnyOrNull == null)
			throw new IllegalStateException("Building at point must not be null");
		
		Assert.assertTrue(buildingInPointIfAnyOrNull.getName().equals("mine"));
	}
}
