/*******************************************************************************
 * Copyright (c) 2013 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Used in CS3733, Software Engineering at Worcester Polytechnic Institute
 * 
 * Contributors:
 * John French
 * Lou Fogel
 *******************************************************************************/

package cs3733.poker;

/**
 * This class implements the PokerHand interface. Students are free to add any
 * methods to this class, but they must implement those methods that are defined
 * in the interface.
 * @version Oct 26, 2013
 */
public class PokerHandImpl implements PokerHand {
	
	private final Card[] cards;

	/**
	 * Default constructor
	 * @param cards the five cards in this hand
	 */
		
	public PokerHandImpl(Card[] cards){
		this.cards = cards;
	}
	
	/*
	 * @see cs3733.poker.PokerHand#beats(cs3733.poker.PokerHand)
	 */
	@Override
	public boolean beats(PokerHand other){
		//We use the HandClassification class because we can't modify PokerHand so that it implements Comparable.
		return new HandClassification(cards).compareTo(new HandClassification(other.getCards())) > 0;
	}

	/*
	 * @see cs3733.poker.PokerHand#getCards()
	 */
	@Override
	public Card[] getCards(){
		return cards;
	}
}
