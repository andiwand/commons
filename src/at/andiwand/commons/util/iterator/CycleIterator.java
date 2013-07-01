package at.andiwand.commons.util.iterator;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CycleIterator<E> extends SimpleDelegationIterator<E> {

    private final Collection<? extends E> collection;

    public CycleIterator(Collection<? extends E> collection) {
	super(collection.iterator());

	this.collection = collection;
    }

    @Override
    public boolean hasNext() {
	return !collection.isEmpty();
    }

    @Override
    public E next() {
	if (collection.isEmpty())
	    throw new NoSuchElementException();
	if (!iterator.hasNext())
	    iterator = collection.iterator();
	return iterator.next();
    }

    @Override
    public void remove() {
	if (collection.isEmpty())
	    throw new IllegalStateException();
	iterator.remove();
    }

}