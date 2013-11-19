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

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

/**
 * Test cases for the ArgumentManager class.
 * 
 * @version Nov 16, 2013
 */
public class ArgumentParserSampleTest
{
	// Test #1
	@Test
	public void createValidArgumentManager()
	{
		final Collection<ArgumentDescriptor> schema = new ArrayList<ArgumentDescriptor>();
		assertNotNull(new ArgumentParser(schema));
	}

	@Test
	public void parseSingleBooleanArgumentIsPresent() throws ArgumentException
	{
		final Collection<ArgumentDescriptor> schema = new ArrayList<ArgumentDescriptor>();
		schema.add(new ArgumentDescriptor(ArgumentType.BINARY, "debug"));
		final ArgumentParser parser = new ArgumentParser(schema);
		parser.parse(new String[] {"debug"});
		assertTrue(parser.IsArgumentPresent("debug"));
	}
	
	@Test
	public void parseValidStringValue() throws ArgumentException
	{
		final Collection<ArgumentDescriptor> schema = new ArrayList<ArgumentDescriptor>();
		schema.add(new ArgumentDescriptor(ArgumentType.STRING, "f"));
		final ArgumentParser parser = new ArgumentParser(schema);
		parser.parse(new String[] {"f", "/etc/fstab"});
		assertTrue(parser.IsArgumentPresent("f"));
		assertEquals("/etc/fstab", parser.getStringArgumentValue("f"));
	}
	
	@Test
	public void parseValidIntegerValue() throws ArgumentException
	{
		final Collection<ArgumentDescriptor> schema = new ArrayList<ArgumentDescriptor>();
		schema.add(new ArgumentDescriptor(ArgumentType.INTEGER, "n"));
		final ArgumentParser parser = new ArgumentParser(schema);
		parser.parse(new String[] {"n", "42"});
		assertTrue(parser.IsArgumentPresent("n"));
		assertEquals(100, parser.getIntegerArgumentValue("n"));
	}
	
	// Test #5
	@Test(expected=ArgumentException.class)
	public void parseInvalidFlag() throws ArgumentException
	{
		final Collection<ArgumentDescriptor> schema = new ArrayList<ArgumentDescriptor>();
		schema.add(new ArgumentDescriptor(ArgumentType.INTEGER, "n"));
		final ArgumentParser parser = new ArgumentParser(schema);
		parser.parse(new String[] {"x", "42"});
	}
}
