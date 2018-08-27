package org.galaxy.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingPuzzle {

	public static void main(String[] args) {
		SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
		//slidingPuzzle.solve(null);
		slidingPuzzle.solveOne();
	}
	
	
	private SlidingPuzzleBoard solve(SlidingPuzzleBoard target) {
		
		Set<String> allStates = new HashSet<String>();
		List<SlidingPuzzleBoard> currBoards = new ArrayList<SlidingPuzzleBoard>();
		List<SlidingPuzzleBoard> nextBoards = new ArrayList<SlidingPuzzleBoard>();
		
		SlidingPuzzleBoard s = SlidingPuzzleBoard.createStandard();
		
		allStates.add(s.toString());
		currBoards.add(s);
		
		int runCnt = 1;
		do {
			for (SlidingPuzzleBoard itr : currBoards) {
				List<SlidingPuzzleBoard> next = itr.getNextBoards();
				for (SlidingPuzzleBoard nitr: next) {
					if (!allStates.contains(nitr.toString())) {
						nitr.setParent(itr);
						nextBoards.add(nitr);
						allStates.add(nitr.toString());
					}
					if (target != null && nitr != null && nitr.equals(target)) {
						return nitr;
					}
				}
			}

			if (nextBoards.isEmpty()) {
				break;
			}
			currBoards = nextBoards;
			nextBoards = new ArrayList<SlidingPuzzleBoard>();
			System.out.println(runCnt + "=>" + allStates.size());
			runCnt++;
		} while (true);
		
		return null;
	}
	
	private void solveOne() {
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

}
