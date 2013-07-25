package at.stefl.commons.swing.graph;

import java.util.Set;

import at.stefl.commons.math.graph.Edge;

public abstract class GenericGraphViewerEdgeFactory<E extends Edge, GE extends GraphViewerEdge>
	implements GraphViewerEdgeFactory {

    private final Class<E> edgeClass;
    private final Class<GE> viewerEdgeClass;

    public GenericGraphViewerEdgeFactory(Class<E> edgeClass,
	    Class<GE> viewerEdgeClass) {
	this.edgeClass = edgeClass;
	this.viewerEdgeClass = viewerEdgeClass;
    }

    @Override
    public final Class<E> getEdgeClass() {
	return edgeClass;
    }

    @Override
    public final Class<GE> getViewerEdgeClass() {
	return viewerEdgeClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final GE buildEdge(Edge edge, Set<GraphViewerVertex> vertices) {
	return buildEdgeGeneric((E) edge, vertices);
    }

    protected abstract GE buildEdgeGeneric(E edge,
	    Set<GraphViewerVertex> vertices);

}