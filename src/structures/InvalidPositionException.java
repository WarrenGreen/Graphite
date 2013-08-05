package structures;

/**
 * Thrown for an invalid Position
 * 
 * @author WarrenGreen
 *
 */

public class InvalidPositionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5683225046386083981L;

	public InvalidPositionException(String msg){
		super(msg);
	}
	
	public InvalidPositionException(){
		
	}
	
	

}
