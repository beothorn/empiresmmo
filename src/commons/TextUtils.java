package commons;

import java.awt.Point;
import java.awt.Rectangle;

import org.apache.commons.lang.StringUtils;


public class TextUtils {

	public static String withCoords(final String string) {
		return withCoordsStartingPoint(string, new Point(0,0));
	}
	
	public static String withCoordsStartingPoint(final String string, final Point startingPoint){
		final Rectangle textSize = getTextSize(string);

		final int linesAlgarismsCount = String.valueOf(Math.abs(textSize.height - startingPoint.y)).length();
		

		final String header = getColumnCoordinates(textSize.width,linesAlgarismsCount,startingPoint.x);
		final String body = getLinesWithCoordinates(string, textSize.height, linesAlgarismsCount,startingPoint.y);
		
		
		return header+body;
	}
	
	private static String getLinesWithCoordinates(final String string,
			final int textHeight, final int linesAlgarismsCount, final int originLine) {
		final String[] split = string.split("\n");
		
		final StringBuilder lines = new StringBuilder();
		
		for(int i=0; i< textHeight;i++){
			if(i>0){
				lines.append("\n");
			}
			lines.append(StringUtils.leftPad(String.valueOf(Math.abs(i - originLine)), linesAlgarismsCount) );
			lines.append(split[i]);			
		}
		final String result = lines.toString();
		return result;
	}

	private static Rectangle getTextSize(final String string) {
		int biggestLineLength = 0;
		int lineCount = 1;
		
		int lineLenght = 0;
		for(final char c: string.toCharArray()){
			if(c  == '\n'){
				lineCount++;
				lineLenght = 0;		
			} else {
				lineLenght ++;
				biggestLineLength = Math.max(biggestLineLength, lineLenght);
			}
		}
		
		final Rectangle textSize = new Rectangle(biggestLineLength, lineCount);
		return textSize;
	}

	private static String getColumnCoordinates(final int textWidth,
			final int linesAlgarismsCount, final int originColumn) {
		
		final int columnsAlgarismsCount = String.valueOf(Math.abs(textWidth - originColumn)).length();
		
		final StringBuilder builder = new StringBuilder();
		
		for (int algarismsColumnLine = 0; algarismsColumnLine< columnsAlgarismsCount; algarismsColumnLine++ ){
			builder.append(StringUtils.repeat(" ", linesAlgarismsCount));
			for (int algarismsColumnLineColumn = 0 - originColumn; algarismsColumnLineColumn < textWidth - originColumn; algarismsColumnLineColumn++){
				final String algarismColumnString = StringUtils.leftPad(String.valueOf(Math.abs(algarismsColumnLineColumn)), columnsAlgarismsCount);
				builder.append(algarismColumnString.charAt(algarismsColumnLine));
			}
			builder.append("\n");
		}
		final String result = builder.toString();
		return result;
	}

	
	
}
