package at.andiwand.commons.math;

public class RectangleI {
	
	private final int left;
	private final int top;
	private final int right;
	private final int bottom;
	
	public RectangleI(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottom;
		result = prime * result + left;
		result = prime * result + right;
		result = prime * result + top;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RectangleI other = (RectangleI) obj;
		if (bottom != other.bottom) return false;
		if (left != other.left) return false;
		if (right != other.right) return false;
		if (top != other.top) return false;
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
	
	public int getLeft() {
		return left;
	}
	
	public int getTop() {
		return top;
	}
	
	public int getRight() {
		return right;
	}
	
	public int getBottom() {
		return bottom;
	}
	
}