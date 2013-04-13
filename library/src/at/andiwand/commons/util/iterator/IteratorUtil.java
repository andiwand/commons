package at.andiwand.commons.util.iterator;

import java.util.Iterator;


public class IteratorUtil {
	
	public <E> void toArray(Iterator<E> iterator, E[] array) {
		for (int i = 0; iterator.hasNext(); i++)
			array[i] = iterator.next();
	}
	
	public <E> void toArray(Iterator<E> iterator, E[] array, int off) {
		for (int i = off; iterator.hasNext(); i++)
			array[i] = iterator.next();
	}
	
	public <E> void toArray(Iterator<E> iterator, E[] array, int off, int len) {
		int end = off + len;
		for (int i = off; i < end; i++)
			array[i] = iterator.next();
	}
	
	private IteratorUtil() {}
	
}