package at.stefl.commons.swing.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphLayout implements GraphLayout {

    private final GraphViewer viewer;

    private final List<GraphViewerVertex> vertices = new ArrayList<GraphViewerVertex>();
    private final List<GraphViewerEdge> edges = new ArrayList<GraphViewerEdge>();

    public AbstractGraphLayout(GraphViewer viewer) {
	this.viewer = viewer;
    }

    @Override
    public Dimension getMinimumSize() {
	return new Dimension();
    }

    @Override
    public Dimension getPreferedSize() {
	Rectangle all = new Rectangle();

	synchronized (vertices) {
	    for (GraphViewerVertex vertex : vertices) {
		all = all.union(vertex.getBounds());
	    }
	}

	return all.getSize();
    }

    @Override
    public Dimension getMaximumSize() {
	return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected final Dimension getSize() {
	return viewer.getSize();
    }

    public GraphViewer getGraphViewer() {
	return viewer;
    }

    protected final int getVertexCount() {
	synchronized (vertices) {
	    return vertices.size();
	}
    }

    protected final int getEdgeCount() {
	synchronized (edges) {
	    return edges.size();
	}
    }

    protected final List<GraphViewerVertex> getVertices() {
	synchronized (vertices) {
	    return new ArrayList<GraphViewerVertex>(vertices);
	}
    }

    protected final List<GraphViewerEdge> getEdges() {
	synchronized (edges) {
	    return new ArrayList<GraphViewerEdge>(edges);
	}
    }

    @Override
    public final void addViewerVertex(GraphViewerVertex vertex) {
	addViewerVertexImpl(vertex);

	synchronized (vertices) {
	    vertices.add(vertex);
	}

	viewer.setPreferredSize(getPreferedSize());
	fireRevalidate();
    }

    protected void addViewerVertexImpl(GraphViewerVertex vertex) {
    }

    @Override
    public final void addViewerEdge(GraphViewerEdge edge) {
	addViewerEdgeImpl(edge);

	synchronized (edges) {
	    edges.add(edge);
	}
    }

    protected void addViewerEdgeImpl(GraphViewerEdge edge) {
    }

    @Override
    public final void removeViewerVertex(GraphViewerVertex vertex) {
	removeViewerVertexImpl(vertex);

	synchronized (vertices) {
	    vertices.add(vertex);
	}
    }

    protected void removeViewerVertexImpl(GraphViewerVertex vertex) {
    }

    @Override
    public final void removeViewerEdge(GraphViewerEdge edge) {
	removeViewerEdgeImpl(edge);

	synchronized (edges) {
	    edges.add(edge);
	}
    }

    protected void removeViewerEdgeImpl(GraphViewerEdge edge) {
    }

    @Override
    public void moveVertex(GraphViewerVertex vertex, Point p) {
	Rectangle newBounds = vertex.getBounds();
	newBounds.setLocation(p);

	if (newBounds.x < 0)
	    newBounds.x = 0;
	if (newBounds.y < 0)
	    newBounds.y = 0;

	vertex.setPosition(newBounds.getLocation());
	viewer.setPreferredSize(getPreferedSize());
	fireRevalidate();
	viewer.scrollRectToVisible(vertex.getBounds());
    }

    protected Point moveVertexImpl(GraphViewerVertex vertex, Point p) {
	return p;
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void revalidate(GraphViewerVertex vertex) {
    }

    protected final void fireRepaint() {
	viewer.repaint();
    }

    protected final void fireRevalidate() {
	viewer.revalidate();
    }

}