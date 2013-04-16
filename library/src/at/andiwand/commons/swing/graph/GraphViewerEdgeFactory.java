package at.andiwand.commons.swing.graph;

import java.util.Set;

import at.andiwand.commons.math.graph.Edge;

public interface GraphViewerEdgeFactory {

    public abstract Class<? extends GraphViewerEdge> getViewerEdgeClass();

    public abstract Class<? extends Edge> getEdgeClass();

    public abstract GraphViewerEdge buildEdge(Edge edge,
	    Set<GraphViewerVertex> vertices);

}