package structures;

/**
 * Runtime exception throw when attempting to access or remove
 * on an empty deque
 * 
 * @author WarrenGreen
 * 
 *
 */

public class EmptyDequeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4023960659543885459L;

	public EmptyDequeException(String msg) {
		super(msg);
	}
}
