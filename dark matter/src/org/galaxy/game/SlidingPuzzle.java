package org.galaxy.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingPuzzle {
	
	private Set<String> allStates = new HashSet<String>();
	private List<SlidingPuzzleBoard> currSquares = new ArrayList<SlidingPuzzleBoard>();
	private List<SlidingPuzzleBoard> nextSquares = new ArrayList<SlidingPuzzleBoard>();
	
	public static void main(String[] args) {
		SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
		//slidingPuzzle.findAll();
		slidingPuzzle.solve();
	}
	
	
	//finds all possible states
	private void findAll() {
		SlidingPuzzleBoard s = SlidingPuzzleBoard.createStandard();
		
		allStates.add(s.toString());
		currSquares.add(s);
		
		int runCnt = 1;
		do {
			for (SlidingPuzzleBoard itr : currSquares) {
				int[] empty = itr.findEmpty();
				for (int[] f : itr.getValidMoves(empty)) {
					move(itr, f[0], f[1], empty[0], empty[1]);
				}
			}

			if (nextSquares.isEmpty()) {
				break;
			}
			currSquares = nextSquares;
			nextSquares = new ArrayList<SlidingPuzzleBoard>();
			System.out.println(runCnt + "=>" + allStates.size());
			if (runCnt == 31) {
				System.out.println(currSquares.get(0));
				System.out.println(currSquares.get(1));
			}
			runCnt++;
		} while (true);
	}
	
	private SlidingPuzzleBoard solve(SlidingPuzzleBoard target) {

		SlidingPuzzleBoard s = SlidingPuzzleBoard.createStandard();
		
		allStates.add(s.toString());
		currSquares.add(s);
		
		int runCnt = 1;
		do {
			for (SlidingPuzzleBoard itr : currSquares) {
				int[] empty = itr.findEmpty();
				for (int[] f : itr.getValidMoves(empty)) {
					SlidingPuzzleBoard temp = move(itr, f[0], f[1], empty[0], empty[1]);
					if (temp != null && temp.equals(target)) {
						return temp;
					}
				}
			}

			if (nextSquares.isEmpty()) {
				break;
			}
			currSquares = nextSquares;
			nextSquares = new ArrayList<SlidingPuzzleBoard>();
			System.out.println(runCnt + "=>" + allStates.size());
			runCnt++;
		} while (true);
		
		return null;
	}
	
	private void solve() {
		SlidingPuzzleBoard target = SlidingPuzzleBoard.createStandard();
		target.shuffle(200);
		System.out.println("Target: ");
		System.out.println(target.toString());
		
		SlidingPuzzleBoard solution = solve(target);
		
		System.out.println("Solution: ");
		while (solution != null) {
			System.out.println(solution.toString());
			solution = solution.getParent();
		}
	}
	
	private SlidingPuzzleBoard move(SlidingPuzzleBoard itr, int fi, int fj, int ti, int tj) {
		SlidingPuzzleBoard s1 = itr.clone();
		s1.setValue(fi, fj, SlidingPuzzleBoard.EMPTY_SPACE);
		s1.setValue(ti, tj, itr.getValue(fi, fj));
		if (!allStates.contains(s1.toString())) {
			s1.setParent(itr);
			nextSquares.add(s1);
			allStates.add(s1.toString());
			return s1;
		}
		return null;
	}
}
