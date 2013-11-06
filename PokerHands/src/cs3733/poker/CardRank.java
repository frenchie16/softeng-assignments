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

/**
 * Enumeration constants for the ranks of cards in a card deck.
 * @version Oct 26, 2013
 */
public enum CardRank
{
	TWO(1),
	THREE(2),
	FOUR(3),
	FIVE(4),
	SIX(5),
	SEVEN(6),
	EIGHT(7),
	NINE(8),
	TEN(9),
	JACK(10),
	QUEEN(11),
	KING(12),
	ACE(13);
	
	private final int value;
	
	/**
	 * Constructor sets the rank.
	 * @param value
	 */
	private CardRank(int value)
	{
		this.value = value;
	}
	
	/**
	 * @param other the other rank to compare this one with
	 * @return true if this rank beats the other
	 */
	public boolean isHigherThan(CardRank other)
	{
		return value > other.value;
	}
	
	/**
	 * @return the numerical value in the range of 1 to 13
	 */
	public int getValue()
	{
		return value;
	}
}
