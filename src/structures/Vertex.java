package structures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Vertex<E> {
	
	private E 					element;
	private Point				coords = new Point(10,10);;
	private Position<Vertex<E>> vertexLoc;
	private Map<Object, Object> attributes;
	private ArrayList<Edge> 	incidentEdges;
	Ellipse2D oval;
	
	public Vertex(E element){
		this.attributes =  new HashMap<Object, Object>(5);
		attributes.put("color", Color.BLACK);
		this.element = element;
		this.incidentEdges = new ArrayList<Edge>();
		for(int i=0; i<incidentEdges.size(); i++)
			this.incidentEdges.add(incidentEdges.get(i));
	}
	
	public Vertex(E element, int xCoord, int yCoord){
		this.attributes =  new HashMap<Object, Object>(5);
		attributes.put("color", Color.BLACK);
		this.element = element;
		this.coords = new Point(xCoord, yCoord);
		this.incidentEdges = new ArrayList<Edge>();
		for(int i=0; i<incidentEdges.size(); i++)
			this.incidentEdges.add(incidentEdges.get(i));
	}
	
	public E element() {
		return this.element;
	}
	
	public Iterable<Edge> getIncidentEdges() {
		return this.incidentEdges;
	}
	
	public void addIterableEdge(Edge e) {
		this.incidentEdges.add(0,e);
	}
	
	public Edge removeIterableEdge(Edge e){
		incidentEdges.remove(e);
		
		return null;
	}

	public Position<Vertex<E>> getVertexLoc() {
		return vertexLoc;
	}

	public void setVertexLoc(Position<Vertex<E>> vertexLoc) {
		this.vertexLoc = vertexLoc;
	}

	public void setElement(E element) {
		this.element = element;
	}

	
	public Iterable<Entry<Object, Object>> entrySet() {
		return this.attributes.entrySet();
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
		return this.attributes.size();
	}

	
	public Iterable<Object> values() {
		return this.attributes.values();
	}
	
	public Iterable<Edge> getEdges() {
		return this.incidentEdges;
	}
	
	public Point getCoords(){
		return this.coords;
	}
	
	public void setCoords(int xCoord, int yCoord){
		this.coords = new Point(xCoord, yCoord);
	}
	
	public boolean contains(Point p){
		return oval.contains(p);
	}
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		oval = new Ellipse2D.Double(coords.getX(), coords.getY(), 25, 25);
		g2d.setColor((Color)attributes.get("color"));
		g2d.fill(oval);
		g2d.setColor(Color.WHITE);
		if(Integer.parseInt(element.toString()) != -1)
			g2d.drawString(element.toString(), (int)coords.getX()+10, (int)coords.getY()+17);

	}

}
