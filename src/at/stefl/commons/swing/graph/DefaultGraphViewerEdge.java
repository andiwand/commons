package at.stefl.commons.swing.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

import at.stefl.commons.graphics.GraphicsUtil;
import at.stefl.commons.math.graph.Edge;

// TODO: make use of Vertex2d
public class DefaultGraphViewerEdge extends GraphViewerEdge {

    public static final float DEFAULT_LINE_WIDTH = 3;

    private final float lineWidth;

    public DefaultGraphViewerEdge(Edge edge, Set<GraphViewerVertex> vertices) {
	this(edge, vertices, DEFAULT_LINE_WIDTH);
    }

    public DefaultGraphViewerEdge(Edge edge, Set<GraphViewerVertex> vertices,
	    float lineWidth) {
	super(edge, vertices);

	this.lineWidth = lineWidth;
    }

    @Override
    public void paint(Graphics g) {
	Set<? extends GraphViewerVertex> vertices = getVertices();
	Point[] positions = new Point[vertices.size()];

	int i = 0;
	for (GraphViewerVertex vertex : vertices) {
	    positions[i++] = vertex.getMiddle();
	}

	GraphicsUtil graphicsUtil = new GraphicsUtil(g);
	graphicsUtil.setColor(Color.BLACK);
	graphicsUtil.setStroke(lineWidth);

	if (positions.length == 2) {
	    graphicsUtil.drawLine(positions[0], positions[1]);
	} else {
	    Point middle = new Point();

	    for (int j = 0; j < positions.length; j++) {
		middle.x += positions[j].x;
		middle.y += positions[j].y;
	    }

	    middle.x /= positions.length;
	    middle.y /= positions.length;

	    for (int j = 0; j < positions.length; j++) {
		graphicsUtil.drawLine(positions[j], middle);
	    }
	}
    }

    @Override
    public boolean intersects(Point p) {
	return false;
    }

}