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
 * This interface represents the behavior of a poker hand. It contains five cards.
 * Students must not modify this file.
 * @version Oct 26, 2013
 */
public interface PokerHand
{
	/**
	 * @param another poker hand
	 * @return true if this hand beats the other hand, false if the other beats this
	 * 	hand or if they are the same
	 */
	boolean beats(PokerHand other);
	
	/**
	 * @return the five cards from the hand
	 */
	Card[] getCards();
}
