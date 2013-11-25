package cs3733.args;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {

	private ArgumentParser emptyParser, basicBinaryParser, basicStringParser, basicIntParser;
	private ArrayList<ArgumentDescriptor> basicBinarySchema, basicStringSchema, basicIntSchema;
	
	@Before
	public void setup(){
		emptyParser = new ArgumentParser(new ArrayList<ArgumentDescriptor>());
		
		basicBinarySchema = new ArrayList<ArgumentDescriptor>();
		basicBinarySchema.add(new ArgumentDescriptor(ArgumentType.BINARY, "flag", false));
		basicBinaryParser = new ArgumentParser(basicBinarySchema);
		
		basicStringSchema = new ArrayList<ArgumentDescriptor>();
		basicStringSchema.add(new ArgumentDescriptor(ArgumentType.STRING, "flag", false));
		basicStringParser = new ArgumentParser(basicStringSchema);
		
		basicIntSchema = new ArrayList<ArgumentDescriptor>();
		basicIntSchema.add(new ArgumentDescriptor(ArgumentType.INTEGER, "flag", false));
		basicIntParser = new ArgumentParser(basicIntSchema);
	}
	
	@Test //#1
	public void testCreate(){
		assertNotNull(emptyParser);
	}
	
	@Test(expected=ArgumentException.class) //#2
	public void testEmptyParserThrowsException() throws ArgumentException{
		emptyParser.parse(new String[]{"hello", "world"});
	}

	@Test //#3
	public void testBinaryFlagWorksWhenTrue() throws ArgumentException {
		basicBinaryParser.parse(new String[]{"flag"});
		assertTrue(basicBinaryParser.IsArgumentPresent("flag"));
	}
	
	@Test //#4
	public void testBinaryFlagWorksWhenFalse() throws ArgumentException {
		basicBinaryParser.parse(new String[]{});
		assertFalse(basicBinaryParser.IsArgumentPresent("flag"));
	}
	
	@Test(expected=ArgumentException.class) //#5
	public void testInvalidFlagInInput() throws ArgumentException {
		basicBinaryParser.parse(new String[]{"flag"});
		basicBinaryParser.IsArgumentPresent("notflag");
	}
	
	@Test //#6
	public void testStringFlagWorksWhenPresent() throws ArgumentException {
		basicStringParser.parse(new String[]{"flag", "foo"});
		assertTrue(basicStringParser.IsArgumentPresent("flag"));
		assertTrue(basicStringParser.getStringArgumentValue("flag").equals("foo"));
	}
	
	@Test //#7
	public void testIntFlagWorksWhenPresent() throws ArgumentException {
		basicIntParser.parse(new String[]{"flag", "100"});
		assertTrue(basicIntParser.IsArgumentPresent("flag"));
		assertEquals(basicIntParser.getIntegerArgumentValue("flag"), 100);
	}
}
