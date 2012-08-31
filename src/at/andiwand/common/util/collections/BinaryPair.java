package at.andiwand.common.util.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


// TODO: handle null
public class BinaryPair<E> {
	
	private E elementA;
	private E elementB;
	
	public BinaryPair(E elementA, E elementB) {
		this.elementA = elementA;
		this.elementB = elementB;
	}
	
	@Override
	public String toString() {
		return "{" + elementA + ", " + elementB + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof BinaryPair<?>)) return false;
		BinaryPair<?> other = (BinaryPair<?>) obj;
		
		return (elementA.equals(other.elementA) && elementB
				.equals(other.elementB));
	}
	
	@Override
	public int hashCode() {
		return elementA.hashCode() + 31 * elementB.hashCode();
	}
	
	public E getElementA() {
		return elementA;
	}
	
	public E getElementB() {
		return elementB;
	}
	
	@SuppressWarnings("unchecked")
	public E[] getAsArray() {
		E[] result = (E[]) Array.newInstance(elementA.getClass(), 2);
		
		result[0] = elementA;
		result[1] = elementB;
		
		return result;
	}
	
	public List<E> getAsList() {
		ArrayList<E> result = new ArrayList<E>(2);
		
		result.add(elementA);
		result.add(elementB);
		
		return result;
	}
	
	public static void main(String[] args) {
		BinaryPair<String> pair = new BinaryPair<String>("asdf", null);
		
		System.out.println(pair.getAsArray()[0]);
	}
	
}