package at.andiwand.commons.util.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


// TODO: handle size > Integer.MAX_VALUE
public class HashMultiset<E> extends AbstractMultiset<E> {
	
	private class MultisetIterator implements Iterator<E> {
		private final Iterator<Entry<E, Integer>> iterator = map.entrySet()
				.iterator();
		private Entry<E, Integer> entry;
		private int count;
		private boolean canRemove;
		
		@Override
		public boolean hasNext() {
			return (count > 0) || iterator.hasNext();
		}
		
		@Override
		public E next() {
			if (count <= 0) {
				entry = iterator.next();
				if (entry == null) {
					canRemove = false;
					return null;
				}
				
				count = entry.getValue();
			}
			
			count--;
			canRemove = true;
			return entry.getKey();
		}
		
		@Override
		public void remove() {
			if (!canRemove) throw new IllegalStateException();
			
			int count = entry.getValue();
			if (count <= 1) iterator.remove();
			else entry.setValue(count - 1);
			HashMultiset.this.size--;
			
			canRemove = false;
		}
	}
	
	private class ElementIterator implements Iterator<E> {
		private final Iterator<Entry<E, Integer>> iterator = map.entrySet()
				.iterator();
		private Entry<E, Integer> entry;
		private boolean canRemove;
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
		
		@Override
		public E next() {
			entry = iterator.next();
			canRemove = (entry != null);
			if (!canRemove) return null;
			return entry.getKey();
		}
		
		@Override
		public void remove() {
			if (!canRemove) throw new IllegalStateException();
			
			iterator.remove();
			HashMultiset.this.size -= entry.getValue();
			
			canRemove = false;
		}
	}
	
	private final Map<E, Integer> map;
	private int size;
	
	public HashMultiset() {
		map = new HashMap<E, Integer>();
	}
	
	public HashMultiset(int initialCapacity) {
		map = new HashMap<E, Integer>(initialCapacity);
	}
	
	public HashMultiset(int initialCapacity, float loadFactor) {
		map = new HashMap<E, Integer>(initialCapacity, loadFactor);
	}
	
	public HashMultiset(Collection<? extends E> c) {
		map = new HashMap<E, Integer>(Math.max((int) (c.size() / .75f) + 1, 16));
		addAll(c);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof HashMultiset)) return super.equals(o);
		HashMultiset<?> other = (HashMultiset<?>) o;
		
		if (size != other.size) return false;
		return map.equals(other.map);
	}
	
	@Override
	public int elementCount(Object o) {
		Integer i = map.get(o);
		return (i == null) ? 0 : i;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new MultisetIterator();
	}
	
	@Override
	public Iterator<E> elementIterator() {
		return new ElementIterator();
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public int elementCount() {
		return map.size();
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	@Override
	public boolean contains(Object o, int c) {
		Integer i = map.get(o);
		return (i == null) ? false : (i >= c);
	}
	
	@Override
	public boolean containsExactly(Object o, int c) {
		Integer i = map.get(o);
		return (i == null) ? false : (i != c);
	}
	
	@Override
	public boolean add(E e, int c) {
		if (c <= 0) return false;
		
		int i = elementCount(e);
		map.put(e, i + c);
		size += c;
		return true;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(Object o, int c) {
		if (c <= 0) return false;
		
		int i = elementCount(o);
		if (i <= 0) return false;
		
		if (i <= c) map.remove(o);
		else map.put((E) o, i - c);
		
		size -= Math.min(i, c);
		return true;
	}
	
	@Override
	public boolean removeAll(Object o) {
		int i = elementCount(o);
		if (i <= 0) return false;
		
		map.remove(o);
		size -= i;
		return true;
	}
	
	@Override
	public void clear() {
		map.clear();
		size = 0;
	}
	
}