/*******************************************************************************
 * Copyright (c) 2013 Gary F. Pollice
 * 
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Used in CS3733, Software Engineering at Worcester Polytechnic Institute
 *******************************************************************************/

package cs3733.poker;

/**
 * Data structure representing a card in a deck of cards.
 * 
 * Students must implement the required behavior as noted in the code. All
 * unimplemented methods have empty bodies and contain comments indicating that
 * they must be implemented.
 * 
 * Students may NOT add other methods to this class.
 * @version Oct 26, 2013
 */
public class Card
{
	
	private CardRank rank;
	private CardSuit suit;
	
	/**
	 * Constructor that sets the values of the card's properties.
	 * @param rank the card's rank
	 * @param suit the card's suit
	 */
	public Card(CardRank rank, CardSuit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * @return the card's rank
	 */
	public CardRank getRank()
	{
		return rank;
	}

	/**
	 * @return the card's suit
	 */
	public CardSuit getSuit()
	{
		return suit;
	}
}
