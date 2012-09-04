package at.andiwand.commons.util.collections;

public interface KeyGenerator<K, V> {
	
	public K generateKey(V value);
	
}