package ai.exception;

public class UnsupportedInputException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public UnsupportedInputException(Throwable ex) {
		super(ex);
	}
	public UnsupportedInputException(String msg) {
		super("wrong input format. Use --help to get "
				+ "information about correct input format.");
	}
}
