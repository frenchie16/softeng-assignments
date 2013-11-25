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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The ArgumentParser is the main class for this assignment. When you create an
 * instance of this class, you do so by providing a schema. The schema is a collection
 * of ArgumentDescriptors that the arguments that are acceptable on the command line.
 * 
 * @author gpollice
 * @version Nov 16, 2013
 */
public class ArgumentParser {

	private class ParseResult {
		final ArgumentType type;
		final boolean required;
		boolean present;
		String stringValue;
		Integer intValue;
		
		public ParseResult(ArgumentType type, boolean required){
			this.type = type;
			this.required = required;
			present = false;
			stringValue = null;
			intValue = null;
		}
	}

	private final Collection<ArgumentDescriptor> schema;
	private Map<String, ParseResult> parseResults;
	
	
	/**
	 * Constructor that takes a schema. The schema is a collection of 
	 * ArgumentDescriptor objects and is used by this parser to parse the arguments
	 * from a command line.
	 * @param schema a collection of argument descriptors for this parser to use
	 */
	public ArgumentParser(Collection<ArgumentDescriptor> schema){
		this.schema = schema;
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
		
		
		//reset parse results from schema
		parseResults = new HashMap<String, ParseResult>();
		
		for(ArgumentDescriptor ad : schema){
			parseResults.put(ad.getFlagValue(), new ParseResult(ad.getArgumentType(), ad.isRequired()));
		}
		
		List<String> clsList = Arrays.asList(commandLineStrings);
		Iterator<String> clIterator = clsList.iterator();
		while(clIterator.hasNext()){
			ParseResult pr = parseResults.get(clIterator.next());
			if(pr == null) throw new ArgumentException("Invalid flag");
			
			//the flag is present so set that
			pr.present = true;
			
			//set value as appropriate
			switch(pr.type){
			case BINARY:
				//we already set present, nothing more to do
				break;
			case INTEGER:
				//set the int value
				try {
					pr.intValue = Integer.parseInt(clIterator.next());
				} catch (NoSuchElementException e){
					throw new ArgumentException("Bad number of tokens!");
				} catch (NumberFormatException e){
					throw new ArgumentException("Invalid value for integer flag!");
				}
				break;
			case STRING:
				//set the string value
				try {
					pr.stringValue = clIterator.next();
				} catch (NoSuchElementException e){
					throw new ArgumentException("Bad number of tokens!");
				}
				break;
			}
		}
		
		//check if any required flags are not present
		for(ParseResult pr : parseResults.values()){
			if(pr.required && !(pr.present)) throw new ArgumentException("Missing required argument!");
		}
	}
	
	/**
	 * If the flag has been defined on the last parsed command line, this method returns true.
	 * @param flag the string representing the argument's flag
	 * @return true if the flag has been defined in the last parsed command line
	 * @throws ArgumentException if the flag given is not defined in the schema
	 */
	public boolean IsArgumentPresent(String flag) throws ArgumentException {
		ParseResult pr = parseResults.get(flag);
		if(pr == null) throw new ArgumentException("Nonexistant flag");
		return pr.present;
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
		ParseResult pr = parseResults.get(flag);
		if(pr == null) throw new ArgumentException("Nonexistant flag");
		if(pr.type != ArgumentType.STRING) throw new ArgumentException("Not a string flag");
		return pr.stringValue;
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
		ParseResult pr = parseResults.get(flag);
		if(pr == null) throw new ArgumentException("Nonexistant flag");
		if(pr.type != ArgumentType.INTEGER) throw new ArgumentException("Not an int flag");
		return pr.intValue;
	}
}
