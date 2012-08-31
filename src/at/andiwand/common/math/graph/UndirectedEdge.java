package at.andiwand.common.math.graph;

import at.andiwand.common.util.collections.Multiset;


public interface UndirectedEdge extends Edge {
	
	public Object getVertexA();
	
	public Object getVertexB();
	
	public Multiset<? extends Object> getVertices();
	
	public boolean isLoop();
	
}