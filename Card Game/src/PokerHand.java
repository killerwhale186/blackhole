import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PokerHand implements Comparable<PokerHand> {
	
	private List<Card> cards; //five cards
	private List<Integer> subRank; //this list of values is used to determine rank if two hands have same type
	private HandRank handRank;
	
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
	
	public PokerHand(List<Card> cards) {
		this.cards = cards;
		this.subRank = new ArrayList<Integer>();
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
			this.subRank.add((int)(i * Math.pow(10, cnt - 1)));
		}
		Collections.sort(this.subRank);
		
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
		for (int i = 1; i < this.cards.size(); i++) {
			if (this.cards.get(i).getSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	private boolean isStraight() {
		for (int i = 0; i < this.cards.size() - 1; i++) {
			if (this.cards.get(i).getRank() != this.cards.get(i+1).getRank() + 1) {
				return false;
			}
		}
		return true;
	}
	
	//count duplicates for each rank, like if there are three 5, two 9, we have 5 -> 3, 9 -> 2
	private Map<Integer, Integer> groupByRanks() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Card card : this.cards) {
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
		return this.cards.toString() + " : " + getHandRank() + " : " + this.subRank;
	}

	public int compareTo(PokerHand that) {
		int rankDiff = that.getHandRank().ordinal() - this.getHandRank().ordinal();
		if (rankDiff != 0) {
			return rankDiff;
		} else {
			for (int i = this.subRank.size() - 1; i >= 0; i--) {
				int subDiff = this.subRank.get(i) - that.subRank.get(i);
				if (subDiff != 0) {
					return subDiff;
				}
			}
		}
		return 0;
	}
}
