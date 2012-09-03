package at.andiwand.commons.util.comparator;

import java.util.Comparator;


public class ReverseComparator<T> implements Comparator<T> {
	
	private final Comparator<? super T> comparator;
	
	public ReverseComparator(Comparator<? super T> comparator) {
		this.comparator = (Comparator<? super T>) comparator;
	}
	
	@Override
	public int compare(T o1, T o2) {
		return -comparator.compare(o1, o2);
	}
	
}