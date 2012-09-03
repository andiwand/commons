package at.andiwand.commons.util.collections;

import java.util.Iterator;
import java.util.Set;


public interface Multiset<E> extends Set<E> {
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public int size();
	
	public int elementCount();
	
	public int elementCount(Object o);
	
	public boolean contains(Object o, int c);
	
	public boolean containsExactly(Object o, int c);
	
	public boolean add(E o, int c);
	
	public boolean remove(Object o, int c);
	
	public boolean removeAll(Object o);
	
	@Override
	public Iterator<E> iterator();
	
	public Iterator<E> elementIterator();
	
	public Set<E> elementSet();
	
}