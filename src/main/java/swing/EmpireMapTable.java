package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import commandLine.GameCommandLine;

import empire.EmpireMap;
import empire.OccupiedPointException;
import empire.Output;
import empire.PointNotInSightException;
import empire.buildings.Builder;

public class EmpireMapTable {

	private final JTable jTable;
	private static EmpiresTableModel model;
	private DefaultTableColumnModel columnModel;

	public EmpireMapTable(EmpireMap map) {
		model = new EmpiresTableModel(map);
		jTable = new JTable(model);
		
		EmpiresCellRenderer empiresCellRenderer = new EmpiresCellRenderer();
		jTable.setDefaultRenderer(Object.class, empiresCellRenderer);
	}


	public JTable getJTable() {
		return jTable;
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException, OccupiedPointException, PointNotInSightException, IOException {
		
		EmpireMap map = new EmpireMap();
		
		map.addBuilding(new Builder().createMine(), new Point(-10, -10));
		
		JFrame frame = new JFrame();
		EmpireMapTable empireMapTable = new EmpireMapTable(map);
		Component empireMapJTable = empireMapTable.getJTable();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(empireMapJTable, BorderLayout.CENTER);
		JScrollPane empiresMapJScrollPane = new JScrollPane(panel);
		empiresMapJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		frame.setLayout(new BorderLayout());
		frame.add(empiresMapJScrollPane, BorderLayout.CENTER);
		
		
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
		GameCommandLine gcl = new GameCommandLine(map, new Builder(), new Output() {
			
			@Override
			public void writeLine(String message) {
				System.out.println(message);
				
			}
		});
		
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while (!((line = "" + reader.readLine())).equals("end")){
			gcl.command(line);
			empireMapTable.refresh();
		}
		
		
	}


	private void refresh() {
		jTable.setColumnModel(new DefaultTableColumnModel());
		model.fireTableStructureChanged();
		
	}
	
}
