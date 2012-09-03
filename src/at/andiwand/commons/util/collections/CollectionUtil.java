package at.andiwand.commons.util.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import at.andiwand.commons.util.iterator.IterableIterator;


public class CollectionUtil {
	
	public static <E> Iterable<E> getIterable(Iterator<E> iterator) {
		return new IterableIterator<E>(iterator);
	}
	
	public static boolean containsAll(Collection<?> c, Object... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Boolean> c,
			boolean... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Byte> c, byte... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Character> c,
			char... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Short> c,
			short... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Integer> c,
			int... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Long> c, long... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Float> c,
			float... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Double> c,
			double... array) {
		for (int i = 0; i < array.length; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static <E> boolean addAll(Collection<? super E> c, E... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Boolean> c,
			boolean... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Byte> c, byte... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Character> c, char... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Short> c, short... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Integer> c, int... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Long> c, long... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Float> c, float... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Double> c, double... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<?> c, Object... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Byte> c,
			boolean... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Byte> c, byte... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Character> c,
			char... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Short> c, short... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Integer> c, int... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Long> c, long... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Float> c, float... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Double> c,
			double... array) {
		boolean result = false;
		
		for (int i = 0; i < array.length; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static <E extends Comparable<E>> E getGreatest(Collection<E> c) {
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) > 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getGreatest(Comparator<? super E> comparator,
			Collection<E> c) {
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) > 0))
				result = e;
		}
		
		return result;
	}
	
	public static <E extends Comparable<E>> E getSmallest(Collection<E> c) {
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) < 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getSmallest(Comparator<? super E> comparator,
			Collection<E> c) {
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) < 0))
				result = e;
		}
		
		return result;
	}
	
	private CollectionUtil() {}
	
}