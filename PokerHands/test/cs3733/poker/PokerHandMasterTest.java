/*******************************************************************************
 * Copyright (c) 2013 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Used in CS3733, Software Engineering at Worcester Polytechnic Institute
 *******************************************************************************/

package cs3733.poker;

import static cs3733.poker.CardRank.*;
import static cs3733.poker.CardSuit.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for the poker hand, specifically the PokerHandImpl class.
 * @version Oct 27, 2013
 */
public class PokerHandMasterTest
{
	// The cards in the deck
	static public final Card
	    twoClub = new Card(TWO, CLUB),
	    threeClub = new Card(THREE, CLUB),
	    fourClub = new Card(FOUR, CLUB),
	    fiveClub = new Card(FIVE, CLUB),
	    sixClub = new Card(SIX, CLUB),
	    sevenClub = new Card(SEVEN, CLUB),
	    eightClub = new Card(EIGHT, CLUB),
	    nineClub = new Card(NINE, CLUB),
	    tenClub = new Card(TEN, CLUB),
	    jackClub = new Card(JACK, CLUB),
	    queenClub = new Card(QUEEN, CLUB),
	    kingClub = new Card(KING, CLUB),
	    aceClub = new Card(ACE, CLUB),
	    twoDiamond = new Card(TWO, DIAMOND),
	    threeDiamond = new Card(THREE, DIAMOND),
	    fourDiamond = new Card(FOUR, DIAMOND),
	    fiveDiamond = new Card(FIVE, DIAMOND),
	    sixDiamond = new Card(SIX, DIAMOND),
	    sevenDiamond = new Card(SEVEN, DIAMOND),
	    eightDiamond = new Card(EIGHT, DIAMOND),
	    nineDiamond = new Card(NINE, DIAMOND),
	    tenDiamond = new Card(TEN, DIAMOND),
	    jackDiamond = new Card(JACK, DIAMOND),
	    queenDiamond = new Card(QUEEN, DIAMOND),
	    kingDiamond = new Card(KING, DIAMOND),
	    aceDiamond = new Card(ACE, DIAMOND),
	    twoHeart = new Card(TWO, HEART),
	    threeHeart = new Card(THREE, HEART),
	    fourHeart = new Card(FOUR, HEART),
	    fiveHeart = new Card(FIVE, HEART),
	    sixHeart = new Card(SIX, HEART),
	    sevenHeart = new Card(SEVEN, HEART),
	    eightHeart = new Card(EIGHT, HEART),
	    nineHeart = new Card(NINE, HEART),
	    tenHeart = new Card(TEN, HEART),
	    jackHeart = new Card(JACK, HEART),
	    queenHeart = new Card(QUEEN, HEART),
	    kingHeart = new Card(KING, HEART),
	    aceHeart = new Card(ACE, HEART),
	    twoSpade = new Card(TWO, SPADE),
	    threeSpade = new Card(THREE, SPADE),
	    fourSpade = new Card(FOUR, SPADE),
	    fiveSpade = new Card(FIVE, SPADE),
	    sixSpade = new Card(SIX, SPADE),
	    sevenSpade = new Card(SEVEN, SPADE),
	    eightSpade = new Card(EIGHT, SPADE),
	    nineSpade = new Card(NINE, SPADE),
	    tenSpade = new Card(TEN, SPADE),
	    jackSpade = new Card(JACK, SPADE),
	    queenSpade = new Card(QUEEN, SPADE),
	    kingSpade = new Card(KING, SPADE),
	    aceSpade = new Card(ACE, SPADE);
	
	@Test
	public void getCardsReturnsAllCards()
	{
		final Card[] cards = {
				twoHeart, threeSpade, fourDiamond, fiveClub, aceSpade
		};

		final PokerHand hand = new PokerHandImpl(cards);
		final Card[] handCards = hand.getCards();
		assertTrue(hasCard(handCards, twoHeart));
	}

	// Straight flush tests
	@Test
	public void straightFlushWithHighestCardBeatsOtherStraightFlush()
	{
		final Card[] hand1Cards = {
				tenDiamond, jackDiamond, queenDiamond, kingDiamond, aceDiamond
		};
		final Card[] hand2Cards = {
				twoClub, threeClub, fourClub, fiveClub, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	@Test
	public void straightFlushWithLowestCardDoesNotBeatOtherStraightFlush()
	{
		final Card[] hand1Cards = {
				tenDiamond, jackDiamond, queenDiamond, kingDiamond, aceDiamond
		};
		final Card[] hand2Cards = {
				twoClub, threeClub, fourClub, fiveClub, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	@Test
	public void straightFlushWithSameHighCardDoesNotBeatOtherStraightFlush()
	{
		final Card[] hand1Cards = {
				twoDiamond, threeDiamond, fourDiamond, fiveDiamond, sixDiamond
		};
		final Card[] hand2Cards = {
				twoClub, threeClub, fourClub, fiveClub, sixClub
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}

	@Test
	public void royalFlushBeatsLowestStraightFlush()
	{
		final Card[] hand1Cards = {
				tenDiamond, jackDiamond, queenDiamond, kingDiamond, aceDiamond
		};
		final Card[] hand2Cards = {
				twoClub, threeClub, fourClub, fiveClub, aceClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	@Test
	public void straightFlushBeatsFourOfAKind()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				queenDiamond, twoSpade, twoClub, twoHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	@Test
	public void straightFlushBeatsFullHouse()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				queenDiamond, queenSpade, queenClub, twoHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightFlushBeatsFlush()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				queenDiamond, fourDiamond, sixDiamond, kingDiamond, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightFlushBeatsStraight()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightFlushBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, twoClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightFlushBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightFlushBeatsPair()
	{
		final Card[] hand1Cards = {
				sevenClub, eightClub, nineClub, tenClub, jackClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	// Four-of-a-kind tests
	@Test
	public void fourOfAKindWithHighCardBeatsFourOfAKind()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				queenDiamond, twoSpade, twoClub, twoHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}

	@Test
	public void fourOfAKindBeatsFullHouse()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				queenDiamond, queenSpade, queenClub, twoHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fourOfAKindBeatsFlush()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				queenDiamond, fourDiamond, sixDiamond, kingDiamond, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fourOfAKindBeatsStraight()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fourOfAKindBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, twoClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fourOfAKindBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fourOfAKindBeatsPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, threeHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Full house tests
	@Test
	public void fullHouseWithHighTripleBeatsFullHouseWithLowerTriple()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, twoHeart, twoDiamond
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseWithEqualTripleButHighPair()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, threeHeart, threeDiamond
		};
		final Card[] hand2Cards = {
				queenDiamond, queenSpade, queenClub, twoHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseBeatsFlush()
	{
		final Card[] hand1Cards = {
				queenHeart, queenSpade, queenClub, twoHeart, twoSpade
		};
		final Card[] hand2Cards = {
				queenDiamond, fourDiamond, sixDiamond, kingDiamond, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseBeatsStraight()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, twoHeart, twoDiamond
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, threeHeart, threeDiamond
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, twoClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, threeHeart, threeDiamond
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void fullHouseBeatsPair()
	{
		final Card[] hand1Cards = {
				queenDiamond, queenSpade, queenClub, threeHeart, threeDiamond
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Flush tests
	@Test
	public void flushWithHighCardBeatsOtherFlush()
	{
		final Card[] hand1Cards = {
				kingClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				queenDiamond, fourDiamond, sixDiamond, twoDiamond, tenDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void identicalFlusheRanksHaveNoWinner()
	{
		final Card[] hand1Cards = {
				queenClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				queenDiamond, fourDiamond, sixDiamond, twoDiamond, tenDiamond
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void flushBeatsStraight()
	{
		final Card[] hand1Cards = {
				queenClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void flushBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				queenClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, twoHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void flushBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				queenClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourSpade
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void flushBeatsPair()
	{
		final Card[] hand1Cards = {
				queenClub, twoClub, fourClub, sixClub, tenClub
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Straight tests
	@Test
	public void straightWithHighCardBeatsOtherStraight()
	{
		final Card[] hand1Cards = {
				threeClub, fourSpade, fiveHeart, sixDiamond, sevenSpade
		};
		final Card[] hand2Cards = {
				twoDiamond, threeSpade, fourHeart, fiveDiamond, sixHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void identicalStraightRanksHaveNoWinner()
	{
		final Card[] hand1Cards = {
				threeClub, fourSpade, fiveHeart, sixDiamond, sevenSpade
		};
		final Card[] hand2Cards = {
				threeSpade, fourHeart, fiveDiamond, sixHeart, sevenDiamond
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				threeClub, fourSpade, fiveHeart, sixDiamond, sevenSpade
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, twoHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void straightBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				threeClub, fourSpade, fiveHeart, sixDiamond, sevenSpade
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	public void straightBeatsPair()
	{
		final Card[] hand1Cards = {
				threeClub, fourSpade, fiveHeart, sixDiamond, sevenSpade
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Three-of-a-kind tests
	@Test
	public void threeOfAKindWithHighCardBeatsThreeOfAKind()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, kingHeart
		};
		final Card[] hand2Cards = {
				queenDiamond, twoSpade, twoClub, tenHeart, twoDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void threeOfAKindBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, kingHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void threeOfAKindBeatsPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, threeDiamond, threeSpade, kingHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Two pair tests
	@Test
	public void twoPairWithHighPairBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, sevenDiamond, kingSpade, kingHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fourClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void twoPairEqualHighPairHighLowPairBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, threeClub, sevenDiamond, kingSpade, kingHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, kingClub, jackDiamond, kingDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void twoEqualPairWithHighCardBeatsTwoPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, aceClub, sevenDiamond, kingSpade, kingHeart
		};
		final Card[] hand2Cards = {
				sevenClub, sevenSpade, kingClub, jackDiamond, kingDiamond
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void equalTwoPairsHaveNoWinner()
	{
		final Card[] hand1Cards = {
				sevenHeart, jackClub, sevenDiamond, kingSpade, kingHeart
		};
		final Card[] hand2Cards = {
				sevenClub, sevenSpade, kingClub, jackDiamond, kingDiamond
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void twoPairBeatsPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, jackClub, sevenDiamond, kingSpade, kingHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	// Pair tests
	@Test
	public void highPairBeatsLowPair()
	{
		final Card[] hand1Cards = {
				sevenHeart, jackClub, sevenDiamond, kingSpade, queenHeart
		};
		final Card[] hand2Cards = {
				twoDiamond, twoSpade, fourHeart, jackDiamond, fiveClub
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void equalPairHighCardWins()
	{
		final Card[] hand1Cards = {
				twoDiamond, twoSpade, fourHeart, fiveClub, queenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, twoClub, fourDiamond, fiveSpade, jackHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void equalPairEqualHighCardHighMiddleCardWins()
	{
		final Card[] hand1Cards = {
				twoDiamond, twoSpade, fourHeart, sixClub, queenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, twoClub, fourDiamond, fiveSpade, queenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void equalPairEqualHighAndMiddleCardHighLowCardWins()
	{
		final Card[] hand1Cards = {
				twoDiamond, twoSpade, fourHeart, fiveClub, queenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, twoClub, threeDiamond, fiveSpade, queenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void equalPairHandsHaveNoWinner()
	{
		final Card[] hand1Cards = {
				twoDiamond, twoSpade, fourHeart, fiveClub, queenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, twoClub, fourDiamond, fiveSpade, queenHeart
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}
	
	// Mixed hand test
	@Test
	public void mixedHandsHighCardDiffers()
	{
		final Card[] hand1Cards = {
				twoDiamond, fourSpade, sixHeart, eightClub, jackDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void mixedHandSecondHighCardDiffers()
	{
		final Card[] hand1Cards = {
				twoDiamond, fourSpade, sixHeart, nineClub, tenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void mixedHandThirdHighCardDiffers()
	{
		final Card[] hand1Cards = {
				twoDiamond, fourSpade, sevenHeart, eightClub, tenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void mixedHandFourthHighCardDiffers()
	{
		final Card[] hand1Cards = {
				twoDiamond, fiveSpade, sixHeart, eightClub, tenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void mixedHandLowestCardDiffers()
	{
		final Card[] hand1Cards = {
				threeDiamond, fourSpade, sixHeart, eightClub, tenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runTwoHandTest(hand1Cards, hand2Cards);
	}
	
	@Test
	public void identicalMixedHandsHasNoWinner()
	{
		final Card[] hand1Cards = {
				twoDiamond, fourSpade, sixHeart, eightClub, tenDiamond
		};
		final Card[] hand2Cards = {
				twoHeart, fourClub, sixDiamond, eightSpade, tenHeart
		};
		runNoWinnerTest(hand1Cards, hand2Cards);
	}

	// Helper methods
	private boolean hasCard(Card[] cards, Card card)
	{
		for (Card c : cards) {
			if (c == card) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param hand1Cards
	 * @param hand2Cards
	 */
	private void runTwoHandTest(final Card[] hand1Cards, final Card[] hand2Cards)
	{
		final PokerHand hand1 = new PokerHandImpl(hand1Cards);
		final PokerHand hand2 = new PokerHandImpl(hand2Cards);
		assertTrue(hand1.beats(hand2));
		assertFalse(hand2.beats(hand1));
	}

	/**
	 * @param hand1Cards
	 * @param hand2Cards
	 */
	private void runNoWinnerTest(final Card[] hand1Cards, final Card[] hand2Cards)
	{
		final PokerHand hand1 = new PokerHandImpl(hand1Cards);
		final PokerHand hand2 = new PokerHandImpl(hand2Cards);
		assertFalse(hand2.beats(hand1));
		assertFalse(hand1.beats(hand2));
	}
}