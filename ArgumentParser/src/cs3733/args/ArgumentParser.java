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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The ArgumentParser is the main class for this assignment. When you create an
 * instance of this class, you do so by providing a schema. The schema is a collection
 * of ArgumentDescriptors that the arguments that are acceptable on the command line.
 * 
 * @author gpollice
 * @version Nov 16, 2013
 */
public class ArgumentParser {
	
	private final Collection<ArgumentDescriptor> schema;
	
	private final Map<String, Boolean> binaryFlags;
	
	
	/**
	 * Constructor that takes a schema. The schema is a collection of 
	 * ArgumentDescriptor objects and is used by this parser to parse the arguments
	 * from a command line.
	 * @param schema a collection of argument descriptors for this parser to use
	 */
	public ArgumentParser(Collection<ArgumentDescriptor>schema){
		this.schema = schema;
		binaryFlags = new HashMap<String, Boolean>();
		
		for(ArgumentDescriptor ad : schema){
			switch(ad.getArgumentType()){
			case BINARY:
				binaryFlags.put(ad.getFlagValue(), false);
				break;
			case INTEGER:
				break;
			case STRING:
				break;
			}
		}
	}
	
	/**
	 * Parse a command line. This method is given the elements from the command line
	 * that need to be "parsed." The client is expected to parse the characters of the
	 * command line and organize it into flags and values. These are place into the 
	 * array of strings that are input to this method.
	 * 
	 * @param commandLineStrings the array of Strings that are the elements from the 
	 * 	command line in the order in which they appear
	 * @throws ArgumentException if there is an error when parsing. For example if the
	 * 	method receives a flag that is not defined in the schema.
	 */
	public void parse(String[] commandLineStrings) throws ArgumentException {
		for(String s : commandLineStrings){
			boolean matched = false;
			for(ArgumentDescriptor ad : schema){
				if(s.equals(ad.getFlagValue())){
					switch(ad.getArgumentType()){
					case BINARY:
						binaryFlags.put(s, true);
						break;
					case INTEGER:
						break;
					case STRING:
						break;
					}
					matched = true;
					break;
				}
			}
			if(!matched){
				throw new ArgumentException("Invalid input on string " + s);
			}
		}
	}
	
	/**
	 * If the flag has been defined on the last parsed command line, this method returns true.
	 * @param flag the string representing the argument's flag
	 * @return true if the flag has been defined in the last parsed command line
	 * @throws ArgumentException if the flag given is not defined in the schema
	 */
	public boolean IsArgumentPresent(String flag) throws ArgumentException {
		Boolean flagPresent = binaryFlags.get(flag);
		if(flagPresent == null) throw new ArgumentException("Nonexistant flag");
		return flagPresent.booleanValue();
	}
	
	/**
	 * Return the value associated with the string valued argument. If the argument was 
	 * not specified on the last parsed command line, but was in the schema, then return a null.
	 * @param flag the string representing the argument's flag
	 * @return the value of the argument or null if the argument was not specified on the
	 * 	last parsed command line
	 * @throws ArgumentException if the flag was not specified in the schema
	 */
	public String getStringArgumentValue(String flag) throws ArgumentException {
		return null;
	}
	
	/**
	 * Return the value associated with the string valued argument. If the argument was 
	 * not specified on the last parsed command line, but was in the schema, then return a 0.
	 * @param flag the string representing the argument's flag
	 * @return the value of the argument or zero if the argument was not specified on the
	 * 	last parsed command line
	 * @throws ArgumentException if the flag was not specified in the schema
	 */
	public int getIntegerArgumentValue(String flag) throws ArgumentException {
		return 0;
	}
}
