package at.andiwand.commons.util.iterator;

public abstract class SimpleDelegationIterable<T> extends
	DelegationIterable<T, T> {

    public SimpleDelegationIterable(Iterable<? extends T> iterable) {
	super(iterable);
    }

}