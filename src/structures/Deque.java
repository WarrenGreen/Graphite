package structures;

/**
 * 
 * An interface for a deque which is a double ended queue.
 * 
 * @author WarrenGreen
 *
 * @param <E>
 */

public interface Deque<E> {
	
	/** Returns the number of elements in the deque */
	public int size();
	
	/** Returns whether the deque is empty */
	public boolean isEmpty();
	
	/** Returns the first element in the deque */
	public E getFirst() throws EmptyDequeException;
	
	/** Returns the last element in the deque */
	public E getLast() throws EmptyDequeException;
	
	/** Inserts an element to the beginning of the list */
	public void addFirst (E element);
	
	/** Inserts an element to the end of the list */
	public void addLast (E element);
	
	/** Deletes the first element from the list and returns it */
	public E removeFirst() throws EmptyDequeException;
	
	/** Deletes the last element from the list and returns it */
	public E removeLast() throws EmptyDequeException;
}
