package cs3733.poker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokerHandImplTest {

	private Card[] straightFlushCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.KING, CardSuit.DIAMOND),
			new Card(CardRank.QUEEN, CardSuit.DIAMOND),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.TEN, CardSuit.DIAMOND)
	};
	
	private Card[] worseStraightFlushCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.EIGHT, CardSuit.HEART),
			new Card(CardRank.QUEEN, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.TEN, CardSuit.HEART)
	};
	
	private Card[] fourOfAKindCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.NINE, CardSuit.SPADE),
			new Card(CardRank.NINE, CardSuit.CLUB),
			new Card(CardRank.TEN, CardSuit.HEART)
	};
	
	private Card[] worseFourOfAKindCards = new Card[]{
			new Card(CardRank.EIGHT, CardSuit.HEART),
			new Card(CardRank.EIGHT, CardSuit.DIAMOND),
			new Card(CardRank.EIGHT, CardSuit.SPADE),
			new Card(CardRank.EIGHT, CardSuit.CLUB),
			new Card(CardRank.TEN, CardSuit.SPADE)
	};
	
	private Card[] fullHouseCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.SPADE),
			new Card(CardRank.NINE, CardSuit.CLUB),
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.SPADE),
			new Card(CardRank.JACK, CardSuit.CLUB)
	};
	
	private Card[] worseFullHouseCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.TEN, CardSuit.HEART),
			new Card(CardRank.TEN, CardSuit.SPADE),
			new Card(CardRank.TEN, CardSuit.DIAMOND)
	};
	
	private Card[] flushCards = new Card[]{
			new Card(CardRank.QUEEN, CardSuit.HEART),
			new Card(CardRank.ACE, CardSuit.HEART),
			new Card(CardRank.SEVEN, CardSuit.HEART),
			new Card(CardRank.FOUR, CardSuit.HEART),
			new Card(CardRank.FIVE, CardSuit.HEART)
	};
	
	private Card[] worseFlushCards = new Card[]{
			new Card(CardRank.TWO, CardSuit.DIAMOND),
			new Card(CardRank.KING, CardSuit.DIAMOND),
			new Card(CardRank.QUEEN, CardSuit.DIAMOND),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.SEVEN, CardSuit.DIAMOND)
	};
	
	private Card[] straightCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.CLUB),
			new Card(CardRank.KING, CardSuit.DIAMOND),
			new Card(CardRank.QUEEN, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.TEN, CardSuit.HEART)
	};
	
	private Card[] worseStraightCards = new Card[]{
			new Card(CardRank.ACE, CardSuit.HEART),
			new Card(CardRank.TWO, CardSuit.DIAMOND),
			new Card(CardRank.THREE, CardSuit.HEART),
			new Card(CardRank.FOUR, CardSuit.HEART),
			new Card(CardRank.FIVE, CardSuit.HEART)
	};
	
	private Card[] threeOfAKindCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.NINE, CardSuit.SPADE),
			new Card(CardRank.SEVEN, CardSuit.CLUB),
			new Card(CardRank.TEN, CardSuit.HEART)
	};
	
	private Card[] worseThreeOfAKindCards = new Card[]{
			new Card(CardRank.EIGHT, CardSuit.HEART),
			new Card(CardRank.EIGHT, CardSuit.DIAMOND),
			new Card(CardRank.EIGHT, CardSuit.SPADE),
			new Card(CardRank.SEVEN, CardSuit.CLUB),
			new Card(CardRank.TEN, CardSuit.SPADE)
	};
	
	private Card[] twoPairCards = new Card[]{
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.TEN, CardSuit.SPADE),
			new Card(CardRank.TEN, CardSuit.CLUB),
			new Card(CardRank.THREE, CardSuit.SPADE)
	};
	
	private Card[] badTwoPairCards = new Card[]{
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.TEN, CardSuit.SPADE),
			new Card(CardRank.TEN, CardSuit.CLUB),
			new Card(CardRank.TWO, CardSuit.SPADE)
	};
	
	private Card[] worseTwoPairCards = new Card[]{
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.NINE, CardSuit.SPADE),
			new Card(CardRank.NINE, CardSuit.CLUB),
			new Card(CardRank.THREE, CardSuit.SPADE)
	};
	
	private Card[] worstTwoPairCards = new Card[]{
			new Card(CardRank.SEVEN, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.DIAMOND),
			new Card(CardRank.EIGHT, CardSuit.SPADE),
			new Card(CardRank.EIGHT, CardSuit.CLUB),
			new Card(CardRank.TWO, CardSuit.SPADE)
	};
	
	private Card[] pairCards = new Card[]{
			new Card(CardRank.TEN, CardSuit.HEART),
			new Card(CardRank.TEN, CardSuit.DIAMOND),
			new Card(CardRank.NINE, CardSuit.SPADE),
			new Card(CardRank.EIGHT, CardSuit.CLUB),
			new Card(CardRank.TWO, CardSuit.SPADE)
	};
	
	private Card[] badPairCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.QUEEN, CardSuit.SPADE),
			new Card(CardRank.EIGHT, CardSuit.CLUB),
			new Card(CardRank.TWO, CardSuit.SPADE)
	};
	
	private Card[] worsePairCards = new Card[]{
			new Card(CardRank.NINE, CardSuit.HEART),
			new Card(CardRank.NINE, CardSuit.DIAMOND),
			new Card(CardRank.JACK, CardSuit.SPADE),
			new Card(CardRank.SEVEN, CardSuit.CLUB),
			new Card(CardRank.THREE, CardSuit.SPADE)
	};
	
	//This hand is a tragedy
	private Card[] highCardCards = new Card[]{
			new Card(CardRank.ACE, CardSuit.HEART),
			new Card(CardRank.KING, CardSuit.HEART),
			new Card(CardRank.QUEEN, CardSuit.HEART),
			new Card(CardRank.JACK, CardSuit.HEART),
			new Card(CardRank.TWO, CardSuit.SPADE)
	};
	
	private Card[] worseHighCardCards = new Card[]{
			new Card(CardRank.TWO, CardSuit.HEART),
			new Card(CardRank.THREE, CardSuit.DIAMOND),
			new Card(CardRank.FOUR, CardSuit.SPADE),
			new Card(CardRank.FIVE, CardSuit.CLUB),
			new Card(CardRank.SEVEN, CardSuit.SPADE)
	};


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		//Ensure creation of a hand works
		PokerHand hand = new PokerHandImpl(flushCards);
		assertNotNull(hand);
	}
		
	/*
	 * A note on testing methodology
	 * Each hand is tested thus:
	 * -it loses to the hand above
	 * -it beats a worse version of itself
	 * -it loses to a better version of itself
	 * -(The last two are repeated a few times for
	 * 	e.g. two pair, where there are more than one
	 * 	criteria for winning against same hand)
	 * -it beats the hand below it
	 */
	
	@Test
	public void testStraightFlush(){
		PokerHand straightFlush = new PokerHandImpl(straightFlushCards);
		PokerHand worseStraightFlush = new PokerHandImpl(worseStraightFlushCards);
		PokerHand fourOfAKind = new PokerHandImpl(fourOfAKindCards);
		
		//self
		assertTrue(straightFlush.beats(worseStraightFlush));
		assertFalse(worseStraightFlush.beats(straightFlush));
		
		//hand below
		assertTrue(straightFlush.beats(fourOfAKind));
	}
	
	@Test
	public void testFourOfAKind(){
		PokerHand fourOfAKind = new PokerHandImpl(fourOfAKindCards);
		PokerHand worseFourOfAKind = new PokerHandImpl(worseFourOfAKindCards);
		PokerHand straightFlush = new PokerHandImpl(straightFlushCards);
		PokerHand fullHouse = new PokerHandImpl(fullHouseCards);
		
		//hand above
		assertTrue(fourOfAKind.beats(worseFourOfAKind));
		
		//self
		assertFalse(worseFourOfAKind.beats(fourOfAKind));
		assertFalse(fourOfAKind.beats(straightFlush));
		
		//hand below
		assertTrue(fourOfAKind.beats(fullHouse));
	}
	
	@Test
	public void testFullHouse(){
		PokerHand fourOfAKind = new PokerHandImpl(fourOfAKindCards);
		PokerHand fullHouse = new PokerHandImpl(fullHouseCards);
		PokerHand worseFullHouse = new PokerHandImpl(worseFullHouseCards);
		PokerHand flush = new PokerHandImpl(flushCards);
		
		//hand above
		assertTrue(fullHouse.beats(worseFullHouse));
		
		//self
		assertFalse(worseFullHouse.beats(fourOfAKind));
		assertTrue(fullHouse.beats(flush));
		
		//hand below
		assertFalse(fullHouse.beats(fourOfAKind));
	}
	
	@Test
	public void testFlush(){
		PokerHand flush = new PokerHandImpl(flushCards);
		PokerHand fullHouse = new PokerHandImpl(fullHouseCards);
		PokerHand worseFlush = new PokerHandImpl(worseFlushCards);
		PokerHand straight = new PokerHandImpl(straightCards);
		
		//hand above
		assertFalse(flush.beats(fullHouse));
		
		//self
		assertTrue(flush.beats(worseFlush));
		assertFalse(worseFlush.beats(flush));
		
		//hand below
		assertTrue(flush.beats(straight));
	}
	
	@Test
	public void testStraight(){
		PokerHand flush = new PokerHandImpl(flushCards);
		PokerHand straight = new PokerHandImpl(straightCards);
		PokerHand worseStraight = new PokerHandImpl(worseStraightCards);
		PokerHand threeOfAKind = new PokerHandImpl(threeOfAKindCards);
		
		//hand above
		assertFalse(straight.beats(flush));
		
		//self
		assertTrue(straight.beats(worseStraight));
		assertFalse(worseStraight.beats(straight));
		
		//Ace Cace
		assertTrue(worseStraight.beats(threeOfAKind));
		
		//hand below
		assertTrue(straight.beats(threeOfAKind));
	}
	
	@Test
	public void testThreeOfAKind(){
		PokerHand twoPair = new PokerHandImpl(twoPairCards);
		PokerHand straight = new PokerHandImpl(straightCards);
		PokerHand worseThreeOfAKind = new PokerHandImpl(worseThreeOfAKindCards);
		PokerHand threeOfAKind = new PokerHandImpl(threeOfAKindCards);
		
		//hand above
		assertFalse(threeOfAKind.beats(straight));
		
		//self
		assertTrue(threeOfAKind.beats(worseThreeOfAKind));
		assertFalse(worseThreeOfAKind.beats(threeOfAKind));
		
		//hand below
		assertTrue(threeOfAKind.beats(twoPair));
	}
	
	@Test
	public void testTwoPair(){
		PokerHand twoPair = new PokerHandImpl(twoPairCards);
		PokerHand badTwoPair = new PokerHandImpl(badTwoPairCards);
		PokerHand worseTwoPair = new PokerHandImpl(worseTwoPairCards);
		PokerHand worstTwoPair = new PokerHandImpl(worstTwoPairCards);
		PokerHand pair = new PokerHandImpl(pairCards);
		PokerHand threeOfAKind = new PokerHandImpl(threeOfAKindCards);
		
		//hand above
		assertFalse(twoPair.beats(threeOfAKind));
		
		//self (1)
		assertTrue(twoPair.beats(badTwoPair));
		assertFalse(badTwoPair.beats(twoPair));
		
		//self (2)
		assertTrue(badTwoPair.beats(worseTwoPair));
		assertFalse(worseTwoPair.beats(badTwoPair));
		
		//self (3)
		assertTrue(worseTwoPair.beats(worstTwoPair));
		assertFalse(worstTwoPair.beats(worseTwoPair));
		
		//hand below
		assertTrue(twoPair.beats(pair));
	}
	
	@Test
	public void testPair(){
		PokerHand pair = new PokerHandImpl(pairCards);
		PokerHand badPair = new PokerHandImpl(badPairCards);
		PokerHand worsePair = new PokerHandImpl(worsePairCards);
		PokerHand twoPair = new PokerHandImpl(twoPairCards);
		PokerHand highCard = new PokerHandImpl(highCardCards);
		
		//hand above
		assertFalse(pair.beats(twoPair));
		
		//self (1)
		assertTrue(pair.beats(badPair));
		assertFalse(badPair.beats(pair));
		
		//self (2)
		assertTrue(badPair.beats(worsePair));
		assertFalse(worsePair.beats(badPair));
		
		//hand below
		assertTrue(pair.beats(highCard));
	}
	
	@Test
	public void testHighCard(){
		PokerHand highCard = new PokerHandImpl(highCardCards);
		PokerHand worseHighCard = new PokerHandImpl(worseHighCardCards);
		PokerHand pair = new PokerHandImpl(pairCards);
		
		//hand above
		assertFalse(highCard.beats(pair));
		
		//self
		assertTrue(highCard.beats(worseHighCard));
		assertFalse(worseHighCard.beats(highCard));

	}

}
