package org.galaxy.mahjong;


import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Brick> bricks;
	
	public Deck() {
		this.bricks = new ArrayList<Brick>();
		for (int i = 0; i < 4; i++) {
			for (WindType wt: WindType.values()) {
				this.bricks.add(new WindBrick(wt));
			}
			for (BrickType wt : BrickType.values()) {
				for (int k = 1; k <=9; k++) {
					this.bricks.add(new NormalBrick(wt, k));
				}
			}
		}
	}
	
	public void shuffle() {
		int run = 1000;
		while (run > 0) {
			int index = (int) (Math.random() * this.bricks.size());
			Brick hold = this.bricks.remove(index);
			this.bricks.add(hold);
			run--;
		}
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		for (Brick bk : d.bricks) {
			System.out.print(bk + " ");
		}
		//System.out.println();
		System.out.println(d.bricks.size());
		
		Brick b1 = new NormalBrick(BrickType.TONG, 3);
		Brick b2 = new NormalBrick(BrickType.TONG, 3);
		System.out.println(b1.equals(b2));
	}
}
