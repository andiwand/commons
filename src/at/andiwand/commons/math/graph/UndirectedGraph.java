package at.andiwand.commons.math.graph;

import at.andiwand.commons.util.collection.Multiset;

public interface UndirectedGraph<V, E extends UndirectedEdge> extends
	Graph<V, E> {

    @Override
    public Multiset<E> getEdges();

    public int getVertexDegree(V vertex);

}