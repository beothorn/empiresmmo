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
		System.out.println(processCommand(command));
	}

	private String processCommand(final String command) {
		final String[] commandArgs = command.split("\\s+");
		
		final String commandName = commandArgs[0];
		if (commandName.equals("add")){
			
			if (commandArgs.length < 4)
				return ("Invalid number of args < 4");
			
			final String buildingType = commandArgs[1];
			final String x = commandArgs[2];
			final String y = commandArgs[3];
			
			if (!builder.isBuildingType(buildingType)){
				return "Unknown building type " + buildingType;
			}
			
			try {
				map.addBuilding(builder.createBuilding(buildingType), new Point(Integer.valueOf(x), Integer.valueOf(y)));
			} catch (final NumberFormatException e) {
				throw new UnhandledException(e);
			} catch (final OccupiedPointException e) {
				return "The point " + x + ", " + y + " is occupied already";
			}
			
			return "Building " + buildingType + " added in " + x + " " + y;
			
		}else if (commandName.equals("print")){
			return  EmpireMapTextPrinter.printMapWithCoords(map);
		}
		
		return "Command " + commandName + " is not valid.";
	}

}
