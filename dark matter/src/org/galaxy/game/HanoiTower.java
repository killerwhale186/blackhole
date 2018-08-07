package org.galaxy.game;

import java.util.ArrayList;
import java.util.List;

public class HanoiTower {
	
	static int n = 5;
	static List<Integer> towerA;
	static List<Integer> towerB;
	static List<Integer> towerC;
	
	public static void main(String[] args) {

		towerA = new ArrayList<Integer>();
		towerB = new ArrayList<Integer>();
		towerC = new ArrayList<Integer>();
		for (int i = n; i > 0; i--) {
			towerA.add(0, i);
		}
		printAllTowers();
		
		move(n, towerA, towerB, towerC);
	}
	
	private static void move(int k, List<Integer> from, List<Integer> to, List<Integer> with) {
		if (k == 1) {
			int a = from.remove(0);
			to.add(0, a);
			printAllTowers();
			return;
		}
		move(k-1, from, with, to);
		int a = from.remove(0);
		to.add(0, a);
		printAllTowers();
		move(k-1, with, to, from);
	}
	
	private static void printAllTowers() {
		for (int i = 0; i < n; i++) {
			printTower(towerA, i);
			System.out.print("  ");
			printTower(towerB, i);
			System.out.print("  ");
			printTower(towerC, i);
			System.out.println();
		}
		System.out.println("-----  -----  -----");
	}
	
	private static void printTower(List<Integer> tower, int k) {
		System.out.print("  ");
		int index = k - (n - tower.size());
		if (index < 0) {
			System.out.print(" ");
		} else {
			System.out.print(tower.get(index));
		}
		System.out.print("  ");
	}
}
