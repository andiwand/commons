package at.andiwand.common.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


// TODO: make use of Vertex2d
public class DefaultGraphViewerVertex extends GraphViewerVertex {
	
	public static final double DEFAULT_RADIUS = 15;
	
	private final int radius2;
	
	public DefaultGraphViewerVertex(Object vertex) {
		this(vertex, DEFAULT_RADIUS);
	}
	
	public DefaultGraphViewerVertex(Object vertex, double radius) {
		super(vertex);
		
		radius2 = (int) (radius * 2);
	}
	
	@Override
	public Dimension getSize() {
		return new Dimension(radius2, radius2);
	}
	
	@Override
	public void paint(Graphics g) {
		Point p = getPosition();
		g.setColor(Color.BLACK);
		g.fillOval(p.x, p.y, radius2, radius2);
	}
	
	@Override
	public boolean intersects(Point p) {
		double radius = (double) radius2 / 2;
		double distance = getMiddle().distance(p);
		return distance <= radius;
	}
	
}