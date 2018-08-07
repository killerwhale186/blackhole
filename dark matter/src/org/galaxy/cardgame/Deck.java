package org.galaxy.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private boolean aceIsOne = true;
	private List<Card> cards;
	
	public Deck(boolean aceIsOne) {
		this.aceIsOne = aceIsOne;
		this.cards = new ArrayList<Card>();
		addCards('S');
		addCards('H');
		addCards('D');
		addCards('C');
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public boolean hasCards() {
		return this.cards.size() > 0;
	}
	
	public Card removeTop() {
		return this.cards.remove(0);
	}
	
	public Card removeRandomCard() {
		int index = (int) (Math.random() * this.cards.size());
		return this.cards.remove(index);
	}
	
	private void addCards(char suit) {
		for (int i = 2; i <= 13; i++) {
			this.cards.add(new Card(suit, i));
		}
		if (this.aceIsOne) {
			this.cards.add(new Card(suit, 1));
		} else {
			this.cards.add(new Card(suit, 14));
		}
	}
	
	public void shuffle() {
		int run = 300;
		while (run > 0) {
			int index = (int) (Math.random() * this.cards.size());
			Card hold = this.cards.remove(index);
			this.cards.add(hold);
			run--;
		}
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Card card : this.cards) {
			buf.append(card.toString()).append(" ");
		}
		return buf.toString();
	}
}
