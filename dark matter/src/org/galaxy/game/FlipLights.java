package org.galaxy.game;

public class FlipLights {

	public static void main(String[] args) {
		boolean[] lights = new boolean[100];
		for (int i = 0; i < lights.length; i++) {
			lights[i] = false;
		}
		for (int i = 1; i <= lights.length; i++) {
			for (int j = 0; j < lights.length; j++) {
				if ((j+1) % i == 0) {
					lights[j] = !lights[j];
				}
			}
		}
		
		for (int i = 0; i < lights.length; i++) {
			if (lights[i]) {
				System.out.print((i+1) + "||");
			}
		}
	}
}
