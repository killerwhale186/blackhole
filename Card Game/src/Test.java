import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Test {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		deck.shuffle();
		//System.out.println(deck);
		
		List<Card> hole = new ArrayList<Card>();
		hole.add(deck.removeRandomCard());
		hole.add(deck.removeRandomCard());
		System.out.println(hole);
		
		List<Card> flop = new ArrayList<Card>();
		flop.add(deck.removeRandomCard());
		flop.add(deck.removeRandomCard());
		flop.add(deck.removeRandomCard());
		
		List<Card> tableCards = new ArrayList<Card>();
		tableCards.addAll(flop);
		System.out.println(tableCards);
		PokerHand best0 = getBestHand(hole, tableCards);
		System.out.println(best0);
		
		Card turn = deck.removeRandomCard();
		tableCards.add(turn);
		System.out.println(tableCards);
		PokerHand best1 = getBestHand(hole, tableCards);
		System.out.println(best1);
		
		Card river = deck.removeRandomCard();
		tableCards.add(river);
		System.out.println(tableCards);
		PokerHand best2 = getBestHand(hole, tableCards);
		System.out.println(best2);
	}
	
	public static PokerHand getBestHand(List<Card> hole, List<Card> tableCards) {
		PokerHand hand = null;
		Set<Set<Integer>> subSetIndex = SetUtil.getSubsetIndex(tableCards.size(), 3);
		for (Set<Integer> subIndex : subSetIndex) {
			List<Card> temp = new ArrayList<Card>();
			temp.addAll(hole);
			for (Integer i : subIndex) {
				temp.add(tableCards.get(i));
			}
			PokerHand tempHand = new PokerHand(temp);
			if (hand == null || tempHand.compareTo(hand) > 0) {
				hand = tempHand;
			}
		}
		return hand;
	}
}
