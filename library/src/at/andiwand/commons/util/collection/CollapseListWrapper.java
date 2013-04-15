package at.andiwand.commons.util.collection;

import java.util.List;
import java.util.ListIterator;

import at.andiwand.commons.util.object.ObjectUtil;
import at.andiwand.commons.util.primitive.IntegerReference;


public abstract class CollapseListWrapper<E> extends AbstractCollapseList<E> {
	
	private final List<OrderedPair<E, IntegerReference>> list;
	
	private int size;
	
	public CollapseListWrapper(List<OrderedPair<E, IntegerReference>> list) {
		this.list = list;
	}
	
	@Override
	public void add(int index, E element, int count) {
		if (index < 0) throw new IndexOutOfBoundsException("index < 0");
		if (index >= size)
			throw new IndexOutOfBoundsException("index >= size");
		
		int previousIndex = index - 1;
		ListIterator<OrderedPair<E, IntegerReference>> iterator = list
				.listIterator(previousIndex);
		OrderedPair<E, IntegerReference> listElement;
		
		if (ObjectUtil.equals(element, (listElement = iterator.next())
				.getElement1())
				|| (iterator.hasNext() && ObjectUtil.equals(element,
						(listElement = iterator.next()).getElement1()))) {
			listElement.getElement2().value += count;
		}
		
		size += count;
	}
	
}