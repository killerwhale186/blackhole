package org.galaxy.game;

public class ChessBlock {

	private int moves;
	private ChessBlock parent;
	private boolean visible;
	
	public ChessBlock() {
		this.moves = -1;
		this.parent = null;
		this.visible = false;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public ChessBlock getParent() {
		return parent;
	}

	public void setParent(ChessBlock parent) {
		this.parent = parent;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
