package org.galaxy.cardgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Blackjack {

	public static void main(String[] args) {
		
		Deck deck = new Deck(true);
		deck.shuffle();
	
		try {
			playOneRound(deck);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void playOneRound(Deck deck) throws Exception {
		List<Card> dealerCards = new ArrayList<Card>();
		List<Card> playerCards = new ArrayList<Card>();
		
		dealerCards.add(deck.removeTop());
		dealerCards.add(deck.removeTop());
		
		System.out.println("Dealer open card: " + dealerCards.get(0));
		
		System.out.println("Your cards: ");
		playerCards.add(deck.removeTop());
		playerCards.add(deck.removeTop());
		for (Card card : playerCards) {
			System.out.println(card);
		}
		
		int playerTotal = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("More card (y|n) ?");
			String response = in.readLine();
			if (response.equalsIgnoreCase("y")) {
				Card c = deck.removeTop();
				playerCards.add(c);
				System.out.println("Here is your new card: " + c);
				playerTotal = getTotal(playerCards);
				System.out.println("Your total is: " + playerTotal);
				if (playerTotal > 21) {
					System.out.println("You are busted.");
					return;
				}
			} else {
				break;
			}
		}
		
		int dealerTotal = getTotal(dealerCards);
		while (dealerTotal < 17) {
			dealerCards.add(deck.removeTop());
			dealerTotal = getTotal(dealerCards);
		}
		
		System.out.println("Dealer cards: " + dealerCards);
		System.out.println("dealerTotal: " + dealerTotal);
		if (dealerTotal > 21) {
			System.out.println("Dealer busted. You win.");
		} else {
			if (dealerTotal > playerTotal) {
				System.out.println("Dealer win.");
			} else if (dealerTotal < playerTotal) {
				System.out.println("You win.");
			} else {
				System.out.println("Tie.");
			}
		}
	}
	
	private static int getTotal(List<Card> cards) {
		int total = 0;
		for (Card c : cards) {
			if (c.getRank() == 11 || c.getRank() == 12 || c.getRank() == 13) {
				total += 10;
			} else if (c.getRank() == 1 || c.getRank() == 14) {
				total += 11;
			} else {
				total += c.getRank();
			}
		}
		return total;
	}
}