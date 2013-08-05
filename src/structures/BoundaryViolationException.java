package structures;

/**
 * Thrown when attempting to traverse past the boundaries of a list.
 * 
 * @author WarrenGreen
 *
 */

public class BoundaryViolationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6421177919527661589L;
	
	public BoundaryViolationException(String msg) {
		super(msg);
	}
	
	public BoundaryViolationException(){
		
	}

}
