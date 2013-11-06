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
 * This class implements the PokerHand interface. Students are free to add any
 * methods to this class, but they must implement those methods that are defined
 * in the interface.
 * @version Oct 26, 2013
 */
public class PokerHandImpl implements PokerHand
{
	/**
	 * Default constructor
	 * @param cards the five cards
	 */
	public PokerHandImpl(Card[] cards) 
	{
		// TODO: Implement this constructor.
	}
	
	/*
	 * @see cs3733.poker.PokerHand#beats(cs3733.poker.PokerHand)
	 */
	@Override
	public boolean beats(PokerHand other)
	{
		// TODO Implement this method.
		return false;
	}

	/*
	 * @see cs3733.poker.PokerHand#getCards()
	 */
	@Override
	public Card[] getCards()
	{
		// TODO Implement this method.
		return null;
	}

}
