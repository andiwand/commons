package at.andiwand.commons.util.iterator;

import java.lang.reflect.Array;


public class ArrayIterator<E> extends AbstractIterator<E> {
	
	private final Object array;
	private final int length;
	private int index;
	
	public ArrayIterator(Object array) {
		this.array = array;
		this.length = Array.getLength(array);
	}
	
	@Override
	public boolean hasNext() {
		return index < length;
	}
	
	@SuppressWarnings("unchecked")
	public E next() {
		return (E) Array.get(array, index++);
	}
	
}