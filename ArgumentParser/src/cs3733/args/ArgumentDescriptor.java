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
 * The ArgumentDescriptor describes a single argument that may occur on a command
 * line for a specific program. It is simply a data structure that contains fields
 * that describe each property of a command line argument.
 * 
 * @author gpollice
 * @version Nov 16, 2013
 */
public class ArgumentDescriptor
{
	private final ArgumentType argumentType;
	private final String flagValue;
	private final boolean valueRequired;
	
	/**
	 * Constructor that takes all of the possible fields required for an
	 * ArgumentDescriptor.
	 * 
	 * @param argumentType the type of the argument this descriptor describes
	 * @param flagValue the actual value of the flag, not including the '-' or '--'
	 * @param valueRequired true if the argument is required on the command line
	 */
	public ArgumentDescriptor(ArgumentType argumentType, String flagValue, boolean valueRequired)
	{
		this.argumentType = argumentType;
		this.flagValue = flagValue;
		this.valueRequired = valueRequired;
	}
	
	/**
	 * Constructor that takes the argument type and the actual value of the flag and 
	 * sets the valueRequired field to true.
	 *@param argumentType the type of the argument this descriptor describes
	 * @param flagValue the actual value of the flag, not including the '-' or '--'
	 */
	public ArgumentDescriptor(ArgumentType argumentType, String flagValue)
	{
		this(argumentType, flagValue, true);
	}

	/**
	 * @return the argumentType
	 */
	public ArgumentType getArgumentType()
	{
		return argumentType;
	}

	/**
	 * @return the flagValue
	 */
	public String getFlagValue()
	{
		return flagValue;
	}

	/**
	 * @return true if the argument is required
	 */
	public boolean isRequired()
	{
		return valueRequired;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean result = obj instanceof ArgumentDescriptor;
		if (result) {
			final ArgumentDescriptor other = (ArgumentDescriptor)obj;
			result = argumentType == other.argumentType
					&& flagValue.equals(other.flagValue)
					&& valueRequired == other.valueRequired;
		}
		return result;
	}
	
	@Override
	public int hashCode()
	{
		return argumentType.hashCode() + flagValue.hashCode()
				+ (valueRequired ? 1 : 0);
	}
}
