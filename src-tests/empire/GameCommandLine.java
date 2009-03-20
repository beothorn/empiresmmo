package empire;

import java.awt.Point;

import org.apache.commons.lang.UnhandledException;

import empire.buildings.Builder;


public class GameCommandLine {

	private final EmpireMap map;
	private final Builder builder;

	public GameCommandLine(final EmpireMap map, final Builder builder) {
		this.map = map;
		this.builder = builder;
	}

	public void command(final String command) {
		final String[] commandArgs = command.split("\\s+");
		
		final String commandName = commandArgs[0];
		if (commandName.equals("add")){
			
			if (commandArgs.length < 4)
				throw new IllegalStateException("Invalid number of args < 4");
			
			final String buildingName = commandArgs[1];
			final String x = commandArgs[2];
			final String y = commandArgs[3];
			
			try {
				map.addBuilding(builder.createBuilding(buildingName), new Point(Integer.valueOf(x), Integer.valueOf(y)));
			} catch (final NumberFormatException e) {
				throw new UnhandledException(e);
			} catch (final OccupiedPointException e) {
				System.out.println("The point " + x + ", " + y + " is occupied already");
			}
			return;
		}
		
		if (commandName.equals("print")){
			System.out.println(EmpireMapTextPrinter.printMapWithCoords(map));
			return;
		}
		
		throw new IllegalStateException("Command " + commandName + " is not valid.");
	}

}
