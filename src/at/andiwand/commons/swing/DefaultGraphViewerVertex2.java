package at.andiwand.commons.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


// TODO: make use of Vertex2d
public class DefaultGraphViewerVertex2 extends GraphViewerVertex {
	
	public static final double DEFAULT_RADIUS = 15;
	
	private final int radius2;
	
	public DefaultGraphViewerVertex2(Object vertex) {
		this(vertex, DEFAULT_RADIUS);
	}
	
	public DefaultGraphViewerVertex2(Object vertex, double radius) {
		super(vertex);
		
		radius2 = (int) (radius * 2);
	}
	
	@Override
	public Rectangle getBounds() {
		Dimension d = new Dimension(radius2, radius2);
		return new Rectangle(getPosition(), d);
	}
	
	@Override
	public void paint(Graphics g) {
		Point p = getPosition();
		if (isSelected()) g.setColor(Color.GRAY);
		else g.setColor(Color.BLACK);
		g.fillOval(p.x, p.y, radius2, radius2);
	}
	
	@Override
	public boolean intersects(Point p) {
		double radius = (double) radius2 / 2;
		double distance = getMiddle().distance(p);
		return distance <= radius;
	}
	
}