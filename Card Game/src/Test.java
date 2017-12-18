import java.util.ArrayList;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		deck.shuffle();
		System.out.println(deck);
		
		List<Card> cards = new ArrayList<Card>();
		cards.add(deck.removeRandomCard());
		cards.add(deck.removeRandomCard());
		cards.add(deck.removeRandomCard());
		cards.add(deck.removeRandomCard());
		cards.add(deck.removeRandomCard());
		
		PokerHand hand = new PokerHand(cards);
		System.out.println(hand);
	}
}
