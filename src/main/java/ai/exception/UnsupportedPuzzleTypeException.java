package ai.exception;

public class UnsupportedPuzzleTypeException extends Exception{

	private static final long serialVersionUID = -4950051553784346668L;
	

	public UnsupportedPuzzleTypeException(Throwable ex) {
		super(ex);
	}
	public UnsupportedPuzzleTypeException(String msg) {
		super("Unsupported puzzle type. Only eight puzzle (3x3) and fifteen puzzle (4x4) is supported");
	}
}
