package at.andiwand.commons.swing.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


public class GridGraphLayout extends AbstractGraphLayout {
	
	public static final int DEFAULT_HGAP = 20;
	public static final int DEFAULT_VGAP = 20;
	
	private int hgap = DEFAULT_HGAP;
	private int vgap = DEFAULT_VGAP;
	
	public GridGraphLayout(GraphViewer viewer) {
		super(viewer);
	}
	
	@Override
	public void moveVertex(GraphViewerVertex vertex, Point p) {
		Point oldPosition = vertex.getPosition();
		int dx = p.x - oldPosition.x;
		int dy = p.y - oldPosition.y;
		Point middle = vertex.getMiddle();
		int mdx = oldPosition.x - middle.x;
		int mdy = oldPosition.y - middle.y;
		middle.x += dx;
		middle.y += dy;
		int x = ((int) Math.round((double) middle.x / hgap)) * hgap + mdx;
		int y = ((int) Math.round((double) middle.y / vgap)) * vgap + mdy;
		
		super.moveVertex(vertex, new Point(x, y));
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension dimension = getSize();
		
		g.setColor(Color.LIGHT_GRAY);
		
		for (int y = vgap; y < dimension.height; y += vgap) {
			g.drawLine(0, y, dimension.width, y);
		}
		
		for (int x = hgap; x < dimension.width; x += hgap) {
			g.drawLine(x, 0, x, dimension.height);
		}
	}
	
}