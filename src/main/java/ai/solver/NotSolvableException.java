package ai.solver;

public class NotSolvableException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public NotSolvableException(Throwable ex) {
		super(ex);
	}
	public NotSolvableException(String msg) {
		super(msg);
	}
}
