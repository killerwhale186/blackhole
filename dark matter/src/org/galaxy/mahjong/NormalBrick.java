package org.galaxy.mahjong;

public class NormalBrick extends Brick {

	private BrickType brickType;
	private int cardinal;
	
	public NormalBrick(BrickType brickType, int cardinal) {
		this.brickType = brickType;
		this.cardinal = cardinal;
	}
	
	public String toString() {
		return String.valueOf(this.cardinal) + " " + this.brickType;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof NormalBrick) {
			NormalBrick b = (NormalBrick)obj;
			return (this.brickType == b.brickType && this.cardinal == b.cardinal);
		} else { 
			return false;
		}
	}
}
