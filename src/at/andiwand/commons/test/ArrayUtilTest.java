package at.andiwand.commons.test;

import at.andiwand.commons.util.array.ArrayUtil;


public class ArrayUtilTest {
	
	public static void main(String[] args) {
		int[] array = (int[]) ArrayUtil.growGeometric(new int[3], 4, 2);
		System.out.println(array.length);
	}
	
}