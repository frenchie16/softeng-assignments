package cs3733.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class HandClassification implements Comparable<HandClassification>{
	
	private class RankCountMap extends EnumMap<CardRank, Integer> {

		private static final long serialVersionUID = 1L;

		private RankCountMap(SortedSet<Card> cardSet) {
			super(CardRank.class);
			for(Card c : cardSet){
				Integer currentCount = get(c.getRank());
				//If that rank wasn't already in the map then its count so far is 0
				if(currentCount == null){
					currentCount = 0;
				}
				put(c.getRank(), currentCount + 1);
			}
		}

		/**
		 * 
		 * @return A list of ranks sorted first by the count of that rank and then by the rank itself
		 */
		private List<CardRank> getRanksByCount() {
			List<CardRank> rankList = new ArrayList<CardRank>();
			
			Comparator<Map.Entry<CardRank, Integer>> cardRankComparator =
					new Comparator<Map.Entry<CardRank, Integer>>(){

						@Override
						public int compare(
								Map.Entry<CardRank, Integer> a,
								Map.Entry<CardRank, Integer> b) {
							int countComparison = a.getValue().compareTo(b.getValue());
							if(countComparison == 0){
								//negative because higher number cards beat (come before) lower numbers
								return -a.getKey().compareTo(b.getKey());
							} else {
								//negative because more cards beat (come before) fewer cards
								return -countComparison;
							}
						}
				
			};

			
			SortedSet<Map.Entry<CardRank, Integer>> kvPairs =
					new TreeSet<Map.Entry<CardRank, Integer>>(cardRankComparator);
			kvPairs.addAll(entrySet());
			
			for(Map.Entry<CardRank, Integer> entry : kvPairs){
				rankList.add(entry.getKey());
			}
			
			return rankList;
		}

		/**
		 * 
		 * @param count The count to check for
		 * @return True if there are exactly [count] of any single rank
		 */
		private boolean hasRankWithCount(int count) {
			return hasNRanksWithCount(1, count);
		}

		/**
		 * 
		 * @param n The number of ranks with the count
		 * @param count The count to check for
		 * @return True if there are exactly [count] cards of each of [n] different ranks
		 */
		private boolean hasNRanksWithCount(int n, int count) {
			int foundSoFar = 0;
			for(Map.Entry<CardRank, Integer> entry : entrySet()){
				if(entry.getValue() == count){
					foundSoFar++;
					if(foundSoFar > n){
						return false;
					}
				}
			}
			return foundSoFar == n;
		}
		
	}
	
	private static enum HandType {
		HIGH_CARD,
		PAIR,
		TWO_PAIR,
		THREE_OF_KIND,
		STRAIGHT,
		FLUSH,
		FULL_HOUSE,
		FOUR_OF_KIND,
		STRAIGHT_FLUSH
	};
	
	private final HandType type;
	
	//list of ranks used for tie-breaking. Most things will have only one rank, but pairs will have 2 and two-pairs will have 3.
	private final List<CardRank> ranks;
	
	public HandClassification(Card[] cards){
		Comparator<Card> cardComparator = new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				int rankComparison = o1.getRank().compareTo(o2.getRank());
				if(rankComparison == 0){
					return o1.getSuit().compareTo(o2.getSuit());
				} else {
					return rankComparison;
				}
			}
		};
		SortedSet<Card> cardSet = new TreeSet<>(cardComparator);
		cardSet.addAll(Arrays.asList(cards));
		
		boolean straight = isStraight(cardSet);
		boolean flush = isFlush(cardSet);
		RankCountMap countByRank = new RankCountMap(cardSet);
		
		//Ace Special Cace
		if(straight){
			this.ranks = getStraightRank(cardSet);
		} else {
			this.ranks = countByRank.getRanksByCount();
		}
		
		if(straight && flush){
			this.type = HandType.STRAIGHT_FLUSH;
		} else if(countByRank.hasRankWithCount(4)){
			this.type = HandType.FOUR_OF_KIND;
		} else if(countByRank.hasRankWithCount(3) && countByRank.hasRankWithCount(2)){
			this.type = HandType.FULL_HOUSE;
		} else if(flush){
			this.type = HandType.FLUSH;
		} else if(straight){
			this.type = HandType.STRAIGHT;
		} else if(countByRank.hasRankWithCount(3)){
			this.type = HandType.THREE_OF_KIND;
		} else if(countByRank.hasNRanksWithCount(2, 2)){
			this.type = HandType.TWO_PAIR;
		} else if(countByRank.hasRankWithCount(2)){
			this.type = HandType.PAIR;
		} else {
			this.type = HandType.HIGH_CARD;
		}
	}
	
	private List<CardRank> getStraightRank(SortedSet<Card> cards) {
		Iterator<Card> iterator = cards.iterator();
		Card first = iterator.next();
		//Check if the ace is a 1 or a 13
		if(first.getRank()==CardRank.ACE||cards.last().getRank()==CardRank.TWO){
			first = iterator.next();
		}
		List<CardRank> ranks = new ArrayList<>();
		ranks.add(first.getRank());
		return ranks;
	}

	/**
	 * 
	 * @param cardSet The cards in the hand
	 * @return True if all cards are the same suit, else false
	 */
	private boolean isFlush(SortedSet<Card> cardSet) {
		CardSuit suit = null;
		for(Card c : cardSet){
			if(suit == null){
				suit = c.getSuit();
			} else if(c.getSuit() != suit){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param cards The cards in the hand
	 * @return True if all cards are in order
	 */
	private boolean isStraight(SortedSet<Card> cards) {
		if(cards.size()!=5){
			//If there are any repeated cards it is not a straight
			return false;
		}
		
		//ACE SPECIAL CACE

		boolean aceSpecialCace = cards.first().getRank().equals(CardRank.TWO)&&cards.last().getRank().equals(CardRank.ACE);
		Iterator<Card> iterator = cards.iterator();
		Card currentCard = iterator.next();
		for(int i = 0; i<(cards.size()-(aceSpecialCace?2:1)); i++){
			Card nextCard = iterator.next();
			//current == next-1 in a straight
			if(currentCard.getRank().getValue() != nextCard.getRank().getValue()-1){
				return false;
			}
			currentCard = nextCard;
		}
		return true;
	}

	protected HandType getType(){
		return this.type;
	}
	
	protected List<CardRank> getRanks(){
		return ranks;
	}
	
	@Override
	public int compareTo(HandClassification o) {
		int typeComparison = type.compareTo(o.getType());
		if(typeComparison == 0){
			//Loop over both card ranks and compare until resolved
			Iterator<CardRank> myRankIterator = ranks.iterator();
			Iterator<CardRank> otherRankIterator = o.getRanks().iterator();
			while(myRankIterator.hasNext() && otherRankIterator.hasNext()){
				int rankComparison = myRankIterator.next().compareTo(otherRankIterator.next());
				if(rankComparison != 0) return rankComparison;
			}
			return 0;
		} else {
			return typeComparison;
		}
	}
	
}
