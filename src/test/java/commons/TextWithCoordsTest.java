package commons;

import java.awt.Point;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import empire.BuildingsManagementTest;

public class TextWithCoordsTest {

	@Test
	public void testTextWithCoords() throws IOException{		
		final String text = 
			"foo\n" +
			"ba";
		
		final String textWithCoords = 
			" 012\n" +
			"0foo\n" +
			"1ba";
		
		Assert.assertEquals(textWithCoords, TextUtils.withCoords(text));
	}
	
	@Test
	public void testTextWithCoordsCenterPoint() throws IOException{		
		final String mapWithACastleAndMine = BuildingsManagementTest.resourceAsString(BuildingsManagementTest.class, "mapWithACastleAndMine.txt");
		final String mapWithACastleAndMineCoords = BuildingsManagementTest.resourceAsString(BuildingsManagementTest.class, "mapWithACastleAndMineAndCoords.txt");
		Assert.assertEquals(mapWithACastleAndMineCoords, TextUtils.withCoordsStartingPoint(mapWithACastleAndMine, new Point(10,10)));
	}
}
