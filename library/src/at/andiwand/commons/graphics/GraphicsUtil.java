package at.andiwand.commons.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GraphicsUtil {

    private final Graphics g;

    public GraphicsUtil(Graphics g) {
	this.g = g.create();
    }

    public Color getColor() {
	return g.getColor();
    }

    public Stroke getStroke() {
	return ((Graphics2D) g).getStroke();
    }

    public void setColor(Color color) {
	g.setColor(color);
    }

    public void setStroke(Stroke stroke) {
	((Graphics2D) g).setStroke(stroke);
    }

    public void setStroke(float width) {
	Stroke stroke = new BasicStroke(width);
	setStroke(stroke);
    }

    public void setPaintMode() {
	g.setPaintMode();
    }

    public void setXORMode(Color color) {
	g.setXORMode(color);
    }

    public void fillCircle(Point p, int radius2) {
	g.fillOval(p.x, p.y, radius2, radius2);
    }

    public void fillCircle(Point center, double radius) {
	int size = (int) (radius * 2);
	g.fillOval((int) (center.getX() - radius),
		(int) (center.getY() - radius), size, size);
    }

    public void drawLine(Point a, Point b) {
	g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(),
		(int) b.getY());
    }

    public void drawCircle(Point p, int radius2) {
	g.drawOval(p.x, p.y, radius2, radius2);
    }

    public void drawCircle(Point center, double radius) {
	int size = (int) (radius * 2);
	g.drawOval((int) (center.getX() - radius),
		(int) (center.getY() - radius), size, size);
    }

    public void drawRectangle(Rectangle r) {
	g.drawRect(r.x, r.y, r.width, r.height);
    }

    public void drawPolygon(List<Point> vertices) {
	int[] xPoints = new int[vertices.size()];
	int[] yPoints = new int[vertices.size()];

	int i = 0;
	for (Point vertex : vertices) {
	    xPoints[i] = vertex.x;
	    yPoints[i] = vertex.y;

	    i++;
	}

	g.drawPolygon(xPoints, yPoints, vertices.size());
    }

    public void fillRectangle(Rectangle r) {
	g.fillRect(r.x, r.y, r.width, r.height);
    }

    public void fillPolygon(List<Point> vertices) {
	int[] xPoints = new int[vertices.size()];
	int[] yPoints = new int[vertices.size()];

	int i = 0;
	for (Point vertex : vertices) {
	    xPoints[i] = vertex.x;
	    yPoints[i] = vertex.y;

	    i++;
	}

	g.fillPolygon(xPoints, yPoints, vertices.size());
    }

    public void drawString(Point start, String string) {
	g.drawString(string, (int) start.getX(), (int) start.getY());
    }

    public void drawXCenterString(Point xCenter, String string) {
	FontMetrics fontMetrics = g.getFontMetrics();
	Rectangle2D bounds = fontMetrics.getStringBounds(string, g);

	g.drawString(string, (int) (xCenter.getX() - bounds.getWidth() / 2),
		(int) xCenter.getY());
    }

}