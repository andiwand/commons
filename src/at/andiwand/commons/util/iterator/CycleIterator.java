package at.andiwand.commons.util.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CycleIterator<E> implements Iterator<E> {
	
	private final Collection<? extends E> collection;
	private Iterator<? extends E> iterator;
	
	public CycleIterator(Collection<? extends E> collection) {
		this.collection = collection;
		iterator = collection.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return !collection.isEmpty();
	}
	
	@Override
	public E next() {
		if (collection.isEmpty()) throw new NoSuchElementException();
		if (!iterator.hasNext()) iterator = collection.iterator();
		return iterator.next();
	}
	
	@Override
	public void remove() {
		if (collection.isEmpty()) throw new IllegalStateException();
		iterator.remove();
	}
	
}