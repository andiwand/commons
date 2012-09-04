package at.andiwand.commons.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import at.andiwand.commons.util.collections.CollectionUtil;
import at.andiwand.commons.util.collections.KeyGenerator;


public class ArrayUtil {
	
	public static <T> T getFirstNotNull(T... objects) {
		for (T object : objects) {
			if (object != null) return object;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] getNotNullArray(T... objects) {
		int elements = 0;
		
		for (T object : objects) {
			if (object != null) elements++;
		}
		
		T[] result = (T[]) Array.newInstance(objects.getClass()
				.getComponentType(), elements);
		
		int i = 0;
		for (T object : objects) {
			if (object != null) result[i++] = object;
		}
		
		return result;
	}
	
	public static <E> HashSet<E> toHashSet(E... array) {
		HashSet<E> result = new HashSet<E>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Boolean> toHashSet(boolean... array) {
		HashSet<Boolean> result = new HashSet<Boolean>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Byte> toHashSet(byte... array) {
		HashSet<Byte> result = new HashSet<Byte>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Character> toHashSet(char... array) {
		HashSet<Character> result = new HashSet<Character>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Short> toHashSet(short... array) {
		HashSet<Short> result = new HashSet<Short>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Integer> toHashSet(int... array) {
		HashSet<Integer> result = new HashSet<Integer>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Long> toHashSet(long... array) {
		HashSet<Long> result = new HashSet<Long>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Float> toHashSet(float... array) {
		HashSet<Float> result = new HashSet<Float>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static HashSet<Double> toHashSet(double... array) {
		HashSet<Double> result = new HashSet<Double>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static <E> ArrayList<E> toArrayList(E... array) {
		ArrayList<E> result = new ArrayList<E>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Boolean> toArrayList(boolean... array) {
		ArrayList<Boolean> result = new ArrayList<Boolean>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Byte> toArrayList(byte... array) {
		ArrayList<Byte> result = new ArrayList<Byte>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Character> toArrayList(char... array) {
		ArrayList<Character> result = new ArrayList<Character>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Short> toArrayList(short... array) {
		ArrayList<Short> result = new ArrayList<Short>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Integer> toArrayList(int... array) {
		ArrayList<Integer> result = new ArrayList<Integer>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Long> toArrayList(long... array) {
		ArrayList<Long> result = new ArrayList<Long>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Float> toArrayList(float... array) {
		ArrayList<Float> result = new ArrayList<Float>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static ArrayList<Double> toArrayList(double... array) {
		ArrayList<Double> result = new ArrayList<Double>(array.length);
		CollectionUtil.addAll(result, array);
		return result;
	}
	
	public static <E extends Comparable<E>> E getGreatest(E... array) {
		E result = null;
		
		for (int i = 0; i < array.length; i++) {
			E e = array[i];
			
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) > 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getGreatest(Comparator<? super E> comparator,
			E... array) {
		E result = null;
		
		for (int i = 0; i < array.length; i++) {
			E e = array[i];
			
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) > 0))
				result = e;
		}
		
		return result;
	}
	
	public static boolean getGreatest(boolean... array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i]) return true;
		}
		
		return false;
	}
	
	public static byte getGreatest(byte... array) {
		byte result = Byte.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Byte.MAX_VALUE) return Byte.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static char getGreatest(char... array) {
		char result = Character.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Character.MAX_VALUE) return Character.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static short getGreatest(short... array) {
		short result = Short.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Short.MAX_VALUE) return Short.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static int getGreatest(int... array) {
		int result = Integer.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static long getGreatest(long... array) {
		long result = Long.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Long.MAX_VALUE) return Long.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static float getGreatest(float... array) {
		float result = Float.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Float.MAX_VALUE) return Float.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static double getGreatest(double... array) {
		double result = Double.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Double.MAX_VALUE) return Double.MAX_VALUE;
			if (array[i] > result) result = array[i];
		}
		
		return result;
	}
	
	public static <E extends Comparable<E>> E getSmallest(E... array) {
		E result = null;
		
		for (int i = 0; i < array.length; i++) {
			E e = array[i];
			
			if (e == null) continue;
			if ((result == null) || (e.compareTo(result) < 0)) result = e;
		}
		
		return result;
	}
	
	public static <E> E getSmallest(Comparator<? super E> comparator,
			E... array) {
		E result = null;
		
		for (int i = 0; i < array.length; i++) {
			E e = array[i];
			
			if (e == null) continue;
			if ((result == null) || (comparator.compare(e, result) < 0))
				result = e;
		}
		
		return result;
	}
	
	public static boolean getSmallest(boolean... array) {
		for (int i = 0; i < array.length; i++) {
			if (!array[i]) return false;
		}
		
		return true;
	}
	
	public static byte getSmallest(byte... array) {
		byte result = Byte.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Byte.MIN_VALUE) return Byte.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static char getSmallest(char... array) {
		char result = Character.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Character.MIN_VALUE) return Character.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static short getSmallest(short... array) {
		short result = Short.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Short.MIN_VALUE) return Short.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static int getSmallest(int... array) {
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Integer.MIN_VALUE) return Integer.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static long getSmallest(long... array) {
		long result = Long.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Long.MIN_VALUE) return Long.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static float getSmallest(float... array) {
		float result = Float.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Float.MIN_VALUE) return Float.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static double getSmallest(double... array) {
		double result = Double.MAX_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Double.MIN_VALUE) return Double.MIN_VALUE;
			if (array[i] < result) result = array[i];
		}
		
		return result;
	}
	
	public static <K, V> void putAll(Map<? super K, ? super V> map,
			KeyGenerator<? extends K, ? super V> keyGenerator, V... values) {
		for (int i = 0; i < values.length; i++) {
			V value = values[i];
			K key = keyGenerator.generateKey(value);
			map.put(key, value);
		}
	}
	
	public static <K, V> HashMap<K, V> toHashMap(
			KeyGenerator<? extends K, ? super V> keyGenerator, V... values) {
		HashMap<K, V> result = new HashMap<K, V>();
		putAll(result, keyGenerator, values);
		return result;
	}
	
	private ArrayUtil() {}
	
}