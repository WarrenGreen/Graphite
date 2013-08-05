package structures;

/**
 * An interface for a Node Position List.
 * 
 * @author WarrenGreen
 *
 * @param <E>
 */

public interface PositionList<E> extends Iterable<E> {

	/** Returns the number of nodes in the list */
	public int size();
	
	/** Returns whether the list is empty */
	public boolean isEmpty();
	
	/** Returns the first node in the list */
	public Position<E> first();
	
	/** Returns the last node in the list */
	public Position<E> last();
	
	/** Returns the node after the given node in the list */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/** Returns the node before the given node in the list */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/** Inserts a new node at the beginning of the list and returns the new position */
	public void addFirst(E e);
	
	/** Inserts a new node at the end of the list and returns the new position */
	public void addLast(E e);
	
	/** Inserts a new node after the given node */
	public void addAfter(Position<E> p, E e) throws InvalidPositionException;
	
	/** Inserts a new node before the given node */
	public void addBefore(Position<E> p, E e) throws InvalidPositionException;
	
	/** Remove the given node and return the element that was stored in the removed node */
	public E remove(Position<E> p) throws InvalidPositionException;
	
	/** Set the element in the given node to the given element and return the old element */
	public E set(Position<E> p, E e) throws InvalidPositionException;
}
