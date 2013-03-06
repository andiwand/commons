package at.andiwand.commons.util;

public interface ObjectTranslator<T1, T2> {
	
	public T2 translate(T1 o1);
	
	public T1 translateBack(T2 o2);
	
}