/**
 * This class represents the fractions in math
 */
public class Fraction {
	private int denominater;
	private int numerator;
	
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
		int factor = findFactor(this.numerator, this.denominater);
		while (factor > 1) {
			this.numerator = this.numerator / factor;
			this.denominater = this.denominater / factor;
			factor = findFactor(this.numerator, this.denominater);
		}
	}
	
	//find a common factor, 1 if none exists
	private int findFactor(int a, int b) {
		int min = Math.min(a, b);
		for (int i = 2; i <= min; i++) {
			if (a % i == 0 && b % i == 0) {
				return i;
			}
		}
		return 1;
	}
	
	public String toString() {
		return this.numerator + "/" + this.denominater;
	}
}
