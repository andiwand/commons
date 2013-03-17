package at.andiwand.commons.util.object;

public class ObjectUtil {
	
	public static boolean equals(Object a, Object b) {
		return (a == null) ? (b == null) : a.equals(b);
	}
	
	private ObjectUtil() {}
	
}