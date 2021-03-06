package empire;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import commandLine.EmpireMapTextPrinter;

import empire.buildings.Building;
import empire.buildings.Castle;

public class EmpireMap {

	private final Map<Point, Building> buildings = new LinkedHashMap<Point, Building>();

	public EmpireMap() {
		internalAddBuilding(new Castle(), new Point(0, 0));
	}

	public Dimension mapDimension() {

		final Point upperLeft = calculateUpperLeft();
		final Point bottomRight = calculateBottomRight();

		return new Dimension((int) (bottomRight.getX() - upperLeft.getX()),
				(int) (bottomRight.getY() - upperLeft.getY()));
	}

	public Point calculateUpperLeft() {
		final Point upperLeft = new Point();
		for (final Map.Entry<Point, Building> entry : buildings.entrySet()) {
			final Building building = entry.getValue();
			final Point point = entry.getKey();
			final int sight = building.getSight();
	
			final int buildingUpperLeftX = point.x - sight;
			final int buildingUpperLeftY = point.y - sight;
			final Point upperLeftPoint = new Point(buildingUpperLeftX,
					buildingUpperLeftY);
			if (upperLeftPoint.x < upperLeft.x)
				upperLeft.x = upperLeftPoint.x;
			if (upperLeftPoint.y < upperLeft.y)
				upperLeft.y = upperLeftPoint.y;
		}
		return upperLeft;
	}

	public void addBuilding(final Building building, final Point point)
			throws OccupiedPointException, PointNotInSightException {
		if (pointIsOccupied(point,building))//TODO: create valid addBuilding
			throw new OccupiedPointException();
		if(intersectsOtherBuilding(point,building))
			throw new OccupiedPointException();
		if (!isPointInSight(point))
			throw new PointNotInSightException();
	
		internalAddBuilding(building, point);
	}
	
	public Dimension getOriginOffset(){
		Point upperLeft = calculateUpperLeft();
		final int x = upperLeft.x;
		final int y = upperLeft.y;		
		return new Dimension(x, y);
	}

	private Point calculateBottomRight() {
		final Point bottomRight = new Point();
		for (final Map.Entry<Point, Building> entry : buildings.entrySet()) {
			final Building building = entry.getValue();
			final Point point = entry.getKey();
			final int sight = building.getSight();

			final int buildingBottomRightX = (int) (point.x
					+ building.getWidth() + sight);
			final int buildingBottomRightY = (int) (point.y
					+ building.getHeight() + sight);
			final Point buildingBottomRight = new Point(buildingBottomRightX,
					buildingBottomRightY);
			if (buildingBottomRight.x > bottomRight.x)
				bottomRight.x = buildingBottomRight.x;
			if (buildingBottomRight.y > bottomRight.y)
				bottomRight.y = buildingBottomRight.y;

		}
		return bottomRight;
	}

	private boolean intersectsOtherBuilding(final Point point,final Building building) {
		final Set<Entry<Point, Building>> entrySet = buildings.entrySet();
		final Rectangle buildingToCompare = new Rectangle(point,building.getDimension()) ;
		for (Entry<Point, Building> entry : entrySet) {
			final Rectangle buildingArea = new Rectangle(entry.getKey(),entry.getValue().getDimension()) ;
			if(buildingToCompare.intersects(buildingArea))
				return true;
		}
		return false;
	}

	private boolean pointIsOccupied(final Point point, final Building buildingOrNull ) {
		return getBuildingInPointIfAnyOrNull(point) != null;
	}

	public void removeBuilding(final Point point) throws EmptyPointException {
		if (!pointIsOccupied(point))
			throw new EmptyPointException();
		
		buildings.remove(point);
	}

	private boolean pointIsOccupied(final Point point) {
		return pointIsOccupied(point, null);
	}

	private void internalAddBuilding(final Building building,
			final Point point) {
		buildings.put(point, building);
	}

	private Set<Entry<Point, Building>> buildings() {
		return buildings.entrySet();
	}

	private Point getSightPosition(final Building b,
			final Point buildingPosition) {
		final Point sightPosition = (Point) buildingPosition.clone();
		sightPosition.translate(b.getSight() * -1, b.getSight() * -1);
		return sightPosition;
	}

	public Building getBuildingInPointIfAnyOrNull(final Point pointOnMap) {
		for (final Map.Entry<Point, Building> entry : buildings()) {

			final Building b = entry.getValue();
			final Point buildingPosition = entry.getKey();

			final Rectangle buildingRect = new Rectangle(buildingPosition, b
					.getDimension());
			if (buildingRect.contains(pointOnMap))
				return b;
		}
		return null;
	}

	public boolean isPointInSight(final Point pointOnMap) {
		for (final Map.Entry<Point, Building> entry : buildings()) {

			final Building b = entry.getValue();
			final Point buildingPosition = entry.getKey();
			final Point sightPosition = getSightPosition(b, buildingPosition);
			final Dimension sightSize = b.getSightSize();

			final Rectangle sightRect = new Rectangle(sightPosition, sightSize);

			if (sightRect.contains(pointOnMap))
				return true;
		}
		return false;
	}

	public Point getAbsoluteCenterPoint() {
		final Point upperLeft = calculateUpperLeft();
		return new Point(Math.abs(upperLeft.x), Math.abs(upperLeft.y));
	}

	@Override
	public String toString() {
		return EmpireMapTextPrinter.printMapWithCoords(this);
	}

}
