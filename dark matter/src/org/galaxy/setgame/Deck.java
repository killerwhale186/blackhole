package org.galaxy.setgame;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		for (NumberType number : NumberType.values()) {
			for (ColorType color: ColorType.values()) {
				for (ShapeType shape: ShapeType.values()) {
					for (ShadeType shade: ShadeType.values()) {
						cards.add(new Card(number, color, shape, shade));
					}
				}
			}
		}
	}
	
	public List<Card> getCards() {
		return cards;
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
	
	public Card removeRandomCard() {
		int index = (int) (Math.random() * this.cards.size());
		return this.cards.remove(index);
	}
}
