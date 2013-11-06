package cs3733.poker;

public class UnspecifiedBehaviorException extends RuntimeException {

	public UnspecifiedBehaviorException(String string) {
		super(string);
	}
	//This exception is thrown when unspecified behavior is requested (e.g. invalid input).
}
