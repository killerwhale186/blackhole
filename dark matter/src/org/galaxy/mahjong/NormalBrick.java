package org.galaxy.mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public boolean isSequence(Brick b2, Brick b3) {
		//System.out.println("calling isSequence: " + this + " " + b2 + " " + b3);
		if (b2 instanceof NormalBrick && b3 instanceof NormalBrick) {
			NormalBrick nb2 = (NormalBrick)b2;
			NormalBrick nb3 = (NormalBrick)b3;
			if (this.brickType == nb2.brickType && nb2.brickType == nb3.brickType) {
				List<Integer> n = new ArrayList<Integer>();
				n.add(this.cardinal);
				n.add(nb2.cardinal);
				n.add(nb3.cardinal);
				Collections.sort(n);
				boolean isSeq = (n.get(0) + 1 == n.get(1) && n.get(1) + 1 == n.get(2));
				//System.out.println(isSeq);
				return isSeq;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
