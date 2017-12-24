package org.galaxy.math;

public class Test {
	
	public static void main(String[] args) {
		
		Fraction f = new Fraction(90, 24);
		System.out.println(f);
		
		ComplexNumber a = new ComplexNumber(1, 2);
		ComplexNumber b = new ComplexNumber(3, 4);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		ComplexNumber sum = a.add(b);
		System.out.println("a + b = " + sum);
		
		ComplexNumber diff = a.minus(b);
		System.out.println("a - b = " + diff);
		
		//test multiply
		ComplexNumber prod = a.multiply(b);
		System.out.println("a * b = " + prod);

		//test norm
		double norm = a.getNorm();
		System.out.println("|a| = " + norm);
		
		System.out.println();
		
		Matrix m1 = new Matrix(3, 3);
		System.out.println(m1);
		Matrix m2 = new Matrix(3, 3);
		System.out.println(m2);
		
		Matrix m3 = m1.add(m2);
		System.out.println(m3);
		
		Matrix m4 = m1.multiple(m2);
		System.out.println(m4);
		
		Matrix sub = m1.getSubMatrix(1, 1);
		System.out.println(sub);
		
		double det = m1.getDeterminant();
		System.out.println(det);
	}
}

