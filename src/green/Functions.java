package green;

import java.util.ArrayList;

import structures.*;

public class Functions {
	public AdjListGraph<Integer, Integer> colorGraph(AdjListGraph<Integer, Integer> graph){
		ArrayList<Integer> colors = new ArrayList<Integer>();
		ArrayList<Integer> available;
		colors.add(0);
		
		for(Vertex v:(Iterable<Vertex<Integer>>)graph.vertices()){
			available = new ArrayList<Integer>();
			available.addAll(colors);
			for(Edge e:(Iterable<Edge<Integer>>)v.getIncidentEdges()){
				int element = (int)graph.opposite(v, e).element();
				if(available.contains(element))
					available.remove(available.indexOf(element));
			}
			
			if(available.size()==0){
				colors.add(colors.size());
				available.add(colors.size()-1);
			}
			
			v.setElement(available.get(0));
		
		}
		
		return graph;
	}
}
