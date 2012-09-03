package at.andiwand.commons.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


// TODO: make use of Vertex2d
public abstract class GraphViewerVertex {
	
	private GraphViewer viewer;
	
	private final Object vertex;
	private Point position = new Point();
	
	public GraphViewerVertex(Object vertex) {
		this.vertex = vertex;
	}
	
	public GraphViewer getViewer() {
		return viewer;
	}
	
	public Object getVertex() {
		return vertex;
	}
	
	public Point getPosition() {
		return new Point(position);
	}
	
	public Point getMiddle() {
		Rectangle bounds = getBounds();
		return new Point((int) bounds.getCenterX(), (int) bounds.getCenterY());
	}
	
	public abstract Dimension getSize();
	
	public Rectangle getBounds() {
		return new Rectangle(getPosition(), getSize());
	}
	
	protected void setViewer(GraphViewer viewer) {
		this.viewer = viewer;
	}
	
	public void setPosition(Point position) {
		this.position = new Point(position);
	}
	
	public abstract void paint(Graphics g);
	
	public boolean intersects(Point p) {
		return getBounds().contains(p);
	}
	
	public final void revalidate() {
		if (viewer == null) return;
		viewer.revalidateVertex(this);
	}
	
	protected final void fireRepaint() {
		if (viewer == null) return;
		viewer.repaint();
	}
	
}