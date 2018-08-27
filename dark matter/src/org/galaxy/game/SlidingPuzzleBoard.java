package org.galaxy.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.galaxy.util.MathUtil;

public class SlidingPuzzleBoard {

	public final static char EMPTY_SPACE = ' ';
	
	private char[][] board;
	private SlidingPuzzleBoard parent = null;

	public static SlidingPuzzleBoard createStandard() {
		SlidingPuzzleBoard s = new SlidingPuzzleBoard();
		s.setValue(0, 0, '1');
		s.setValue(0, 1, '2');
		s.setValue(0, 2, '3');
		s.setValue(1, 0, '4');
		s.setValue(1, 1, '5');
		s.setValue(1, 2, '6');
		s.setValue(2, 0, '7');
		s.setValue(2, 1, '8');
		s.setValue(2, 2, EMPTY_SPACE);
		return s;
	}
	
	private void move(int[] from, int[] to) {
		setValue(to[0], to[1], getValue(from[0], from[1]));
		setValue(from[0], from[1], EMPTY_SPACE);
	}
	
	public List<SlidingPuzzleBoard> getNextBoards() {
		List<SlidingPuzzleBoard> ret = new ArrayList<SlidingPuzzleBoard>();
		int[] empty = findEmpty();
		for (int[] itr : getValidMoves(empty)) {
			SlidingPuzzleBoard newBoard = this.clone();
			newBoard.move(itr, empty);
			ret.add(newBoard);
		}
		return ret;
	}
	
	private List<int[]> getValidMoves(int[] empty) {
		int ex = empty[0];
		int ey = empty[1];
		List<int[]> validMoves = new ArrayList<int[]>();
		if (ex - 1 >= 0) {
			validMoves.add(new int[]{ex - 1, ey});
		}
		if (ex + 1 <= 2) {
			validMoves.add(new int[]{ex + 1, ey});
		}
		if (ey - 1 >= 0) {
			validMoves.add(new int[]{ex, ey - 1});
		}
		if (ey + 1 <= 2) {
			validMoves.add(new int[]{ex, ey + 1});
		}
		return validMoves;
	}
	
	public void shuffle(int cnt) {
		for (int i = 0; i < cnt; i++) {
			int[] empty = findEmpty();
			List<int[]> validMoves = getValidMoves(empty);
			int index = MathUtil.getRandom(validMoves.size());
			int[] from = validMoves.get(index);
			move(from, empty);
			//System.out.println(this.toString());
		}
	}
	
	private int[] findEmpty() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.board[i][j] == EMPTY_SPACE) {
					return new int[]{i, j};
				}
			}
		}
		//should never get here
		return null;
	}
	
	public SlidingPuzzleBoard getParent() {
		return parent;
	}

	public void setParent(SlidingPuzzleBoard parent) {
		this.parent = parent;
	}

	public SlidingPuzzleBoard clone() {
		SlidingPuzzleBoard s = new SlidingPuzzleBoard();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				s.board[i][j] = this.board[i][j];
			}
		}
		return s;
	}

	public SlidingPuzzleBoard() {
		board = new char[3][3];
	}

	public char getValue(int i, int j) {
		return board[i][j];
	}

	public void setValue(int i, int j, char val) {
		board[i][j] = val;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buf.append(this.board[i][j]).append(" ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlidingPuzzleBoard other = (SlidingPuzzleBoard) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

}
