package org.galaxy.cardgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Blackjack {

	public static final int DEALER_LIMIT = 17;
	
	public static void main(String[] args) {
		simulate1();
	}
	
	//seems like 13 = 0.477
	// 2 => 12
	// 3 => 12
	// 4 => 12
	// 5 => 12
	// 6 => 12
	// 7 => 14
	// 8 => 17
	// 9 => 14
	// 10 => 15
	// 1 => 13
	// with above = 0.483
	private static int getStop(int dealer1) {
		switch (dealer1) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			return 12;
		case 7:
			return 14;
		case 8:
			return 17;
		case 9:
			return 14;
		case 10:
		case 11:
		case 12:
		case 13:
			return 15;
		case 1:
			return 13;
		default:
			return 13;
		}
	}
	
	private static void simulate1() {
		try {
			//for (int k = 11; k <= 18; k++) {
				int total = 0;
				int wins = 0;
				int loses = 0;
				for (int i = 0; i < 50000; i++) {
					int result = playOneAuto(1);
					if (result > 0) {
						wins++;
					} else if (result < 0) {
						loses++;
					}
				}
				total = wins + loses;
				double pct = 1.0 * wins / total;
				System.out.println(" win percent: " + pct);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int playOneAuto(int stop) {
		
		Deck deck = new Deck(true);
		deck.shuffle();
	
		List<Card> dealerCards = new ArrayList<Card>();
		List<Card> playerCards = new ArrayList<Card>();
		
		dealerCards.add(deck.removeTop());
		//dealerCards.add(deck.remove(8));
		dealerCards.add(deck.removeTop());
		
		stop = getStop(dealerCards.get(0).getRank());
		
		playerCards.add(deck.removeTop());
		playerCards.add(deck.removeTop());
		
		int playerTotal = getTotal(playerCards);
		while (playerTotal < stop) {
			playerCards.add(deck.removeTop());
			playerTotal = getTotal(playerCards);
		}
		if (playerTotal > 21) {
			return -1;
		}
		
		int dealerTotal = getTotal(dealerCards);
		while (dealerTotal < DEALER_LIMIT) {
			dealerCards.add(deck.removeTop());
			dealerTotal = getTotal(dealerCards);
		}
		if (dealerTotal > 21) {
			return 1;
		}
		
		if (isBlackjack(dealerCards) && !isBlackjack(playerCards)) {
			return -1;
		}
		
		if (!isBlackjack(dealerCards) && isBlackjack(playerCards)) {
			return 1;
		}
		
		return playerTotal - dealerTotal;
	}
	
	private static void playOneRound() throws Exception {
		
		Deck deck = new Deck(true);
		deck.shuffle();
	
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
	
	private static boolean isBlackjack(List<Card> cards) {
		return cards.size() == 2 && getTotal(cards) == 21;
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
