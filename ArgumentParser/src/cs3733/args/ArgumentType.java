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
 * <p>
 * The ArgumentType enumeration provides constants that describe the type of an argument
 * that may appear on a command line. There are three types of arguments:
 * <ol>
 *   <li>
 *     <em>BINARY</em>&mdash;a simple flag that has no value associated with it. For
 *     example, "-n".
 *   </li>
 *   <li>
 *     <em>STRING</em>&mdash;a flag that may be followed by a string value. For example,
 *     "-m July".
 *   </li>
 *   <li>
 *     <em>INTEGER</em>&mdash;a flag that may be followed by an integer value. For example,
 *     "-l 53".
 *   </li>
 * </ol>
 * </p>
 * @version Nov 16, 2013
 */
public enum ArgumentType
{
	BINARY, INTEGER, STRING;
}
