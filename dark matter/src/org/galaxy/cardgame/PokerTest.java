package org.galaxy.cardgame;

import java.util.ArrayList;
import java.util.List;

public class PokerTest {

	public static void main(String[] args) {
		
		Deck deck = new Deck(false);
		deck.shuffle();
		//System.out.println(deck);
		
		List<Card> hole = new ArrayList<Card>();
		hole.add(deck.removeRandomCard());
		hole.add(deck.removeRandomCard());
		System.out.println("hole: " + hole);
		
		List<Card> flop = new ArrayList<Card>();
		flop.add(deck.removeRandomCard());
		flop.add(deck.removeRandomCard());
		flop.add(deck.removeRandomCard());
		
		List<Card> tableCards = new ArrayList<Card>();
		tableCards.addAll(flop);
		System.out.println("table: " + tableCards);
		PokerHand best0 = PokerHand.getBestHand(hole, tableCards);
		System.out.println("best hand: " + best0);
		
		Card turn = deck.removeRandomCard();
		tableCards.add(turn);
		System.out.println("table: " + tableCards);
		PokerHand best1 = PokerHand.getBestHand(hole, tableCards);
		System.out.println("best hand: " + best1);
		
		Card river = deck.removeRandomCard();
		tableCards.add(river);
		System.out.println("table: " + tableCards);
		PokerHand best2 = PokerHand.getBestHand(hole, tableCards);
		System.out.println("best hand: " + best2);
	}
}
