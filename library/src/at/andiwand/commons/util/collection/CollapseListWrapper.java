package at.andiwand.commons.util.collection;

import java.util.List;

import at.andiwand.commons.util.primitive.IntegerReference;


public abstract class CollapseListWrapper<E> extends AbstractCollapseList<E> {
	
	private final List<OrderedPair<E, IntegerReference>> list;
	
	private int size;
	
	public CollapseListWrapper(List<OrderedPair<E, IntegerReference>> list) {
		this.list = list;
	}
	
	@Override
	public void add(int index, E element, int count) {
		if (((index - 1) >= 0) && element.equals(get(index - 1))) {
			
		} else if (index < list.size() && element.equals(get(index))) {
			
		} else {
			
		}
		
		size += count;
	}
	
}