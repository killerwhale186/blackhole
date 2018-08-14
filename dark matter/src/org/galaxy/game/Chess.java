package org.galaxy.game;

public class Chess {

	public static final int BOARD_SIZE = 8;
	
	public static void main(String[] args) {
		knightMoves(7, 7, 0, 0);
	}

	private static void knightMoves(int initX, int initY, int endX, int endY) {
		
		ChessBlock[][] board = initBoard(initX, initY);
		
		//
		int filledBlock = 1;
		int currMoves = 0;
		while (filledBlock < BOARD_SIZE * BOARD_SIZE) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (board[i][j].getMoves() == currMoves) {
						filledBlock += tryMove(board, currMoves, i, j, 1, 2);
						filledBlock += tryMove(board, currMoves, i, j, -1, 2);
						filledBlock += tryMove(board, currMoves, i, j, 1, -2);
						filledBlock += tryMove(board, currMoves, i, j, -1, -2);
						filledBlock += tryMove(board, currMoves, i, j, 2, 1);
						filledBlock += tryMove(board, currMoves, i, j, -2, 1);
						filledBlock += tryMove(board, currMoves, i, j, 2, -1);
						filledBlock += tryMove(board, currMoves, i, j, -2, -1);
					}
				}
			}
			currMoves++;
		}
		
		//turn on visible blocks, going backward
		ChessBlock currBlock = board[endX][endY];
		do {
			currBlock.setVisible(true);
			currBlock = currBlock.getParent();
		} while (currBlock != null);
		
		printBoard(board);
	}
	
	private static ChessBlock[][] initBoard(int initX, int initY) {
		ChessBlock[][] board = new ChessBlock[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new ChessBlock();
			}
		}
		board[initX][initY].setMoves(0);
		return board;
	}
	
	private static int tryMove(ChessBlock[][]board, int currMove, int i, int j, int ii, int jj) {
		if (i + ii >= 0 && i + ii < BOARD_SIZE && j + jj >= 0 && j + jj < BOARD_SIZE && board[i+ii][j+jj].getMoves() == -1) {
			board[i+ii][j+jj].setMoves(currMove + 1);
			board[i+ii][j+jj].setParent(board[i][j]);
			return 1;
		} else {
			return 0;
		}
	}
	
	private static void printBoard(ChessBlock[][] board) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j].isVisible()) {
					System.out.print(" " + board[i][j].getMoves());
				} else {
					System.out.print(" " + ".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
} 
