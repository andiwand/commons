package at.andiwand.commons.util.iterator;

import java.util.Enumeration;


public class IterableEnumeration<E> extends IterableIterator<E> {
	
	public IterableEnumeration(Enumeration<? extends E> enumeration) {
		super(new EnumerationIterator<E>(enumeration));
	}
	
}