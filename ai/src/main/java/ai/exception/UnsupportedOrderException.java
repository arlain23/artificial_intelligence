package ai.exception;

public class UnsupportedOrderException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public UnsupportedOrderException(Throwable ex) {
		super(ex);
	}
	public UnsupportedOrderException(String msg) {
		super("wrong order format: " + msg + " . Use --help to get "
				+ "information about correct order format.");
	}
}
