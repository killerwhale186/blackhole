import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		addCards('S');
		addCards('H');
		addCards('D');
		addCards('C');
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public Card removeRandomCard() {
		int index = (int) (Math.random() * this.cards.size());
		return this.cards.remove(index);
	}
	
	private void addCards(char suit) {
		for (int i = 2; i <= 14; i++) {
			this.cards.add(new Card(suit, i));
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
