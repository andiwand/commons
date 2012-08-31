package at.andiwand.common.util.comparator;

import java.util.Comparator;


public class ReverseComparator implements Comparator<Object> {
	
	private final Comparator<Object> comparator;
	
	@SuppressWarnings("unchecked")
	public ReverseComparator(Comparator<?> comparator) {
		this.comparator = (Comparator<Object>) comparator;
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return -comparator.compare(o1, o2);
	}
	
}