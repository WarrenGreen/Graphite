package structures;


/**
 * A node to be used in a node position list.
 * 
 * @author WarrenGreen
 *
 * @param <E>
 */
public class LNode<E> implements Position<E> {

	private LNode<E> prev, next; //References to adjacent nodes
	private E element; 			 //currents node's element
	
	/** constructor */
	public LNode(LNode<E> prev, LNode<E> next, E element) {
		this.prev 	 = prev;
		this.next 	 = next;
		this.element = element;
	}
	
	/**
	 * Returns the position's element
	 * 
	 * @return the position's element
	 * @throws InvalidPositionException 
	 */
	@Override
	public E element() throws InvalidPositionException {
		if((this.prev == null) && (this.next == null))
			throw new InvalidPositionException("Position is not in a list.");
		return this.element;
	}
	
	/**
	 * 
	 * @return the Node after the current in the list
	 */
	public LNode<E> getNext() {
		return this.next;
	}
	
	/**
	 * 
	 * @return the Node before the current in the list
	 */
	public LNode<E> getPrev() {
		return this.prev;
	}
	
	/**
	 * Set the next node in the list after the current
	 * 
	 * @param next - the node that is to be next in the list after the current 
	 */
	public void setNext(LNode<E> next){
		this.next = next;
	}
	
	/**
	 * Set the previous node in the list before the current
	 * 
	 * @param prev - the node that is to be previous in the list after the current
	 */
	public void setPrev(LNode<E> prev){
		this.prev = prev;
	}
	
	/**
	 * Set the element of the current node
	 * 
	 * @param element - the to-be element
	 */
	public void setElement(E element){
		this.element = element;
	}

}
