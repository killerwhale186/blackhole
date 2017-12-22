package org.galaxy.mahjong;


public class WindBrick extends Brick {

	private WindType windType;
	
	public WindBrick(WindType windType) {
		this.windType = windType;
	}
	
	public String toString() {
		return String.valueOf(windType);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof WindBrick) {
			WindBrick b = (WindBrick)obj;
			return (this.windType == b.windType);
		} else { 
			return false;
		}
	}

}
