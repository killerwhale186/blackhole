package org.galaxy.setgame;

import java.util.ArrayList;
import java.util.List;

public class Play {
	
	public static void main(String[] args) {
		
		Deck deck = new Deck();
		
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 12; i++) {
			cards.add(deck.removeRandomCard());
		}
		//System.out.println(cards);
		
		List<Card> triple = findTriple(cards);
		if (triple.size() == 3) {
			System.out.println("Triple found: ");
			System.out.println(triple.get(0));
			System.out.println(triple.get(1));
			System.out.println(triple.get(2));
		} else {
			System.out.println("Not found.");
		}
	}
	
	public static List<Card> findTriple(List<Card> cards) {
		List<Card> triples = new ArrayList<Card>();
		int s = cards.size();
		for (int i = 0; i < s; i++) {
			for (int j = i+1; j < s; j++) {
				for (int k = j+1; k < s; k++) {
					if (isTriple(cards.get(i), cards.get(j), cards.get(k))) {
						triples.add(cards.get(i));
						triples.add(cards.get(j));
						triples.add(cards.get(k));
						return triples;
					}
				}
			}
		}
		return triples;
	}
	
	public static boolean isTriple(Card a, Card b, Card c) {
		return 
			((a.getNumber() == b.getNumber() && b.getNumber() == c.getNumber()) || 
			 (a.getNumber() != b.getNumber() && a.getNumber() != c.getNumber() && b.getNumber() != c.getNumber()))
			&&
			((a.getColor() == b.getColor() && b.getColor() == c.getColor()) || 
			 (a.getColor() != b.getColor() && a.getColor() != c.getColor() && b.getColor() != c.getColor()))
			&&
			((a.getShape() == b.getShape() && b.getShape() == c.getShape()) || 
			 (a.getShape() != b.getShape() && a.getShape() != c.getShape() && b.getShape() != c.getShape()))
			&&
			((a.getShade() == b.getShade() && b.getShade() == c.getShade()) || 
			 (a.getShade() != b.getShade() && a.getShade() != c.getShade() && b.getShade() != c.getShade()));
	}
}
