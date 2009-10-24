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
	public void testAddTwoCastles(){
		final GameCommandLine commandLine = new GameCommandLine(map, builder, new Output() {
			@Override
			public void writeLine(final String message) {
				//ignore it
			}
		});
		
		final Point firstCastlePoint = new Point(5, 3);
		final Building buildingAtFirstCastlePoint = addCastle(commandLine, firstCastlePoint);
		if (buildingAtFirstCastlePoint == null)
			throw new IllegalStateException("Building at point must not be null");
		Assert.assertTrue(buildingAtFirstCastlePoint.getName().equals("castle"));
		
		final Point secondCastlePoint = new Point(3, 5);
		final Building buildingAtSecondCastlePoint = addCastle(commandLine, secondCastlePoint);
		if (buildingAtSecondCastlePoint != null)
			throw new IllegalStateException("Castle should not be added here.");
		
		
		
	}

	private Building addCastle(final GameCommandLine commandLine, final Point castlePoint) {
		commandLine.command("add castle "+castlePoint.x+" "+castlePoint.y);
		final Building buildingInPointIfAnyOrNull = map.getBuildingInPointIfAnyOrNull(castlePoint);
		return buildingInPointIfAnyOrNull;
	}
}
