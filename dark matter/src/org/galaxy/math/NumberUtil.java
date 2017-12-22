package org.galaxy.math;


public class NumberUtil {

	public static void main(String[] args) {
		int c = getGCD(60, 40);
		System.out.println(c);
		
		System.out.println(isPrime(67));
		System.out.println(isPrime(143));
		System.out.println(isPrime(2));
		System.out.println(isPrime(49));
		
		genFibonacci(10);
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
		if (a < b) {
			return getGCD(b, a);
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
