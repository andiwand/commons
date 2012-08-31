package at.andiwand.common.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import at.andiwand.common.util.collections.CollectionUtil;


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
	
	private ArrayUtil() {}
	
}