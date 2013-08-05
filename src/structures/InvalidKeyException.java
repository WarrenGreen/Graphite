package structures;

/**
 * Thrown when a key is proven to be invalid.
 * 
 * @author WarrenGreen
 *
 */
public class InvalidKeyException  extends RuntimeException {
	  public InvalidKeyException (String message) {
	    super (message);
  }
}