package org.galaxy.math;

import java.util.*;

public class NumberUtil {

	public static void main(String[] args) {
		//int c = getGCD(0, 40);
		//System.out.println(c);
		
		//System.out.println(isPrime(67));
		//System.out.println(isPrime(143));
		//System.out.println(isPrime(2));
		//System.out.println(isPrime(49));
		
		//genFibonacci(10);
		List<String> res = pythagoreanSolutions(300, 2);
		for (String s : res) {
			System.out.println(s);
		}
	}

	public static List<String> pythagoreanSolutions(Integer max, Integer p) {
		List<String> res = new ArrayList<String>();
		for (Integer a = 1; a <= max; a++) {
			for (Integer b = a + 1; b <= max; b++) {
				for (Integer c = b + 1; c <= max; c++) {
					if (pow(a, p) + pow(b, p) == pow(c, p)) {
						if (hasFactor(a, b, c) == false) {
							res.add(a + "^" + p + " + " + b + "^" + p + " = " + c + "^" + p);
						}
					}
				}
			}
		}
		return res;
	}

	private static Long pow(Integer base, Integer exp) {
		Long ret = 1L;
		for (Integer i = 1; i <= exp; i++) {
			ret *= base;
		}
		return ret;
	}

	private static boolean hasFactor(Integer a, Integer b, Integer c) {
		for (Integer i = 2; i <= a; i++) {
			if (a % i == 0 && b % b == 0 && c % i == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPrime(int a) {
		int upperBound = (int)Math.sqrt(a);
		for (int i = 2; i <= upperBound; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int getGCD(int a, int b) {
		if (a < 0) {a = a * (-1);}
		if (b < 0) {b = b * (-1);}
		if (a < b) {
			return getGCD(b, a);
		}
		if (b == 0) {
			return a;
		}
		if (a % b == 0) {
			return b;
		}
		return getGCD(b, a % b);
	}
	
	
	//generate the first m Fibonacci numbers
	public static void genFibonacci(int m) {
		int a = 1;
		int b = 1;
		System.out.println(a);
		System.out.println(b);
		for (int i = 0; i < m - 2; i++) {
			int c = a;
			a = b;
			b = c + b;
			System.out.println(b);
		}
	}
	
}
