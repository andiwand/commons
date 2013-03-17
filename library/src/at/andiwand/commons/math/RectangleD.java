package at.andiwand.commons.math;

import at.andiwand.commons.math.vector.Vector2d;


public class RectangleD {
	
	private final double left;
	private final double top;
	private final double right;
	private final double bottom;
	
	public RectangleD(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long tmp;
		
		tmp = Double.doubleToLongBits(bottom);
		result = prime * result + (int) (tmp ^ (tmp >>> 32));
		tmp = Double.doubleToLongBits(left);
		result = prime * result + (int) (tmp ^ (tmp >>> 32));
		tmp = Double.doubleToLongBits(right);
		result = prime * result + (int) (tmp ^ (tmp >>> 32));
		tmp = Double.doubleToLongBits(top);
		result = prime * result + (int) (tmp ^ (tmp >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		RectangleD other = (RectangleD) obj;
		
		if (Double.doubleToLongBits(bottom) != Double
				.doubleToLongBits(other.bottom)) return false;
		if (Double.doubleToLongBits(left) != Double
				.doubleToLongBits(other.left)) return false;
		if (Double.doubleToLongBits(right) != Double
				.doubleToLongBits(other.right)) return false;
		if (Double.doubleToLongBits(top) != Double.doubleToLongBits(other.top))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("RectangeI [left=");
		builder.append(left);
		builder.append(", top=");
		builder.append(top);
		builder.append(", right=");
		builder.append(right);
		builder.append(", bottom=");
		builder.append(bottom);
		builder.append("]");
		
		return builder.toString();
	}
	
	public double getLeft() {
		return left;
	}
	
	public double getTop() {
		return top;
	}
	
	public double getRight() {
		return right;
	}
	
	public double getBottom() {
		return bottom;
	}
	
	public Vector2d getCenter() {
		return new Vector2d((left + right) / 2d, (top + bottom) / 2d);
	}
	
	public Vector2d getLeftTop() {
		return new Vector2d(left, top);
	}
	
	public Vector2d getRightTop() {
		return new Vector2d(right, top);
	}
	
	public Vector2d getRightBottom() {
		return new Vector2d(right, bottom);
	}
	
	public Vector2d getLeftBottom() {
		return new Vector2d(left, bottom);
	}
	
	public double getWidth() {
		return right - left;
	}
	
	public double getHeight() {
		return bottom - top;
	}
	
	public Vector2d getSize() {
		return new Vector2d(getWidth(), getHeight());
	}
	
}