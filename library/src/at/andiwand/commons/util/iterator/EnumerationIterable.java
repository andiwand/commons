package at.andiwand.commons.util.iterator;

import java.util.Enumeration;


public class EnumerationIterable<E> extends IterableIterator<E> {
	
	public EnumerationIterable(Enumeration<? extends E> enumeration) {
		super(new EnumerationIterator<E>(enumeration));
	}
	
}