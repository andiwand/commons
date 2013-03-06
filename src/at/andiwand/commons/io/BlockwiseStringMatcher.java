package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import at.andiwand.commons.util.collection.OrderedPair;
import at.andiwand.commons.util.string.CharSequenceArray;
import at.andiwand.commons.util.string.CharSequenceWraper;


public class BlockwiseStringMatcher<V> extends AbstractMap<String, V> {
	
	private static class EntryWrapper<V> implements Entry<String, V> {
		private Entry<CharSequenceWraper, OrderedPair<String, V>> entry;
		
		public EntryWrapper(
				Entry<CharSequenceWraper, OrderedPair<String, V>> entry) {
			this.entry = entry;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (obj == this) return true;
			
			if (!(obj instanceof EntryWrapper)) return false;
			EntryWrapper<?> other = (EntryWrapper<?>) obj;
			
			return entry.equals(other.entry);
		}
		
		@Override
		public int hashCode() {
			return entry.hashCode();
		}
		
		@Override
		public String getKey() {
			return entry.getKey().toString();
		}
		
		@Override
		public V getValue() {
			return entry.getValue().getElement2();
		}
		
		public V setValue(V value) {
			V result = entry.getValue().getElement2();
			entry.setValue(entry.getValue().setElement2(value));
			return result;
		}
	}
	
	private class EntrySetIterator implements Iterator<Entry<String, V>> {
		private final Iterator<Entry<CharSequenceWraper, OrderedPair<String, V>>> iterator = map
				.entrySet().iterator();
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
		
		@Override
		public Entry<String, V> next() {
			return new EntryWrapper<V>(iterator.next());
		}
		
		@Override
		public void remove() {
			iterator.remove();
		}
	}
	
	private class EntrySet extends AbstractSet<Entry<String, V>> {
		@Override
		public Iterator<Entry<String, V>> iterator() {
			return new EntrySetIterator();
		}
		
		@Override
		public boolean add(Entry<String, V> e) {
			BlockwiseStringMatcher.this.put(e.getKey(), e.getValue());
			return true;
		}
		
		@Override
		public boolean remove(Object o) {
			boolean result = BlockwiseStringMatcher.this.containsKey(o);
			if (result) BlockwiseStringMatcher.this.remove(o);
			return result;
		}
		
		@Override
		public boolean contains(Object o) {
			return BlockwiseStringMatcher.this.containsKey(o);
		}
		
		@Override
		public int size() {
			return map.size();
		}
	}
	
	private final Map<CharSequenceWraper, OrderedPair<String, V>> map = new HashMap<CharSequenceWraper, OrderedPair<String, V>>();
	
	private EntrySet entrySet;
	
	private int bufferSize;
	private char[] buffer;
	
	@Override
	public V put(String key, V value) {
		if (key == null) throw new NullPointerException();
		
		int len = key.length();
		if (len > bufferSize) {
			bufferSize = len;
			if (buffer != null) buffer = new char[bufferSize];
		}
		
		OrderedPair<String, V> result = map.put(new CharSequenceWraper(key),
				new OrderedPair<String, V>(key, value));
		if (result == null) return null;
		return result.getElement2();
	}
	
	@Override
	public V remove(Object key) {
		if (!(key instanceof String)) throw new ClassCastException();
		String tmp = (String) key;
		
		OrderedPair<String, V> result = map.remove(new CharSequenceWraper(tmp));
		if (result == null) return null;
		return result.getElement2();
	}
	
	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof String)) throw new ClassCastException();
		String tmp = (String) key;
		return map.containsKey(new CharSequenceWraper(tmp));
	}
	
	@Override
	public V get(Object key) {
		if (!(key instanceof String)) throw new ClassCastException();
		String tmp = (String) key;
		
		OrderedPair<String, V> result = get(tmp);
		if (result == null) return null;
		return result.getElement2();
	}
	
	private OrderedPair<String, V> get(CharSequence charSequence) {
		return map.get(new CharSequenceWraper(charSequence));
	}
	
	public OrderedPair<String, V> match(Reader in) throws IOException {
		if (buffer == null) buffer = new char[bufferSize];
		
		int read = CharStreamUtil.readForward(in, buffer);
		CharSequence charSequence = new CharSequenceArray(buffer, 0, read);
		
		return get(charSequence);
	}
	
	@Override
	public Set<Entry<String, V>> entrySet() {
		if (entrySet == null) entrySet = new EntrySet();
		return entrySet;
	}
	
}