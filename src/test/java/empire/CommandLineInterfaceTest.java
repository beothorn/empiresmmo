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
	
	@Test
	public void testTryToAddCastleIntersecting(){
		final GameCommandLine commandLine = new GameCommandLine(map, builder, new Output() {
			@Override
			public void writeLine(final String message) {
				//ignore it
			}
		});
		
		final Point firstCastlePoint = new Point(5, 3);
		if (!addCastleAnReturnIfAdded(commandLine, firstCastlePoint))
			throw new IllegalStateException("Building at point must not be null");
		Assert.assertEquals(nameOfBuildingAt(firstCastlePoint),"castle");
		
		final Point secondCastlePoint = new Point(3, 5);
		if (addCastleAnReturnIfAdded(commandLine, secondCastlePoint))
			throw new IllegalStateException("Castle intersecting another should not be allowed.");
	}

	private Object nameOfBuildingAt(Point point) {
		final Building building = map.getBuildingInPointIfAnyOrNull(point);
		if(building == null)
			return "";
		return building.getName();
	}

	private boolean addCastleAnReturnIfAdded(final GameCommandLine commandLine, final Point castlePoint) {
		addCastle(commandLine, castlePoint);
		final Building buildingInPoint = map.getBuildingInPointIfAnyOrNull(castlePoint);
		return buildingInPoint != null;
	}

	private void addCastle(final GameCommandLine commandLine,
			final Point castlePoint) {
		commandLine.command("add castle "+castlePoint.x+" "+castlePoint.y);
	}
}
