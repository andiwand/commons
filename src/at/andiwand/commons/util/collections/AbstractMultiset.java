package at.andiwand.commons.util.collections;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;


public abstract class AbstractMultiset<E> extends AbstractSet<E> implements
		Multiset<E> {
	
	private class ElementSet extends AbstractSet<E> {
		@Override
		public Iterator<E> iterator() {
			return elementIterator();
		}
		
		@Override
		public int size() {
			return elementCount();
		}
		
		@Override
		public boolean contains(Object o) {
			return AbstractMultiset.this.contains(o);
		}
		
		@Override
		public boolean remove(Object o) {
			return AbstractMultiset.this.remove(o);
		}
		
		@Override
		public void clear() {
			AbstractMultiset.this.clear();
		}
	}
	
	private ElementSet elementSet;
	
	@Override
	public int hashCode() {
		int result = 0;
		
		for (E e : this) {
			if (e != null) result += e.hashCode();
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		
		if (!(o instanceof Multiset)) return false;
		Multiset<?> other = (Multiset<?>) o;
		
		if (size() != other.size()) return false;
		if (elementCount() != other.elementCount()) return false;
		
		for (E e : this) {
			if (!contains(e)) return false;
			if (elementCount(e) != other.elementCount(e)) return false;
		}
		
		return true;
	}
	
	@Override
	public boolean contains(Object o, int c) {
		if (!contains(o)) return false;
		if (elementCount(o) < c) return false;
		return true;
	}
	
	@Override
	public boolean containsExactly(Object o, int c) {
		if (!contains(o)) return false;
		if (elementCount(o) != c) return false;
		return true;
	}
	
	@Override
	public boolean add(E e) {
		return add(e, 1);
	}
	
	@Override
	public boolean remove(Object o) {
		return remove(o, 1);
	}
	
	@Override
	public boolean removeAll(Object o) {
		return remove(o, elementCount(o));
	}
	
	@Override
	public Set<E> elementSet() {
		return (elementSet == null) ? (elementSet = new ElementSet())
				: elementSet;
	}
	
}