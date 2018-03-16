package org.galaxy.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.galaxy.util.SetUtil;

public class FishingPlayer {

	private String name;
	private List<Card> hand;
	private List<Card> fish;
	
	public FishingPlayer(String name, Deck deck) {
		this.name = name;
		this.hand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			this.hand.add(deck.removeRandomCard());
		}
		this.fish = new ArrayList<Card>();
	}
	
	public boolean hasCards() {
		return this.hand.size() > 0;
	}
	
	public void play(List<Card> pool, Deck deck) {
		
		System.out.println("player: " + this.name);
		System.out.println("pool: " + pool);
		System.out.println("hand: " + hand);
		
		List<Card> currFish = getFish(pool, hand);
		this.fish.addAll(currFish);
		
		if (this.hand.size() > 0) {
			//todo: throw away largest one
			pool.add(this.hand.get(0));
			this.hand.remove(0);
		}
		
		while (deck.hasCards() && this.hand.size() < 5) {
			this.hand.add(deck.removeRandomCard());
		}
		
		System.out.println("fish: " + fish);
		System.out.println();
	}
	
	private static List<Card> getFish(List<Card> pool, List<Card> hand) {
		List<Card> ret = new ArrayList<Card>();
		for (Card card : pool) {
			List<Card> cards = findCardsTotal(hand, 14 - card.getRank());
			if (cards.size() > 0) {
				pool.remove(card);
				ret.addAll(cards);
				ret.add(card);
				break;
			}
		}
		return ret;
	}
	
	//find list of cards, whose total value equals total
	private static List<Card> findCardsTotal(List<Card> cards, int total) {
		List<Card> ret = new ArrayList<Card>();
		for (int k = cards.size(); k >= 1; k--) {
			Set<Set<Integer>> subsets = SetUtil.getSubsetIndex(cards.size(), k);
			for (Set<Integer> subset: subsets) {
				int sum = 0;
				for (Integer i : subset) {
					sum += cards.get(i).getRank();
				}
				if (sum == total) {
					for (Integer i : subset) {
						ret.add(cards.get(i));
					}
					for (Card card : ret) {
						cards.remove(card);
					}
					return ret;
				}
			}
		}
		return ret;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.name + " fish count:" + this.fish.size());
		return buf.toString();
	}
}
