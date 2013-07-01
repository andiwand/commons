package at.andiwand.commons.math.graph;

import at.andiwand.commons.util.collection.Multiset;

public interface UndirectedEdge extends Edge {

    public Object getVertexA();

    public Object getVertexB();

    public Multiset<? extends Object> getVertices();

    public boolean isLoop();

}