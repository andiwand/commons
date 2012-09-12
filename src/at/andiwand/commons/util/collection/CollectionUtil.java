package at.andiwand.commons.util.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;

import at.andiwand.commons.util.iterator.IterableIterator;
import at.andiwand.commons.util.iterator.IteratorEnumeration;


// TODO: implement swapAll
// TODO: improve argument names
// TODO: call method by method, avoid redundant code?
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
	
	public static boolean containsAll(Collection<?> c, Object[] array, int off,
			int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Boolean> c,
			boolean[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Byte> c, byte[] array,
			int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Character> c,
			char[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Short> c,
			short[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Integer> c,
			int[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Long> c, long[] array,
			int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Float> c,
			float[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
			if (!c.contains(array[i])) return false;
		}
		
		return true;
	}
	
	public static boolean containsAll(Collection<? super Double> c,
			double[] array, int off, int len) {
		int end = off + len;
		
		for (int i = off; i < end; i++) {
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
	
	public static <E> boolean addAll(Collection<? super E> c, E[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Boolean> c,
			boolean[] array, int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Byte> c, byte[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Character> c, char[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Short> c, short[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Integer> c, int[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Long> c, long[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Float> c, float[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.add(array[i]);
		}
		
		return result;
	}
	
	public static boolean addAll(Collection<? super Double> c, double[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
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
	
	public static boolean removeAll(Collection<?> c, Object[] array, int off,
			int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Byte> c,
			boolean[] array, int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Byte> c, byte[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Character> c,
			char[] array, int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Short> c, short[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Integer> c, int[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Long> c, long[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Float> c, float[] array,
			int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static boolean removeAll(Collection<? super Double> c,
			double[] array, int off, int len) {
		int end = off + len;
		boolean result = false;
		
		for (int i = off; i < end; i++) {
			result |= c.remove(array[i]);
		}
		
		return result;
	}
	
	public static <E extends Comparable<E>> E getGreatest(
			Collection<? extends E> c) {
		if ((c instanceof List) && (c instanceof RandomAccess))
			return getGreatest((List<? extends E>) c);
		
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) > 0)) result = e;
		}
		
		return result;
	}
	
	private static <E extends Comparable<E>> E getGreatest(
			List<? extends E> randomAccessList) {
		E result = null;
		
		for (int i = 0; i < randomAccessList.size(); i++) {
			E e = randomAccessList.get(i);
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) > 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getGreatest(Comparator<? super E> comparator,
			Collection<? extends E> c) {
		if ((c instanceof List) && (c instanceof RandomAccess))
			return getGreatest(comparator, (List<? extends E>) c);
		
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) > 0))
				result = e;
		}
		
		return result;
	}
	
	private static <E> E getGreatest(Comparator<? super E> comparator,
			List<? extends E> randomAccessList) {
		E result = null;
		
		for (int i = 0; i < randomAccessList.size(); i++) {
			E e = randomAccessList.get(i);
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) > 0))
				result = e;
		}
		
		return result;
	}
	
	public static <E extends Comparable<E>> E getSmallest(
			Collection<? extends E> c) {
		if ((c instanceof List) && (c instanceof RandomAccess))
			return getSmallest((List<? extends E>) c);
		
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) < 0)) result = e;
		}
		
		return result;
	}
	
	private static <E extends Comparable<E>> E getSmallest(
			List<? extends E> randomAccessList) {
		E result = null;
		
		for (int i = 0; i < randomAccessList.size(); i++) {
			E e = randomAccessList.get(i);
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) < 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getSmallest(Comparator<? super E> comparator,
			Collection<? extends E> c) {
		if ((c instanceof List) && (c instanceof RandomAccess))
			return getSmallest(comparator, (List<? extends E>) c);
		
		E result = null;
		
		for (E e : c) {
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) < 0))
				result = e;
		}
		
		return result;
	}
	
	private static <E> E getSmallest(Comparator<? super E> comparator,
			List<? extends E> randomAccessList) {
		E result = null;
		
		for (int i = 0; i < randomAccessList.size(); i++) {
			E e = randomAccessList.get(i);
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) < 0))
				result = e;
		}
		
		return result;
	}
	
	public static <K, V> void putAll(Map<? super K, ? super V> map,
			KeyGenerator<? extends K, ? super V> keyGenerator,
			Collection<? extends V> values) {
		if ((values instanceof List) && (values instanceof RandomAccess)) {
			putAll(map, keyGenerator, (List<? extends V>) values);
			return;
		}
		
		for (V value : values) {
			K key = keyGenerator.generateKey(value);
			map.put(key, value);
		}
	}
	
	private static <K, V> void putAll(Map<? super K, ? super V> map,
			KeyGenerator<? extends K, ? super V> keyGenerator,
			List<? extends V> randomAccessList) {
		for (int i = 0; i < randomAccessList.size(); i++) {
			V value = randomAccessList.get(i);
			K key = keyGenerator.generateKey(value);
			map.put(key, value);
		}
	}
	
	public static <K, V> void putAllNotNull(Map<? super K, ? super V> map,
			KeyGenerator<? extends K, ? super V> keyGenerator,
			Collection<? extends V> values) {
		if ((values instanceof List) && (values instanceof RandomAccess)) {
			putAllNotNull(map, keyGenerator, (List<? extends V>) values);
			return;
		}
		
		for (V value : values) {
			K key = keyGenerator.generateKey(value);
			if (key == null) continue;
			map.put(key, value);
		}
	}
	
	private static <K, V> void putAllNotNull(Map<? super K, ? super V> map,
			KeyGenerator<? extends K, ? super V> keyGenerator,
			List<? extends V> randomAccessList) {
		for (int i = 0; i < randomAccessList.size(); i++) {
			V value = randomAccessList.get(i);
			K key = keyGenerator.generateKey(value);
			if (key == null) continue;
			map.put(key, value);
		}
	}
	
	public static <K, V> HashMap<K, V> toHashMap(
			KeyGenerator<? extends K, ? super V> keyGenerator,
			Collection<? extends V> values) {
		HashMap<K, V> result = new HashMap<K, V>();
		putAll(result, keyGenerator, values);
		return result;
	}
	
	public static <K, V> HashMap<K, V> toHashMapNotNull(
			KeyGenerator<? extends K, ? super V> keyGenerator,
			Collection<? extends V> values) {
		HashMap<K, V> result = new HashMap<K, V>();
		putAllNotNull(result, keyGenerator, values);
		return result;
	}
	
	public static <E> Enumeration<E> enumeration(Collection<? extends E> c) {
		return new IteratorEnumeration<E>(c.iterator());
	}
	
	public static <E> void swap(List<E> list, int i, int j) {
		if (list instanceof RandomAccess) {
			list.set(i, list.set(j, list.get(i)));
		} else {
			ListIterator<E> iterator = list.listIterator(i);
			iterator.set(list.set(j, iterator.next()));
		}
	}
	
	// TODO: implement swapAll
	// TODO: implement reverseComperator
	
	private CollectionUtil() {}
	
}