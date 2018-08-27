package org.galaxy.util;

public class MathUtil {
	public static int getRandom(int size) {
		double r = Math.random();
		return ((int)(r * 99719)) % size;
	}
}
