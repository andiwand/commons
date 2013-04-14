package at.andiwand.commons.util.iterator;

import java.util.Collection;
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
	
	public <E> void toCollection(Iterator<E> iterator, Collection<? super E> c) {
		while (iterator.hasNext())
			c.add(iterator.next());
	}
	
	public <E> void toCollection(Iterator<E> iterator, Collection<? super E> c,
			int len) {
		for (int i = 0; i < len; i++)
			c.add(iterator.next());
	}
	
	private IteratorUtil() {}
	
}