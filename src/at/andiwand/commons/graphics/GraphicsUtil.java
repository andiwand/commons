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

import at.andiwand.commons.math.RectangleD;
import at.andiwand.commons.math.geometry.GeometryCircle2D;
import at.andiwand.commons.math.geometry.GeometryLine2D;
import at.andiwand.commons.math.vector.Vector2d;

public class GraphicsUtil {

    private final Graphics g;
    private final Graphics2D g2;

    public GraphicsUtil(Graphics g) {
	this.g = g;
	this.g2 = (g instanceof Graphics2D) ? (Graphics2D) g : null;
    }

    public Color getColor() {
	return g.getColor();
    }

    public Stroke getStroke() {
	return g2.getStroke();
    }

    public void setColor(Color color) {
	g.setColor(color);
    }

    public void setStroke(Stroke stroke) {
	g2.setStroke(stroke);
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
	g.fillOval((int) (center.x - radius), (int) (center.y - radius), size,
		size);
    }

    public void fillCircle(Vector2d p, int radius2) {
	g.fillOval((int) p.getX(), (int) p.getY(), radius2, radius2);
    }

    public void fillCircle(Vector2d center, double radius) {
	int size = (int) (radius * 2);
	g.fillOval((int) (center.getX() - radius),
		(int) (center.getY() - radius), size, size);
    }

    public void fillCircle(GeometryCircle2D circle) {
	fillCircle(circle.getCenter(), circle.getRadius());
    }

    public void drawLine(Point a, Point b) {
	g.drawLine(a.x, a.y, b.x, b.y);
    }

    public void drawLine(Vector2d a, Vector2d b) {
	g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(),
		(int) b.getY());
    }

    public void drawLine(GeometryLine2D line) {
	drawLine(line.getPointA(), line.getPointB());
    }

    public void drawLines(List<GeometryLine2D> lines) {
	for (GeometryLine2D line : lines) {
	    drawLine(line);
	}
    }

    public void drawCircle(Point p, int radius2) {
	g.drawOval(p.x, p.y, radius2, radius2);
    }

    public void drawCircle(Point center, double radius) {
	int size = (int) (radius * 2);
	g.drawOval((int) (center.getX() - radius),
		(int) (center.getY() - radius), size, size);
    }

    public void drawCircle(Vector2d p, int radius2) {
	g.drawOval((int) p.getX(), (int) p.getY(), radius2, radius2);
    }

    public void drawCircle(Vector2d center, double radius) {
	int size = (int) (radius * 2);
	g.drawOval((int) (center.getX() - radius),
		(int) (center.getY() - radius), size, size);
    }

    public void drawCircle(GeometryCircle2D circle) {
	drawCircle(circle.getCenter(), circle.getRadius());
    }

    public void drawRectangle(Rectangle r) {
	g.drawRect(r.x, r.y, r.width, r.height);
    }

    public void drawRectangle(RectangleD r) {
	g.drawRect((int) r.getLeft(), (int) r.getTop(), (int) r.getWidth(),
		(int) r.getHeight());
    }

    public void drawPolygon(List<Vector2d> vertices) {
	int[] xPoints = new int[vertices.size()];
	int[] yPoints = new int[vertices.size()];
	int i = 0;

	for (Vector2d vertex : vertices) {
	    xPoints[i] = (int) vertex.getX();
	    yPoints[i] = (int) vertex.getY();
	    i++;
	}

	g.drawPolygon(xPoints, yPoints, vertices.size());
    }

    public void fillRectangle(Rectangle r) {
	g.fillRect(r.x, r.y, r.width, r.height);
    }

    public void fillRectangle(RectangleD r) {
	g.fillRect((int) r.getLeft(), (int) r.getTop(), (int) r.getWidth(),
		(int) r.getHeight());
    }

    public void fillPolygon(List<Vector2d> vertices) {
	int[] xPoints = new int[vertices.size()];
	int[] yPoints = new int[vertices.size()];
	int i = 0;

	for (Vector2d vertex : vertices) {
	    xPoints[i] = (int) vertex.getX();
	    yPoints[i] = (int) vertex.getY();
	    i++;
	}

	g.fillPolygon(xPoints, yPoints, vertices.size());
    }

    public void drawString(Point start, String string) {
	g.drawString(string, start.x, start.y);
    }

    public void drawString(Vector2d start, String string) {
	g.drawString(string, (int) start.getX(), (int) start.getY());
    }

    public void drawXCenterString(Point xCenter, String string) {
	FontMetrics fontMetrics = g.getFontMetrics();
	Rectangle2D bounds = fontMetrics.getStringBounds(string, g);

	g.drawString(string, (int) (xCenter.x - bounds.getWidth() / 2),
		xCenter.y);
    }

    public void drawXCenterString(Vector2d xCenter, String string) {
	FontMetrics fontMetrics = g.getFontMetrics();
	Rectangle2D bounds = fontMetrics.getStringBounds(string, g);

	g.drawString(string, (int) (xCenter.getX() - bounds.getWidth() / 2),
		(int) xCenter.getY());
    }

}