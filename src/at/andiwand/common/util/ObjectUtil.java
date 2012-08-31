package at.andiwand.common.util;

public class ObjectUtil {
	
	public static boolean equals(Object a, Object b) {
		return (a == null) ? (b == null) : a.equals(b);
	}
	
	private ObjectUtil() {}
	
}