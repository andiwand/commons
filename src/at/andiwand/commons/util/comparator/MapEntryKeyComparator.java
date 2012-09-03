package at.andiwand.commons.util.comparator;

import java.util.Comparator;
import java.util.Map.Entry;


public class MapEntryKeyComparator<T> implements Comparator<Entry<T, ?>> {
	
	private final Comparator<? super T> keyComparator;
	
	public MapEntryKeyComparator() {
		this(null);
	}
	
	public MapEntryKeyComparator(Comparator<? super T> keyComparator) {
		this.keyComparator = keyComparator;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int compare(Entry<T, ?> o1, Entry<T, ?> o2) {
		if (keyComparator != null)
			return keyComparator.compare(o1.getKey(), o2.getKey());
		Comparable<? super T> co1 = (Comparable<? super T>) o1.getKey();
		return co1.compareTo(o2.getKey());
	}
	
}