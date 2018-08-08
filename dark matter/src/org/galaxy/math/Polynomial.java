package org.galaxy.math;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
	
	public static final Polynomial NEGATIVE_ONE = new Polynomial(new int[]{-1});
	public static final Polynomial ZERO = new Polynomial(new int[]{0});
	
	private int[] coefficients;

	public static void main(String[] args) {
		Polynomial p1 = new Polynomial(new int[]{1, 2});
		Polynomial p2 = new Polynomial(new int[]{1, -3});
		Polynomial p3 = new Polynomial(new int[]{1, 1});
		Polynomial p4 = new Polynomial(new int[]{1, -2});
		Polynomial p5 = p1.multiply(p2).multiply(p3).multiply(p4);

		List<Polynomial> factors = p5.factor();
		for (Polynomial p : factors) {
			System.out.println(p);
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

	public int getDegree() {
		return (coefficients.length - 1);
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
	
	//assume p has leading coefficient = 1, returns the quotient and remainder
	public Polynomial[] divide(Polynomial p) {
		int degreeDiff = this.getDegree() - p.getDegree();
		if (degreeDiff < 0) {
			return new Polynomial[]{ZERO, p};
		}
		int[] q = new int[degreeDiff + 1];
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
		
		return new Polynomial[]{new Polynomial(q), temp};
	}
	
	public Polynomial pow(int n) {
		int[] a = {1};
		Polynomial p = new Polynomial(a);
		for (int i = 0; i < n; i++) {
			p = p.multiply(this);
		}
		return p;
	}
	
	public int evaluate(int x) {
		int total = 0;
		int degree = this.getDegree();
		for (int i = 0; i < this.coefficients.length; i++) {
			total += this.coefficients[i] * NumberUtil.myPow(x, degree - i);
		}
		return total;
	}
	
	public List<Polynomial> factor() {
		List<Polynomial> factors = new ArrayList<Polynomial>();
		Polynomial temp = this;
		while (true) {
			List<Integer> coefFactors = NumberUtil.getFactors(Math.abs(temp.coefficients[temp.getDegree()]));
			Polynomial p = null;
			for (int k : coefFactors) {
				if (temp.evaluate(k) == 0) {
					p = new Polynomial(new int[]{1, (-1)*k});
					factors.add(p);
					temp = temp.divide(p)[0];
					break;
				}
				if (temp.evaluate((-1)*k) == 0) {
					p = new Polynomial(new int[]{1, k});
					factors.add(p);
					temp = temp.divide(p)[0];
					break;
				}
			}
			if (p == null || temp.getDegree() == 1) {
				break;
			}
		}
		factors.add(temp);
		return factors;
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
			if (this.coefficients[i] != 0 || this.getDegree() == 0) {
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
	
	
	private void removeLeadingZero() {
		while (coefficients.length > 1 && coefficients[0] == 0) {
			int[] newcoef = new int[coefficients.length-1];
			for (int i = 0; i < newcoef.length; i++) {
				newcoef[i] = coefficients[i+1];
			}
			coefficients = newcoef;
		}
	}
	
}
