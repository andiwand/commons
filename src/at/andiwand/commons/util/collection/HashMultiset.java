package at.andiwand.commons.util.collection;

import java.util.Collection;
import java.util.HashMap;

import at.andiwand.commons.util.primitive.IntegerReference;

public class HashMultiset<E> extends MultisetWrapper<E> {

    public HashMultiset() {
	super(new HashMap<E, IntegerReference>());
    }

    public HashMultiset(int initialCapacity) {
	super(new HashMap<E, IntegerReference>(initialCapacity));
    }

    public HashMultiset(int initialCapacity, float loadFactor) {
	super(new HashMap<E, IntegerReference>(initialCapacity, loadFactor));
    }

    public HashMultiset(Collection<? extends E> c) {
	this(c.size());
	addAll(c);
    }

}