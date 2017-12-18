import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		addCards('S');
		addCards('H');
		addCards('D');
		addCards('C');
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public Card removeRandomCard() {
		int index = (int) (Math.random() * cards.size());
		return cards.remove(index);
	}
	
	private void addCards(char suit) {
		for (int i = 2; i <= 14; i++) {
			cards.add(new Card(suit, i));
		}
	}
	
	public void shuffle() {
		int run = 200;
		while (run > 0) {
			int index = (int) (Math.random() * cards.size());
			Card hold = cards.remove(index);
			cards.add(hold);
			run--;
		}
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Card card : cards) {
			buf.append(card.toString()).append(" ");
		}
		return buf.toString();
	}
}
