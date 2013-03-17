package at.andiwand.commons.util.array;

import java.lang.reflect.Array;


public abstract class AbstractGrowableArray<T> implements GrowableArray<T> {
	
	private final GrowableArrayStrategy strategy;
	
	public AbstractGrowableArray(GrowableArrayStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void getArray(T array, int offset) {
		getArray(array, offset, Array.getLength(array));
	}
	
	@Override
	public void getArray(GrowableArray<? extends T> array, int offset) {
		getArray(array, offset, array.getLength());
	}
	
	@Override
	public void setArray(T array, int offset) {
		setArray(array, offset, Array.getLength(array));
	}
	
	@Override
	public void setArray(GrowableArray<? extends T> array, int offset, int len) {
		setArray(array, offset, array.getLength());
	}
	
	@Override
	public GrowableArrayStrategy getStrategy() {
		return strategy;
	}
	
}