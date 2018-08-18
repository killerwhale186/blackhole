package org.galaxy.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SquarePlayer {
	
	private Set<String> allSquares = new HashSet<String>();
	private List<Square> currSquares = new ArrayList<Square>();
	private List<Square> nextSquares = new ArrayList<Square>();
	
	public static void main(String[] args) {
		SquarePlayer sqr = new SquarePlayer();
		sqr.run();
	}
	
	private void run() {
		
		Square target = null;

		Square s = Square.createStandard();
		
		allSquares.add(s.toString());
		currSquares.add(s);
		
		int runCnt = 1;
		do {
			for (Square itr : currSquares) {
				if (itr.getValue(0, 0) == Square.EMPTY_SPACE) {
					move(itr, 0, 1, 0, 0);
					move(itr, 1, 0, 0, 0);
				} else if (itr.getValue(0, 1) == Square.EMPTY_SPACE) {
					move(itr, 0, 0, 0, 1);
					move(itr, 1, 1, 0, 1);
					move(itr, 0, 2, 0, 1);
				} else if (itr.getValue(0, 2) == Square.EMPTY_SPACE) {
					move(itr, 0, 1, 0, 2);
					move(itr, 1, 2, 0, 2);
				} else if (itr.getValue(1, 0) == Square.EMPTY_SPACE) {
					move(itr, 0, 0, 1, 0);
					move(itr, 1, 1, 1, 0);
					move(itr, 2, 0, 1, 0);
				} else if (itr.getValue(1 ,1) == Square.EMPTY_SPACE) {
					move(itr, 0, 1, 1, 1);
					move(itr, 1, 0, 1, 1);
					move(itr, 1, 2, 1, 1);
					move(itr, 2, 1, 1, 1);
				} else if (itr.getValue(1, 2) == Square.EMPTY_SPACE) {
					move(itr, 0, 2, 1, 2);
					move(itr, 1, 1, 1, 2);
					move(itr, 2, 2, 1, 2);
				} else if (itr.getValue(2, 0) == Square.EMPTY_SPACE) {
					move(itr, 1, 0, 2, 0);
					move(itr, 2, 1, 2, 0);
				} else if (itr.getValue(2, 1) == Square.EMPTY_SPACE) {
					move(itr, 1, 1, 2, 1);
					move(itr, 2, 0, 2, 1);
					move(itr, 2, 2, 2, 1);
				} else if (itr.getValue(2, 2) == Square.EMPTY_SPACE) {
					move(itr, 1, 2, 2, 2);
					move(itr, 2, 1, 2, 2);
				} 
				target = itr;
			}

			if (nextSquares.isEmpty()) {
				break;
			}
			currSquares = nextSquares;
			nextSquares = new ArrayList<Square>();
			System.out.println(runCnt + "=>" + allSquares.size());
			runCnt++;
		} while (true);
		
		Stack<Square> stack = new Stack<Square>();
		while (target != null) {
			stack.push(target);
			target = target.getParent();
		}
		while (stack.isEmpty() == false) {
			System.out.println(stack.pop().toString());
		}
	}
	
	private void move(Square itr, int fi, int fj, int ti, int tj) {
		Square s1 = itr.clone();
		s1.setValue(fi, fj, Square.EMPTY_SPACE);
		s1.setValue(ti, tj, itr.getValue(fi, fj));
		if (!allSquares.contains(s1.toString())) {
			s1.setParent(itr);
			nextSquares.add(s1);
			allSquares.add(s1.toString());
		}
	}
}
