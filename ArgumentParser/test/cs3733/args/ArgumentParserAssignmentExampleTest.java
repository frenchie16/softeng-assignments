package cs3733.args;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests from the assignment spec document.
 */

public class ArgumentParserAssignmentExampleTest {

	private ArrayList<ArgumentDescriptor> exampleSchema;
	private ArgumentParser exampleParser;
	
	@Before
	public void setup(){
		exampleSchema = new ArrayList<ArgumentDescriptor>();
		exampleSchema.add(new ArgumentDescriptor(ArgumentType.STRING, "sourcepath", false)); //assignment is wrong and this should not be reauired
		exampleSchema.add(new ArgumentDescriptor(ArgumentType.STRING, "source", false)); //ditto above
		exampleSchema.add(new ArgumentDescriptor(ArgumentType.BINARY, "nowarn", true));
		exampleParser = new ArgumentParser(exampleSchema);
	}
	
	
	@Test
	public void test1() throws ArgumentException{
		exampleParser.parse(new String[]{"sourcepath",  ".", "nowarn"});
		assertTrue(exampleParser.getStringArgumentValue("sourcepath").equals("."));
	}

	@Test(expected=ArgumentException.class)
	public void test2() throws ArgumentException {
		exampleParser.parse(new String[]{"version"});
	}
	
	@Test
	public void test3() throws ArgumentException {
		exampleParser.parse(new String[]{"nowarn"});
		assertTrue(exampleParser.IsArgumentPresent("nowarn"));
	}
	
	@Test
	public void test4() throws ArgumentException {
		exampleParser.parse(new String[]{"nowarn"});
		assertFalse(exampleParser.IsArgumentPresent("source"));
	}
	
	@Test(expected=ArgumentException.class) //#5
	public void test5() throws ArgumentException {
		exampleParser.parse(new String[]{"nowarn"});
		exampleParser.IsArgumentPresent("version");
	}
}
