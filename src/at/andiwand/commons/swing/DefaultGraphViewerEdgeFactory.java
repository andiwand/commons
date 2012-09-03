package at.andiwand.commons.swing;

import java.util.Set;

import at.andiwand.commons.math.graph.Edge;


public class DefaultGraphViewerEdgeFactory extends
		GenericGraphViewerEdgeFactory<Edge, DefaultGraphViewerEdge> {
	
	public DefaultGraphViewerEdgeFactory() {
		super(Edge.class, DefaultGraphViewerEdge.class);
	}
	
	@Override
	protected DefaultGraphViewerEdge buildEdgeGeneric(Edge edge,
			Set<GraphViewerVertex> vertices) {
		return new DefaultGraphViewerEdge(edge, vertices);
	}
	
}