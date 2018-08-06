package org.galaxy.math;

public class Polynomial {
	
	public static final Polynomial NEGATIVE_ONE = new Polynomial(new int[]{-1});
	public static final Polynomial ZERO = new Polynomial(new int[]{0});
	
	private int[] coefficients;

	public static void main(String[] args) {
		int[] d1 = {1, 0, 0, 0, 0, 2};
		int[] d2 = {1, 0, 1};
		Polynomial p1 = new Polynomial(d1);
		Polynomial p2 = new Polynomial(d2);
		Polynomial p3 = p1.divide(p2);
		System.out.println(p3.toString());
	}
	
	private void removeLeadingZero() {
		while (coefficients.length > 1 && coefficients[0] == 0) {
			int[] newcoef = new int[coefficients.length-1];
			for (int i = 0; i < newcoef.length; i++) {
				newcoef[i] = coefficients[i+1];
			}
			coefficients = newcoef;
		}
	}
	
	public Polynomial(int[] coefficients) {
		this.coefficients = coefficients;
		removeLeadingZero();
	}
	
	//short cut construct kx^n
	public Polynomial(int coef, int degree) {
		coefficients = new int[degree + 1];
		coefficients[0] = coef;
		for (int i = 1; i < degree + 1; i++) {
			coefficients[i] = 0;
		}
	}

	
	public Polynomial multiply(Polynomial p) {
		int[] d = new int[this.coefficients.length + p.coefficients.length - 1];
		for (int i = 0; i < d.length; i++) {
			d[i] = 0;
		}
		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < p.coefficients.length; j++) {
				int k = i + j;
				d[k] += this.coefficients[i] * p.coefficients[j];
			}
		}
		
		return new Polynomial(d);
	}
	
	public int getDegree() {
		return (coefficients.length - 1);
	}
	
	public Polynomial add(Polynomial p) {
		int max = Math.max(this.getDegree(), p.getDegree());
		int[] a = new int[max + 1];
		for (int i = 0; i < a.length; i++) {
			int j = i + this.coefficients.length - max -1;
			int k = i + p.coefficients.length - max -1;
			if (j < 0) {
				a[i] = p.coefficients[k];
			} else if (k < 0) {
				a[i] = this.coefficients[j];
			} else {
				a[i] = this.coefficients[j] + p.coefficients[k];
			}
		}
		return new Polynomial(a);
	}
	
	public Polynomial subtract(Polynomial p) {
		return this.add(p.multiply(NEGATIVE_ONE));
	}
	
	//assume p has leading coefficient = 1
	public Polynomial divide(Polynomial p) {
		int[] q = new int[this.getDegree() - p.getDegree() + 1];
		Polynomial temp = this;
		for (int k = 0; k < q.length; k++) {
			int degree = temp.getDegree() - p.getDegree();
			if (degree < 0) {
				break;
			}
			q[k] = temp.coefficients[0];
			Polynomial mono = new Polynomial(q[k], degree);
			temp = temp.subtract(p.multiply(mono));
		}
		
		System.out.println("remainder: " + temp);
		return new Polynomial(q);
	}
	
	public Polynomial power(int n) {
		int[] a = {1};
		Polynomial p = new Polynomial(a);
		for (int i = 0; i < n; i++) {
			p = p.multiply(this);
		}
		return p;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < this.coefficients.length; i++) {
			if (i > 0) {
				if (this.coefficients[i] > 0) {
					buf.append("+");
				}
			}
			if (this.coefficients[i] != 0) {
				int exp = (this.coefficients.length - 1 - i);
				if (this.coefficients[i] != 1 || exp == 0) {
					buf.append(this.coefficients[i]);
				}
				if (exp > 1) {
					buf.append("x^" + String.valueOf(exp));
				} else if (exp == 1) {
					buf.append("x");
				}
			}
		}
		return buf.toString();
	}
	

}
