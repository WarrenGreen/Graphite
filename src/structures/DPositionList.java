package structures;

import java.util.Iterator;
import structures.*;

/**
 * Position List implementation
 * 
 * @author WarrenGreen
 *
 * @param <E>
 */

public class DPositionList<E> implements PositionList<E>, Iterator<E>  {

	private int size; 
	protected LNode<E> head, tail; //Boundary nodes
	private LNode<E> pos;
	
	/** constructor */
	public DPositionList(){
		this.size	= 0;
		this.head	= new LNode<E>(null, null, null);
		this.tail	= new LNode<E>(head, null, null);
		this.head.setNext(tail);
		pos =  head;
	}
	
	/**new ElementIterator<E>(this)
	 * @return int representing the number of nodes in the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * @return boolean representing whether the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (this.size <=0);
	}

	/**
	 * @return the first position in the list
	 */
	@Override
	public Position<E> first() {
		return this.head;
	}

	/**
	 * @return the last position in the list
	 */
	@Override
	public Position<E> last() {
		return this.tail.getPrev();
	}

	/**
	 * @param p - Position whose next to be returned
	 * @return the position after the provided position in the list
	 * @throws InvalidPositionException, BoundaryViolationException
	 */
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		LNode<E> pp 	= checkPosition(p);
		LNode<E> next 	= pp.getNext();
		if(next == tail)
			throw new BoundaryViolationException("Cannot advance past the end of the list");
		return next;
	}

	/**
	 * @param p - Position whose next to be returned
	 * @return the position before the provided position in the list
	 * @throws InvalidPositionException, BoundaryViolationException
	 */
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		LNode<E> pp 	= checkPosition(p);
		LNode<E> prev 	= pp.getPrev();
		if(prev == tail)
			throw new BoundaryViolationException("Cannot advance past the end of the list");
		return prev;
	}

	/**
	 * @param e - element to be added into a new node
	 */
	@Override
	public void addFirst(E e) {
		this.size++;
		LNode<E> newNode = new LNode<E>(this.head, this.head.getNext(), e);
		this.head.getNext().setPrev(newNode);
		this.head.setNext(newNode);
	}

	/**
	 * @param e - element to be added into a new node
	 */
	@Override
	public void addLast(E e) {
		this.size++;
		LNode<E> newNode = new LNode<E>(this.tail.getPrev(), this.tail, e);
		this.tail.getPrev().setNext(newNode);
		this.tail.setPrev(newNode);
	}

	/**
	 * @param p - position to add new element after
	 * @param e - element to be added into a new node
	 * @throws InvalidPositionException
	 */
	@Override
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		this.size++;
		LNode<E> pp = checkPosition(p);
		LNode<E> newNode = new LNode<E>(pp, pp.getNext(), e);
		pp.getNext().setPrev(newNode);
		pp.setNext(newNode);
	}

	/**
	 * @param p - position to add new element before
	 * @param e - element to be added into a new node
	 * @throws InvalidPositionException
	 */
	@Override
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		this.size++;
		LNode<E> pp = checkPosition(p);
		LNode<E> newNode = new LNode<E>(pp.getPrev(), pp, e);
		pp.getPrev().setNext(newNode);
		pp.setPrev(newNode);
	}

	/**
	 * @param p - Position to be removed
	 * @return the element of the removed position
	 * @throws InvalidPositionException
	 */
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		this.size--;
		LNode<E> pp 	= checkPosition(p);
		LNode<E> pPrev 	= pp.getPrev();
		LNode<E> pNext	= pp.getNext();
		E  pElem 		= pp.element();
		
		pPrev.setNext(pNext);
		pNext.setPrev(pPrev);
		pp.setNext(null);
		pp.setPrev(null);
		return pElem;
	}

	/**
	 * @param p - Position whose element to change
	 * @param e - Position's new element
	 * @return Position's old element
	 * @throws InvalidPositionException
	 */
	@Override
	public E set(Position<E> p, E e) throws InvalidPositionException {
		LNode<E> pp = checkPosition(p);
		E old		= pp.element();
		pp.setElement(e);
		return old;
	}
	
	/**
	 * 
	 * @param p - Position to be checked
	 * @return Converted LNode of p
	 * @throws InvalidPositionException
	 */
	private LNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p == null)
			throw new InvalidPositionException("Null position passed to NodeList");
		if(p == this.head)
			throw new InvalidPositionException("The header node is not a valid position");
		if(p == this.tail)
			throw new InvalidPositionException("The tail node is not a valid position");
		try {
			LNode<E> temp = (LNode<E>) p;
			if((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			return temp;
		}catch (ClassCastException ex) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}

	@Override
	public Iterator<E> iterator() {
		pos = head;
		return this;
	}

	@Override
	public boolean hasNext() {
		return (pos.getNext()!=null&&pos.getNext().element()!=null)?true:false;
	}

	@Override
	public E next() {
		return (pos=pos.getNext()).element();
	}

	@Override
	public void remove() {
		this.remove(pos);		
	}

}
