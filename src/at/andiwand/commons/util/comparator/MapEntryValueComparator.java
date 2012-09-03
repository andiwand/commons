package at.andiwand.commons.util.comparator;

import java.util.Comparator;
import java.util.Map.Entry;


public class MapEntryValueComparator<T> implements Comparator<Entry<?, T>> {
	
	private final Comparator<? super T> valueComparator;
	
	public MapEntryValueComparator() {
		this(null);
	}
	
	public MapEntryValueComparator(Comparator<? super T> valueComparator) {
		this.valueComparator = valueComparator;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int compare(Entry<?, T> o1, Entry<?, T> o2) {
		if (valueComparator != null)
			return valueComparator.compare(o1.getValue(), o2.getValue());
		Comparable<? super T> co1 = (Comparable<? super T>) o1.getValue();
		return co1.compareTo(o2.getValue());
	}
	
}