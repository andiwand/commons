package at.stefl.commons.test;

import at.stefl.commons.util.array.ArrayUtil;

public class ArrayUtilTest {
    
    public static void main(String[] args) {
        int[] array = (int[]) ArrayUtil.growGeometric(new int[3], 4, 2);
        System.out.println(array.length);
    }
    
}