package at.andiwand.common.math.graph;

import at.andiwand.common.util.collections.Multiset;


public interface UndirectedGraph<V, E extends UndirectedEdge> extends
		Graph<V, E> {
	
	@Override
	public Multiset<E> getEdges();
	
	public int getVertexDegree(V vertex);
	
}