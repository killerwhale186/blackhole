package org.galaxy.math;

public class ComplexNumber {

	private double x;
	private double y;
	
	public ComplexNumber(double xIn) {
		this.x = xIn;
		this.y = 0.0;
	}

	public ComplexNumber(double xIn, double yIn) {
		this.x = xIn;
		this.y = yIn;
	}
	
	public ComplexNumber add(ComplexNumber b) {
		return new ComplexNumber(this.x + b.x, this.y + b.y);
	}
	
	public ComplexNumber minus(ComplexNumber b) {
		return new ComplexNumber(this.x - b.x, this.y - b.y);
	}
	
	public ComplexNumber multiply(ComplexNumber b) {
		return new ComplexNumber(this.x * b.x - this.y * b.y, this.x * b.y + this.y * b.x);
	}
	
	public ComplexNumber conjugate() {
		return new ComplexNumber(this.x, (-1) * this.y);
	}
	
	public double getNorm() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	//TODO: need some more work, to handle negative y, or x = 0, y = 0 etc.
	public String toString() {
		return x + "+" + y + "i";
	}
}

