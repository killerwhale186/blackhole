package org.galaxy.geometry;

public class Triangle {

	private Point a;
	private Point b;
	private Point c;
	
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean isCongruent(Triangle t) {
		return false;
	}
	
	public boolean isSimilar(Triangle t) {
		return false;
	}

	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
	
}
