package org.galaxy.math;


/**
 * This class represents the fractions in math
 */
public class Fraction extends FractionExpression {
	
	private int denominater;
	private int numerator;
	
	public static Fraction Zero = new Fraction(0);
	
	public Fraction(int n) {
		this(n, 1);
	}
	
	public Fraction(int n, int d) {
		if (d == 0) {
			throw new RuntimeException("Denominator cannot be 0!");
		}
		this.numerator = n;
		this.denominater = d;
		reduce();
	}
	
	public int getSign() {
		return numerator * denominater;
	}
	
	public Fraction add(Fraction b) {
		Fraction sum = new Fraction(this.numerator * b.denominater + this.denominater * b.numerator, this.denominater * b.denominater);
		return sum;
	}
	
	public Fraction subtract(Fraction b) {
		Fraction sum = new Fraction(this.numerator * b.denominater - this.denominater * b.numerator, this.denominater * b.denominater);
		return sum;
	}
	
	public Fraction multiply(Fraction b) {
		return new Fraction(this.numerator * b.numerator, this.denominater * b.denominater);
	}

	public Fraction divide(Fraction b) {
		return new Fraction(this.numerator * b.denominater, this.denominater * b.numerator);
	}
	
	public Fraction inverse() {
		return new Fraction(this.denominater, this.numerator);
	}

	//reduce something like 15/20 to 3/4
	private void reduce() {
		if (this.denominater < 0) {
			this.denominater *= (-1);
			this.numerator *= (-1);
		}
		int gcd = NumberUtil.getGCD(this.numerator, this.denominater);
		this.numerator /= gcd;
		this.denominater /= gcd;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Fraction) {
			Fraction f = (Fraction)o;
			return this.numerator * f.denominater == this.denominater * f.numerator;
		} else {
			return false;
		}
	}

	@Override
	public Fraction getValue() {
		return this;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.numerator;
        result = prime * result + this.denominater;
        return result;
    }
    
	public String toString() {
		if (this.denominater == 1) {
			return this.numerator + "";
		}
		return this.numerator + "/" + this.denominater;
	}
}
