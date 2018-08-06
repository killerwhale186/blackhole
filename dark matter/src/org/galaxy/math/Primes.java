package org.galaxy.math;

public class Primes {
	public static boolean isPrime(int a) {
		int upperBound = (int)Math.sqrt(a);
		for (int i = 2; i <= upperBound; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
}
