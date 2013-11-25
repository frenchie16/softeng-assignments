package cs3733.args;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {

	private ArgumentParser emptyParser, basicBinaryParser;
	private ArrayList<ArgumentDescriptor> basicBinarySchema;
	
	@Before
	public void setup(){
		emptyParser = new ArgumentParser(new ArrayList<ArgumentDescriptor>());
		
		basicBinarySchema = new ArrayList<ArgumentDescriptor>();
		basicBinarySchema.add(new ArgumentDescriptor(ArgumentType.BINARY, "flag", false));
		basicBinaryParser = new ArgumentParser(basicBinarySchema);
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
}
