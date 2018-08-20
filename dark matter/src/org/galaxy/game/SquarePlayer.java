package org.galaxy.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SquarePlayer {
	
	private Set<String> allSquares = new HashSet<String>();
	private List<Square> currSquares = new ArrayList<Square>();
	private List<Square> nextSquares = new ArrayList<Square>();
	
	public static void main(String[] args) {
		SquarePlayer sqr = new SquarePlayer();
		sqr.findAll();
		//sqr.solve();
	}
	
	
	//finds all possible states
	private void findAll() {
		Square s = Square.createStandard();
		
		allSquares.add(s.toString());
		currSquares.add(s);
		
		int runCnt = 1;
		do {
			for (Square itr : currSquares) {
				int[] empty = itr.findEmpty();
				for (int[] f : itr.getValidMoves(empty)) {
					move(itr, f[0], f[1], empty[0], empty[1]);
				}
			}

			if (nextSquares.isEmpty()) {
				break;
			}
			currSquares = nextSquares;
			nextSquares = new ArrayList<Square>();
			System.out.println(runCnt + "=>" + allSquares.size());
			if (runCnt == 31) {
				System.out.println(currSquares.get(0));
				System.out.println(currSquares.get(1));
			}
			runCnt++;
		} while (true);
	}
	
	private Square solve(Square target) {

		Square s = Square.createStandard();
		
		allSquares.add(s.toString());
		currSquares.add(s);
		
		int runCnt = 1;
		do {
			for (Square itr : currSquares) {
				int[] empty = itr.findEmpty();
				for (int[] f : itr.getValidMoves(empty)) {
					Square temp = move(itr, f[0], f[1], empty[0], empty[1]);
					if (temp != null && temp.equals(target)) {
						return temp;
					}
				}
			}

			if (nextSquares.isEmpty()) {
				break;
			}
			currSquares = nextSquares;
			nextSquares = new ArrayList<Square>();
			System.out.println(runCnt + "=>" + allSquares.size());
			runCnt++;
		} while (true);
		
		return null;
	}
	
	private void solve() {
		Square target = Square.createStandard();
		target.shuffle(100);
		System.out.println("Target: ");
		System.out.println(target.toString());
		
		Square solution = solve(target);
		
		System.out.println("Solution: ");
		while (solution != null) {
			System.out.println(solution.toString());
			solution = solution.getParent();
		}
	}
	
	private Square move(Square itr, int fi, int fj, int ti, int tj) {
		Square s1 = itr.clone();
		s1.setValue(fi, fj, Square.EMPTY_SPACE);
		s1.setValue(ti, tj, itr.getValue(fi, fj));
		if (!allSquares.contains(s1.toString())) {
			s1.setParent(itr);
			nextSquares.add(s1);
			allSquares.add(s1.toString());
			return s1;
		}
		return null;
	}
}
