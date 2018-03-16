package org.galaxy.poker;

import java.util.ArrayList;
import java.util.List;

public class Fishing {

	public static void main(String[] args) {

		Deck deck = new Deck(true);

		List<Card> pool = new ArrayList<Card>();
		pool.add(deck.removeRandomCard());
		
		FishingPlayer player1 = new FishingPlayer("A", deck);
		FishingPlayer player2 = new FishingPlayer("B", deck);

		int turn = 1;
		do {
			if (turn % 2 == 1) {
				player1.play(pool, deck);
			} else {
				player2.play(pool, deck);
			}
			turn++;
		} while (player1.hasCards() || player2.hasCards());
		
		System.out.println("Game ended.");
		System.out.println("pool: " + pool.size());
		System.out.println(player1);
		System.out.println(player2);
	}	
}
