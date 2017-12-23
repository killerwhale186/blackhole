package org.galaxy.mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.galaxy.util.*;

public class BrickWall {
	private List<Brick> bricks;
	
	public BrickWall() {
		this.bricks = new ArrayList<Brick>();
	}
	
	public static void main(String[] args) {
		BrickWall bw = new BrickWall();
		Brick b8 = new NormalBrick(BrickType.WAN, 9);
		Brick b13 = new WindBrick(WindType.EAST);
		Brick b9 = new NormalBrick(BrickType.TONG, 4);
		Brick b11 = new NormalBrick(BrickType.TONG, 2);
		Brick b12 = new WindBrick(WindType.EAST);
		Brick b14 = new WindBrick(WindType.EAST);
		Brick b1 = new NormalBrick(BrickType.TIAO, 5);
		Brick b2 = new NormalBrick(BrickType.WAN, 3);
		Brick b10 = new NormalBrick(BrickType.TONG, 3);
		Brick b3 = new NormalBrick(BrickType.WAN, 3);
		Brick b4 = new NormalBrick(BrickType.TIAO, 5);
		Brick b6 = new NormalBrick(BrickType.WAN, 7);
		Brick b5 = new NormalBrick(BrickType.TIAO, 5);
		Brick b7 = new NormalBrick(BrickType.WAN, 8);

		bw.addBrick(b1);
		bw.addBrick(b2);
		bw.addBrick(b3);
		bw.addBrick(b4);
		bw.addBrick(b5);
		bw.addBrick(b6);
		bw.addBrick(b7);
		bw.addBrick(b8);
		bw.addBrick(b9);
		bw.addBrick(b10);
		bw.addBrick(b11);
		bw.addBrick(b12);
		bw.addBrick(b13);
		bw.addBrick(b14);
		System.out.println(bw.huLe());
	}
	
	public void addBrick(Brick b) {
		this.bricks.add(b);
	}
	
	public boolean huLe() {
		Set<Set<Integer>> pairsIndex = SetUtil.getSubsetIndex(bricks.size(), 2);
		boolean ret = false;
		Stack<Brick> winningCombo = new Stack<Brick>();
		for (Set<Integer> pair : pairsIndex) {
			List<Integer> list = new ArrayList<Integer>(pair);
			Brick b1 = bricks.get(list.get(0));
			Brick b2 = bricks.get(list.get(1));
			if (b1.equals(b2)) {
				winningCombo.push(b1);
				winningCombo.push(b2);
				List<Brick> remainBricks = new ArrayList<Brick>(this.bricks);
				remainBricks.remove(b1);
				remainBricks.remove(b2);
				if (findTriples(remainBricks, winningCombo)) {
					ret = true;
					break;
				} else {
					winningCombo.pop();
					winningCombo.pop();
				}
			}
		}
		System.out.println(winningCombo);
		return ret;
	}
	
	private boolean findTriples(List<Brick> remainBricks, Stack<Brick> winningCombo) {
		
		if (remainBricks.size() == 3) {
			Brick b1 = remainBricks.get(0);
			Brick b2 = remainBricks.get(1);
			Brick b3 = remainBricks.get(2);
			boolean ret = isTriple(b1, b2, b3);
			if (ret) {
				winningCombo.push(b1);
				winningCombo.push(b2);
				winningCombo.push(b3);
			}
			return ret;
		}
		
		boolean ret = false;
		Set<Set<Integer>> indexSets = SetUtil.getSubsetIndex(remainBricks.size(), 3);
		for (Set<Integer> indexSet : indexSets) {
			List<Integer> indexList = new ArrayList<Integer>(indexSet);
			Brick b1 = remainBricks.get(indexList.get(0));
			Brick b2 = remainBricks.get(indexList.get(1));
			Brick b3 = remainBricks.get(indexList.get(2));
			if (isTriple(b1, b2, b3)) {
				winningCombo.push(b1);
				winningCombo.push(b2);
				winningCombo.push(b3);
				List<Brick> remainBricks2 = new ArrayList<Brick>(remainBricks);
				remainBricks2.remove(b1);
				remainBricks2.remove(b2);
				remainBricks2.remove(b3);
				ret = findTriples(remainBricks2, winningCombo);
				if (ret) {
					break;
				} else {
					winningCombo.pop();
					winningCombo.pop();
					winningCombo.pop();
				}
			}
		}

		return ret;
	}
	
	private boolean isTriple(Brick b1, Brick b2, Brick b3) {
		if (b1.equals(b2) && b2.equals(b3)) {
			return true;
		} else {
			return b1.isSequence(b2, b3);
		}
	}
}
