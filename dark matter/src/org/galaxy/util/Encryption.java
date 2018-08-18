package org.galaxy.util;

public class Encryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "HELLO WORLD";
		String b = encrypt(a, 3);
		System.out.println(b);
	}

	private static String encrypt(String a, int key) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			buf.append((char)(c + key));
		}
		return buf.toString();
	}
}
