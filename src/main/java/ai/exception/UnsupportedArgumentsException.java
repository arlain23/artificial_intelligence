package ai.exception;

public class UnsupportedArgumentsException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public UnsupportedArgumentsException(Throwable ex) {
		super(ex);
	}
	public UnsupportedArgumentsException(String msg) {
		super("method " + msg + " not supported. Use --help to get "
				+ "information about supported methods.");
	}
}
