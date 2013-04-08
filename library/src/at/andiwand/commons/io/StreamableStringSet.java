package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;


public class StreamableStringSet extends AbstractSet<String> {
	
	private static final Object VALUE = new Object();
	
	private final StreamableStringMap<Object> map;
	
	public StreamableStringSet() {
		map = new StreamableStringMap<Object>();
	}
	
	public StreamableStringSet(int capacity) {
		map = new StreamableStringMap<Object>(capacity);
	}
	
	public StreamableStringSet(Collection<? extends String> c) {
		this(c.size());
		
		addAll(c);
	}
	
	public StreamableStringSet(StreamableStringMap<?> map) {
		this(map.size());
		
		addAll(map.keySet());
	}
	
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	@Override
	public int size() {
		return map.size();
	}
	
	@Override
	public Iterator<String> iterator() {
		return map.keySet().iterator();
	}
	
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	@Override
	public boolean add(String e) {
		return map.put(e, VALUE) == null;
	}
	
	@Override
	public boolean remove(Object o) {
		return map.remove(o) != null;
	}
	
	public String match(Reader in) throws IOException {
		return map.match(in).getElement1();
	}
	
	public void clearBuffer() {
		map.clearBuffer();
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
}