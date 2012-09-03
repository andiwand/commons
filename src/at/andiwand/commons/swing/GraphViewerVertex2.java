package at.andiwand.commons.swing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


// TODO: make use of Vertex2d
public abstract class GraphViewerVertex2 {
	
	private GraphViewer viewer;
	
	private final Object vertex;
	private Point position;
	
	private boolean selected;
	
	public GraphViewerVertex2(Object vertex) {
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
	
	public abstract Rectangle getBounds();
	
	public boolean isSelected() {
		return selected;
	}
	
	protected void setViewer(GraphViewer viewer) {
		this.viewer = viewer;
	}
	
	public void setPosition(Point position) {
		this.position = new Point(position);
	}
	
	protected void setSelected(boolean selected) {
		this.selected = selected;
		fireRepaint();
	}
	
	public abstract void paint(Graphics g);
	
	public abstract boolean intersects(Point p);
	
	protected final void fireRepaint() {
		viewer.repaint();
	}
	
}