package at.andiwand.commons.util.iterator;

import java.util.Iterator;


public abstract class DelegationIterator<E> extends AbstractIterator<E> {
	
	protected Iterator<? extends E> iterator;
	
	public DelegationIterator(Iterator<? extends E> iterator) {
		this.iterator = iterator;
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	@Override
	public E next() {
		return iterator.next();
	}
	
	public void remove() {
		iterator.remove();
	}
	
}