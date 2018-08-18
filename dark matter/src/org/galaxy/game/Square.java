package org.galaxy.game;

public class Square {

	public final static char EMPTY_SPACE = ' ';
	
	private char[][] board;
	private Square parent = null;

	public static Square createStandard() {
		Square s = new Square();
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
	
	public Square getParent() {
		return parent;
	}

	public void setParent(Square parent) {
		this.parent = parent;
	}

	public Square clone() {
		Square s = new Square();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				s.board[i][j] = this.board[i][j];
			}
		}
		return s;
	}

	public Square() {
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
}
