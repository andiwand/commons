package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractSet;
import java.util.Iterator;

import at.andiwand.commons.util.collection.OrderedPair;


public class StreamableStringSet extends AbstractSet<String> {
	
	private StreamableStringMap<Object> map = new StreamableStringMap<Object>();
	
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	@Override
	public boolean add(String e) {
		boolean result = map.containsKey(e);
		map.put(e, null);
		return result;
	}
	
	@Override
	public boolean remove(Object o) {
		if (!contains(o)) return false;
		map.remove(o);
		return true;
	}
	
	@Override
	public Iterator<String> iterator() {
		return map.keySet().iterator();
	}
	
	@Override
	public int size() {
		return map.size();
	}
	
	public String match(Reader in) throws IOException {
		OrderedPair<String, Object> match = map.match(in);
		return (match == null) ? null : match.getElement1();
	}
	
}