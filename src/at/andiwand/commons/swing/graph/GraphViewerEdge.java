package at.andiwand.commons.swing.graph;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import at.andiwand.commons.math.graph.Edge;

// TODO: make use of Vertex2d
public abstract class GraphViewerEdge {

    private GraphViewer viewer;

    private final Edge edge;
    private final Set<GraphViewerVertex> vertices;

    public GraphViewerEdge(Edge edge, Set<GraphViewerVertex> vertices) {
	this.edge = edge;
	this.vertices = Collections
		.unmodifiableSet(new HashSet<GraphViewerVertex>(vertices));
    }

    public GraphViewer getViewer() {
	return viewer;
    }

    public Edge getEdge() {
	return edge;
    }

    public Set<GraphViewerVertex> getVertices() {
	return vertices;
    }

    protected void setViewer(GraphViewer viewer) {
	this.viewer = viewer;
    }

    public abstract void paint(Graphics g);

    public abstract boolean intersects(Point p);

    protected final void fireRepaint() {
	if (viewer == null)
	    return;
	viewer.repaint();
    }

}