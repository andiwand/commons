package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import at.andiwand.commons.util.CharArrayCharSequence;
import at.andiwand.commons.util.CharSequenceWraper;
import at.andiwand.commons.util.collections.OrderedPair;


public class BlockwiseStringMatcher<V> extends AbstractMap<String, V> {
	
	private final Map<CharSequenceWraper, OrderedPair<String, V>> map = new HashMap<CharSequenceWraper, OrderedPair<String, V>>();
	
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
		
		int read = in.read(buffer);
		CharSequence charSequence = new CharArrayCharSequence(buffer, 0, read);
		
		return get(charSequence);
	}
	
	// TODO: implement
	@Override
	public Set<Entry<String, V>> entrySet() {
		throw new UnsupportedOperationException();
	}
	
}