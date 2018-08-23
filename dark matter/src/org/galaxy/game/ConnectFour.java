package org.galaxy.game;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour {
	
	public static final int MAX_ROW = 6;
	public static final int MAX_COLUMN = 7;
	
	private static final char EMPTY_CELL = ' ';
	private static final char P1 = 'X';
	private static final char P2 = 'O';
	
	private char[][] board;
	
	public static void main(String[] args) {
		ConnectFour cf = new ConnectFour();
		
		for (int k = 0; k < 21; k++) {
			int[] pos1 = cf.playRandom(P1);
			cf.printBoard();
			if (cf.checkWin(pos1[0], pos1[1], P1)) {
				System.out.println("Player 1 Win.");
				break;
			}
			int[] pos2 = cf.playRandom(P2);
			cf.printBoard();
			if (cf.checkWin(pos2[0], pos2[1], P2)) {
				System.out.println("Player 2 Win.");
				break;
			}
		}
	}
	
	public ConnectFour() {
		this.board = new char[MAX_ROW][MAX_COLUMN];
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COLUMN; j++) {
				board[i][j] = EMPTY_CELL;
			}
		}
	}

	private boolean checkWin(int row, int col, char c) {
		if (checkVertical(row, col, c)) {return true;}
		if (checkHorizontal(row, col, c)) {return true;}
		if (checkDiagonalAsc(row, col, c)) {return true;}
		if (checkDiagonalDsc(row, col, c)) {return true;}
		return false;
	}
	
	private boolean checkDiagonalAsc(int row, int col, char c) {
		int cnt = 1;
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == c) {
				cnt++;
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col + 1; i < MAX_ROW && j < MAX_COLUMN; i++, j++) {
			if (board[i][j] == c) {
				cnt++;
			} else {
				break;
			}
		}
		return cnt >= 4;
	}
	
	private boolean checkDiagonalDsc(int row, int col, char c) {
		int cnt = 1;
		for (int i = row - 1, j = col + 1; i >= 0 && j < MAX_COLUMN; i--, j++) {
			if (board[i][j] == c) {
				cnt++;
			} else {
				break;
			}
		}
		for (int i = row + 1, j = col - 1; i < MAX_ROW && j >= 0; i++, j--) {
			if (board[i][j] == c) {
				cnt++;
			} else {
				break;
			}
		}
		return cnt >= 4;
	}
	
	private boolean checkHorizontal(int row, int col, char c) {
		int cnt = 1;
		//count left side
		for (int i = col - 1; i >= 0; i--) {
			if (board[row][i] == c) {
				cnt++;
			} else {
				break;
			}
		}
		//count right side
		for (int i = col + 1; i < MAX_COLUMN; i++) {
			if (board[row][i] == c) {
				cnt++;
			} else {
				break;
			}
		}
		return cnt >= 4;
	}
	
	private boolean checkVertical(int row, int col, char c) {
		return (row >= 3 && board[row - 3][col] == c && board[row - 2][col] == c && board[row - 1][col] == c);
	}
	
	private int[] playRandom(char c) {
		int[] pos = getRandomMove();
		board[pos[0]][pos[1]] = c;
		return pos;
	}
	
	private int[] getRandomMove() {
		int col = getRandomColumn();
		int row = getAvailableRow(col);
		return new int[]{row, col};
	}
	
	private int getAvailableRow(int column) {
		for (int i = 0; i < MAX_ROW; i++) {
			if (board[i][column] == EMPTY_CELL) {
				return i;
			}
		}
		return -1;
	}
	
	private int getRandomColumn() {
		List<Integer> availColumns = getAvailableColumns();
		int index = ((int)(Math.random() * 9973.0)) % availColumns.size();
		return availColumns.get(index);
	}
	
	private List<Integer> getAvailableColumns() {
		List<Integer> columns = new ArrayList<Integer>();
		for (int j = 0; j < MAX_COLUMN; j++) {
			if (board[MAX_ROW - 1][j] == EMPTY_CELL) {
				columns.add(j);
			}
		}
		return columns;
	}

	
	private void printBoard() {
		for (int i = MAX_ROW - 1; i >= 0; i--) {
			for (int j = 0; j < MAX_COLUMN; j++) {
				System.out.print(" ");
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		for (int j = 0; j < MAX_COLUMN; j++) {
			System.out.print("--");
		}
		System.out.println();
	}
}
