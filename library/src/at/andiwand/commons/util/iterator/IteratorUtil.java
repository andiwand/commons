package at.andiwand.commons.util.iterator;

import java.util.Collection;
import java.util.Iterator;


public class IteratorUtil {
	
	public static <E> void toArray(Iterator<E> iterator, E[] array) {
		for (int i = 0; iterator.hasNext(); i++)
			array[i] = iterator.next();
	}
	
	public static <E> void toArray(Iterator<E> iterator, E[] array, int off) {
		for (int i = off; iterator.hasNext(); i++)
			array[i] = iterator.next();
	}
	
	public static <E> void toArray(Iterator<E> iterator, E[] array, int off,
			int count) {
		int end = off + count;
		for (int i = off; i < end; i++)
			array[i] = iterator.next();
	}
	
	public static <E> void toCollection(Iterator<E> iterator,
			Collection<? super E> c) {
		while (iterator.hasNext())
			c.add(iterator.next());
	}
	
	public static <E> void toCollection(Iterator<E> iterator,
			Collection<? super E> c, int count) {
		for (int i = 0; i < count; i++)
			c.add(iterator.next());
	}
	
	public static <E> E findObject(Iterator<E> iterator, Object matcher) {
		E element;
		while (iterator.hasNext()) {
			element = iterator.next();
			if (matcher.equals(element)) return element;
		}
		
		return null;
	}
	
	public static <E> E findObject(Iterator<E> iterator, Object matcher,
			int limit) {
		if (limit < 0) throw new IllegalArgumentException("limit < 0");
		
		E element;
		for (int i = 0; (i < limit) && iterator.hasNext(); i++) {
			element = iterator.next();
			if (matcher.equals(element)) return element;
		}
		
		return null;
	}
	
	public static int count(Iterator<?> iterator) {
		int result = 0;
		while (iterator.hasNext())
			result++;
		return result;
	}
	
	private IteratorUtil() {}
	
}