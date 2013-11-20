package cs3733.args;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {

	private ArgumentParser emptyParser;
	
	@Before
	public void setup(){
		emptyParser = new ArgumentParser(new ArrayList<ArgumentDescriptor>());
	}
	
	@Test //#1
	public void testCreate(){
		assertNotNull(emptyParser);
	}
	
	@Test //#2
	public void testEmptyParserThrowsException(){
		try {
			emptyParser.parse(new String[]{"hello", "world"});
		} catch (ArgumentException e) {
			return; //pass test
		}
		fail("Did not throw exception!");
	}

}
