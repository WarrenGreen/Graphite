package structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Edge<E>  {
	private E 					element;
	private Vertex[] 			endpoints;
	private Position<Edge<E>> 	edgeLoc;
	private Map<Object, Object> attributes;
	
	public Edge(Vertex u, Vertex v, E element){
		this.element 		= element;
		this.endpoints		= new Vertex[2];
		this.endpoints[0]	= u;
		this.endpoints[1] 	= v;
		attributes			= new HashMap<Object, Object>(5);
		attributes.put("visited", "false");
		
	}
	
	public Vertex[] endpoints() {
		return this.endpoints;
	}
	
	public Position<Edge<E>> getEdgeLoc(){
		return this.edgeLoc;
	}
	
	
	public E element() {
		return this.element;
	}
	
	public void setElement(E e) {
		this.element = e;
	}

	
	public Iterable<Entry<Object, Object>> entrySet() {
		return attributes.entrySet();
	}

	
	public Object get(Object key) throws InvalidKeyException {
		return this.attributes.get(key);
	}

	
	public boolean isEmpty() {
		return this.attributes.isEmpty();
	}

	
	public Iterable<Object> keySet() {
		return this.attributes.keySet();
	}

	
	public Object put(Object key, Object value) throws InvalidKeyException {
		return this.attributes.put(key, value);
	}

	
	public Object remove(Object key) throws InvalidKeyException {
		return this.attributes.remove(key);
	}

	
	public int size() {
		return this.size();
	}

	
	public Iterable<Object> values() {
		return this.attributes.values();
	}
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawLine((int)endpoints[0].getCoords().getX()+13, (int)endpoints[0].getCoords().getY()+13, (int)endpoints[1].getCoords().getX()+13, (int)endpoints[1].getCoords().getY()+13);
		
	}

}
