package cs3733.poker;

public class UnspecifiedBehaviorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnspecifiedBehaviorException(String string) {
		super(string);
	}
	//This exception is thrown when unspecified behavior is requested (e.g. invalid input).
}
