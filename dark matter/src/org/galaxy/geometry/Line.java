package org.galaxy.geometry;

public class Line {

	private Point a;
	private Point b;
	
	public double getSlope() {
		return (b.y - a.y) / (b.x - a.x);
	}
	
	public boolean isParallel(Line line) {
		return this.getSlope() == line.getSlope();
	}
	
	public boolean isPerpendicular(Line line) {
		return this.getSlope() * line.getSlope() == -1;
	}
	
	
}
