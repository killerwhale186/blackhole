import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PokerHand {
	
	private List<Card> cards; //five cards
	private HandRank handRank;
	
	public PokerHand(List<Card> cards) {
		this.cards = cards;
		Collections.sort(cards, new Comparator<Card>() {
			public int compare(Card card1, Card card2) {
				if (card1.getRank() == card2.getRank())
					return 0;
				return card1.getRank() < card2.getRank() ? 1 : -1; //descend
			}
		});
		identifyHandRank();
	}
	
	public HandRank getHandRank() {
		return this.handRank;
	}
	
	public void identifyHandRank() {
		boolean isFlush = isFlush();
		boolean isStraight = isStraight();
		
		int pairs = 0;
		int triples = 0;
		int quadruples = 0;
		Map<Integer, Integer> map = groupByRanks();
		for (Integer i : map.keySet()) {
			int cnt = map.get(i);
			if (cnt == 2) {
				pairs++;
			} else if (cnt == 3) {
				triples++;
			} else if (cnt == 4) {
				quadruples++;
			}
		}
		
		if (quadruples == 1) {
			this.handRank = HandRank.FOUR_OF_A_KIND;
		} else if (triples == 1) {
			if (pairs == 1) {
				this.handRank = HandRank.FULL_HOUSE;
			} else {
				this.handRank = HandRank.THREE_OF_A_KIND;
			}
		} else if (pairs == 2) {
			this.handRank = HandRank.TWO_PAIRS;
		} else if (pairs == 1) {
			this.handRank = HandRank.PAIR;
		} else if (isFlush) {
			if (isStraight) {
				if (this.cards.get(0).getRank() == 14) {
					this.handRank = HandRank.ROYAL_FLUSH;
				} else {
					this.handRank = HandRank.STRAIGHT_FLUSH;
				}
			} else {
				this.handRank = HandRank.FLUSH;
			}
		} else if (isStraight) {
			this.handRank = HandRank.STRAIGHT;
		} else {
			this.handRank = HandRank.HIGH_CARD;
		}
	}
	
	private boolean isFlush() {
		char suit = this.cards.get(0).getSuit();
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).getSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	private boolean isStraight() {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() != cards.get(i+1).getRank() + 1) {
				return false;
			}
		}
		return true;
	}
	
	private Map<Integer, Integer> groupByRanks() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Card card : cards) {
			int rank = card.getRank();
			if (map.containsKey(rank)) {
				map.put(rank, map.get(rank) + 1);
			} else {
				map.put(rank, 1);
			}
		}
		return map;
	}
	
	public String toString() {
		return cards.toString() + " : " + getHandRank();
	}
}
