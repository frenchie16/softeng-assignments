package cs3733.args;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {

	private ArgumentParser emptyParser, basicBinaryParser, basicStringParser, basicIntParser, advancedParser;
	private ArrayList<ArgumentDescriptor> basicBinarySchema, basicStringSchema, basicIntSchema, advancedSchema;
	
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
		
		advancedSchema = new ArrayList<ArgumentDescriptor>();
		advancedSchema.add(new ArgumentDescriptor(ArgumentType.INTEGER, "int", true));
		advancedSchema.add(new ArgumentDescriptor(ArgumentType.STRING, "str", true));
		advancedSchema.add(new ArgumentDescriptor(ArgumentType.BINARY, "bool", true));
		advancedParser = new ArgumentParser(advancedSchema);
		
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
	
	@Test(expected=ArgumentException.class) //#8
	public void testMissingTokenForString() throws ArgumentException {
		basicStringParser.parse(new String[]{"flag"});
	}
	
	@Test(expected=ArgumentException.class) //#8
	public void testMissingTokenForInt() throws ArgumentException {
		basicIntParser.parse(new String[]{"flag"});
	}
	
	@Test(expected=ArgumentException.class) //#9
	public void testBadValueForInt() throws ArgumentException {
		basicIntParser.parse(new String[]{"flag", "foo"});
	}
	
	@Test(expected=ArgumentException.class) //#10
	public void testGetNonexistantInt() throws ArgumentException {
		basicIntParser.parse(new String[]{"flag", "10"});
		basicIntParser.getIntegerArgumentValue("notflag");
	}
	
	@Test(expected=ArgumentException.class) //#11
	public void testGetNonexistantString() throws ArgumentException {
		basicStringParser.parse(new String[]{"flag", "foo"});
		basicStringParser.getStringArgumentValue("notflag");
	}
	
	@Test(expected=ArgumentException.class) //#12
	public void testGetIntOnString() throws ArgumentException {
		basicIntParser.parse(new String[]{"flag", "10"});
		basicIntParser.getStringArgumentValue("flag");
	}
	
	@Test(expected=ArgumentException.class) //#13
	public void testGetStringOnInt() throws ArgumentException {
		basicStringParser.parse(new String[]{"flag", "foo"});
		basicStringParser.getIntegerArgumentValue("flag");
	}
	
	@Test //#14
	public void testMixedTypes() throws ArgumentException {
		advancedParser.parse(new String[]{"int", "5", "bool", "str", "foo"});
		assertTrue(advancedParser.IsArgumentPresent("bool"));
		assertTrue(advancedParser.getStringArgumentValue("str").equals("foo"));
		assertEquals(advancedParser.getIntegerArgumentValue("int"), 5);
	}
	
	@Test(expected=ArgumentException.class) //#15
	public void testMissingRequiredBinary() throws ArgumentException {
		advancedParser.parse(new String[]{"int", "5", "str", "foo"});
	}
	
	@Test(expected=ArgumentException.class) //#16
	public void testMissingRequiredString() throws ArgumentException {
		advancedParser.parse(new String[]{"int", "5", "bool"});
	}
	
	@Test(expected=ArgumentException.class) //#17
	public void testMissingRequiredInt() throws ArgumentException {
		advancedParser.parse(new String[]{"bool", "str", "foo"});
	}
}
