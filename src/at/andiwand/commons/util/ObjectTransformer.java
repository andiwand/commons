package at.andiwand.commons.util;

public interface ObjectTransformer<T1, T2> {
	
	public T2 transform(T1 o1);
	
}