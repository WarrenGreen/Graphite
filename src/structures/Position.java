package structures;

/**
 * 
 * Interface for position, which is a holder object for a single element.
 * 
 * @author WarrenGreen
 *
 * @param <E>
 */

public interface Position<E> {
	
	/** Returns the element stored at this position. */
	public E element() throws InvalidPositionException ;
}
