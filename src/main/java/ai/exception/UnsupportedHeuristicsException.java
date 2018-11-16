package ai.exception;

public class UnsupportedHeuristicsException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public UnsupportedHeuristicsException(Throwable ex) {
		super(ex);
	}
	public UnsupportedHeuristicsException(String msg) {
		super("heuristics " + msg + " not supported. Use --help to get "
				+ "information about supported heuristics.");
	}
}
