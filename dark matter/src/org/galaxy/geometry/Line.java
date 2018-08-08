package org.galaxy.geometry;

public class Line {

	private Point a;
	private Point b;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public double getSlope() {
		return (b.y - a.y) / (b.x - a.x);
	}
	
	public boolean isParallel(Line line) {
		return this.getSlope() == line.getSlope();
	}
	
	public boolean isPerpendicular(Line line) {
		return this.getSlope() * line.getSlope() == -1;
	}

	@Override
	public String toString() {
		return "Line [a=" + a + ", b=" + b + "]";
	}
	
	
}
