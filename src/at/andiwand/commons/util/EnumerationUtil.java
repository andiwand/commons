package at.andiwand.commons.util;

import java.util.Enumeration;
import java.util.Iterator;

import at.andiwand.commons.util.iterator.EnumerationIterable;
import at.andiwand.commons.util.iterator.EnumerationIterator;


public class EnumerationUtil {
	
	public static <E> Iterator<E> iterator(Enumeration<? extends E> enumeration) {
		return new EnumerationIterator<E>(enumeration);
	}
	
	public static <E> Iterable<E> iterable(Enumeration<? extends E> enumeration) {
		return new EnumerationIterable<E>(enumeration);
	}
	
	private EnumerationUtil() {}
	
}