package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import empire.EmpireMap;
import empire.GameCommandLine;
import empire.buildings.Builder;

public class Main {

	
	public static void main(final String[] args) throws IOException {
		final Builder builder = new Builder();
		final EmpireMap map = new EmpireMap();
		
		final GameCommandLine gcl = new GameCommandLine(map, builder);
		
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while (!(line = reader.readLine()).equals("end")){
			gcl.command(line);
		}
				
		
	}

}
