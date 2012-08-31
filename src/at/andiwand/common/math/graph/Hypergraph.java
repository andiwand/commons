package at.andiwand.common.math.graph;

import java.util.Set;


public interface Hypergraph<V, E extends Hyperedge> extends Graph<V, E> {
	
	@Override
	public Set<E> getEdges();
	
}