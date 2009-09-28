package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import commandLine.GameCommandLine;

import empire.EmpireMap;
import empire.Output;
import empire.buildings.Builder;

public class Main {

	public static void main(final String[] args) throws IOException {
		final Builder builder = new Builder();
		final EmpireMap map = new EmpireMap();
		
		final GameCommandLine gcl = new GameCommandLine(map, builder, new Output() {
			@Override
			public void writeLine(String message) {
				System.out.println(message);
			}
		});
		
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while (!((line = "" + reader.readLine())).equals("end")){
			gcl.command(line);
		}
				
		
	}

}
