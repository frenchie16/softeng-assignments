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

package cs3733.args;

/**
 * Exception that gets thrown whenever there is a problem encountered in parsing arguments.
 * @author gpollice
 * @version Nov 16, 2013
 */
public class ArgumentException extends Exception
{
	/**
	 * Constructor that takes a message describing the problem.
	 * @param message the message describing the problem
	 */
	public ArgumentException(String message)
	{
		super(message);
	}
}
