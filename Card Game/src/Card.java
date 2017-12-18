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
	
	public int compareTo(Card o)
	{
	     return (rank - o.rank);
	}
	
	public String toString() {
		return String.valueOf(suit) + getDisplayStr();
	}
	
	public String getDisplayStr() {
		if (rank == 11) {
			return "J";
		} else if (rank == 12) {
			return "Q";
		} else if (rank == 13) {
			return "K";
		} else if (rank == 14) {
			return "A";
		} else {
			return String.valueOf(rank);
		}
	}
}
