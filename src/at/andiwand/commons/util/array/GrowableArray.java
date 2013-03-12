package at.andiwand.commons.util.array;

public interface GrowableArray<T> {
	
	public void set(Object element, int index);
	
	public void setArray(T array, int offset);
	
	public void setArray(T array, int offset, int len);
	
	public void setArray(GrowableArray<? extends T> array, int offset);
	
	public void setArray(GrowableArray<? extends T> array, int offset, int len);
	
	public Object get(int index);
	
	public void getArray(T array, int offset);
	
	public void getArray(T array, int offset, int len);
	
	public void getArray(GrowableArray<? extends T> array, int offset);
	
	public void getArray(GrowableArray<? extends T> array, int offset, int len);
	
	public int getLength();
	
	public GrowableArrayStrategy getStrategy();
	
}