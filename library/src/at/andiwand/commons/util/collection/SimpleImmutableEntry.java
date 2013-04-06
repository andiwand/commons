package at.andiwand.commons.util.collection;

import java.util.Map.Entry;


public class SimpleImmutableEntry<K, V> extends SimpleEntry<K, V> {
	
	public SimpleImmutableEntry(K key, V value) {
		super(key, value);
	}
	
	public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
		super(entry);
	}
	
	@Override
	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}
	
}