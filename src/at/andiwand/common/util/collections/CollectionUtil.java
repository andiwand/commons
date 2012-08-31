package at.andiwand.common.util.collections;

import java.util.Collection;
import java.util.Iterator;

import at.andiwand.common.util.iterator.IterableIterator;


public class CollectionUtil {
	
	public static <E> Iterable<E> getIterable(Iterator<E> iterator) {
		return new IterableIterator<E>(iterator);
	}
	
	public static boolean containsAll(Collection<?> collection, Object... array) {
		for (Object e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Byte> collection,
			byte... array) {
		for (byte e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Character> collection,
			char... array) {
		for (char e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Short> collection,
			short... array) {
		for (short e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Integer> collection,
			int... array) {
		for (int e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Long> collection,
			long... array) {
		for (long e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Float> collection,
			float... array) {
		for (float e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static boolean containsAll(Collection<? super Double> collection,
			double... array) {
		for (double e : array)
			if (!collection.contains(e)) return false;
		return true;
	}
	
	public static <E> boolean addAll(Collection<? super E> collection,
			E... array) {
		boolean result = false;
		for (E e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Byte> collection,
			byte... array) {
		boolean result = false;
		for (byte e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Character> collection,
			char... array) {
		boolean result = false;
		for (char e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Short> collection,
			short... array) {
		boolean result = false;
		for (short e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Integer> collection,
			int... array) {
		boolean result = false;
		for (int e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Long> collection,
			long... array) {
		boolean result = false;
		for (long e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Float> collection,
			float... array) {
		boolean result = false;
		for (float e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean addAll(Collection<? super Double> collection,
			double... array) {
		boolean result = false;
		for (double e : array)
			result |= collection.add(e);
		return result;
	}
	
	public static boolean removeAll(Collection<?> collection, Object... array) {
		boolean result = false;
		for (Object e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Byte> collection,
			byte... array) {
		boolean result = false;
		for (byte e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Character> collection,
			char... array) {
		boolean result = false;
		for (char e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Short> collection,
			short... array) {
		boolean result = false;
		for (short e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Integer> collection,
			int... array) {
		boolean result = false;
		for (int e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Long> collection,
			long... array) {
		boolean result = false;
		for (long e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Float> collection,
			float... array) {
		boolean result = false;
		for (float e : array)
			result |= collection.remove(e);
		return result;
	}
	
	public static boolean removeAll(Collection<? super Double> collection,
			double... array) {
		boolean result = false;
		for (double e : array)
			result |= collection.remove(e);
		return result;
	}
	
	private CollectionUtil() {}
	
}