package at.andiwand.commons.util.collection;

public interface KeyGenerator<K, V> {
	
	public K generateKey(V value);
	
}