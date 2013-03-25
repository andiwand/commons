package at.andiwand.commons.util.string;

public class CharSequenceUtil {
	
	public static int hashCode(CharSequence charSequence) {
		int length = charSequence.length();
		int result = 1;
		
		for (int i = 0; i < length; i++) {
			result = 31 * result + charSequence.charAt(i);
		}
		
		return result;
	}
	
	public static boolean equals(CharSequence a, CharSequence b) {
		int length = a.length();
		if (length != b.length()) return false;
		
		for (int i = 0; i < length; i++) {
			if (a.charAt(i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static String toString(CharSequence charSequence) {
		return new String(getAsCharArray(charSequence));
	}
	
	public static boolean isEmpty(CharSequence charSequence) {
		return charSequence.length() == 0;
	}
	
	public static boolean statsWith(CharSequence a, CharSequence b) {
		int length = b.length();
		if (length > a.length()) return false;
		
		for (int i = 0; i < length; i++) {
			if (a.charAt(i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean statsWith(CharSequence a, int offset, CharSequence b) {
		if (offset < 0) return false;
		
		int length = b.length();
		if (length > (a.length() - offset)) return false;
		
		for (int i = 0; i < length; i++) {
			if (a.charAt(offset + i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean endsWith(CharSequence a, CharSequence b) {
		int length = b.length();
		int offset = length - a.length();
		if (offset < 0) return false;
		
		for (int i = 0; i < length; i++) {
			if (a.charAt(offset + i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static CharSequence trim(CharSequence charSequence) {
		int start = 0;
		int length = charSequence.length();
		
		for (; (start < length) && (charSequence.charAt(start) <= ' '); start++);
		for (; (length > 0) && (charSequence.charAt(length - 1) <= ' '); length--);
		
		return charSequence.subSequence(start, length);
	}
	
	public static CharSequence trimLeft(CharSequence charSequence) {
		int start = 0;
		int length = charSequence.length();
		
		for (; (start < length) && (charSequence.charAt(start) <= ' '); start++);
		
		return charSequence.subSequence(start, length);
	}
	
	public static CharSequence trimRight(CharSequence charSequence) {
		int length = charSequence.length();
		
		for (; (length > 0) && (charSequence.charAt(length - 1) <= ' '); length--);
		
		return charSequence.subSequence(0, length);
	}
	
	public static void copy(CharSequence source, char[] destiantion) {
		copy(source, destiantion, 0);
	}
	
	public static void copy(CharSequence source, char[] destiantion, int offset) {
		int length = source.length();
		
		for (int i = 0; i < length; i++) {
			destiantion[offset + i] = source.charAt(i);
		}
	}
	
	public static void copy(CharSequence source, char[] destiantion,
			int offset, int length) {
		for (int i = 0; i < length; i++) {
			destiantion[offset + i] = source.charAt(i);
		}
	}
	
	public static char[] getAsCharArray(CharSequence charSequence) {
		int length = charSequence.length();
		char[] result = new char[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = charSequence.charAt(i);
		}
		
		return result;
	}
	
	private CharSequenceUtil() {}
	
}