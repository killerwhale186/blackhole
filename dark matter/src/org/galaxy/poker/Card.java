package org.galaxy.poker;

/**
 * 
 * This class represents a card
 */
public class Card {

	private char suit; //S, H, D, C
	private int rank;
	
	public Card(char s, int r) {
		//validate rank between 2 and 14, suit is S, H, D or C
		this.suit = s;
		this.rank = r;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public char getSuit() {
		return this.suit;
	}
	
	public int compareTo(Card c)
	{
		return (this.rank - c.rank);
	}
	
	public String toString() {
		return String.valueOf(this.suit) + getDisplayStr();
	}
	
	public String getDisplayStr() {
		if (this.rank == 11) {
			return "J";
		} else if (this.rank == 12) {
			return "Q";
		} else if (this.rank == 13) {
			return "K";
		} else if (this.rank == 14) {
			return "A";
		} else {
			return String.valueOf(this.rank);
		}
	}
}
