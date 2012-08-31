package at.andiwand.common.util;

public class CharSequenceUtil {
	
	public static int hashCode(CharSequence charSequence) {
		int result = 0;
		
		for (int i = 0; i < charSequence.length(); i++) {
			result = 31 * result + charSequence.charAt(i);
		}
		
		return result;
	}
	
	public static boolean equals(CharSequence a, CharSequence b) {
		if (a.length() != b.length()) return false;
		
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean isEmpty(CharSequence charSequence) {
		return charSequence.length() == 0;
	}
	
	public static boolean statsWith(CharSequence a, CharSequence b) {
		return statsWith(a, b, 0);
	}
	
	public static boolean statsWith(CharSequence a, CharSequence b, int aoffset) {
		if (aoffset < 0) return false;
		if (b.length() > (a.length() - aoffset)) return false;
		
		for (int i = 0; i < b.length(); i++) {
			if (a.charAt(aoffset + i) != b.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean endsWith(CharSequence a, CharSequence b) {
		return statsWith(a, b, a.length() - b.length());
	}
	
	public static CharSequence trim(CharSequence charSequence) {
		int start = 0;
		int end = charSequence.length();
		
		for (; (start < end) && (charSequence.charAt(start) <= ' '); start++);
		for (; (end > 0) && (charSequence.charAt(end - 1) <= ' '); end--);
		
		return charSequence.subSequence(start, end);
	}
	
	public static CharSequence trimLeft(CharSequence charSequence) {
		int start = 0;
		int end = charSequence.length();
		for (; (start < end) && (charSequence.charAt(start) <= ' '); start++);
		return charSequence.subSequence(start, end);
	}
	
	public static CharSequence trimRight(CharSequence charSequence) {
		int end = charSequence.length();
		for (; (end > 0) && (charSequence.charAt(end - 1) <= ' '); end--);
		return charSequence.subSequence(0, end);
	}
	
	public static char[] getAsCharArray(CharSequence charSequence) {
		char[] result = new char[charSequence.length()];
		
		for (int i = 0; i < charSequence.length(); i++) {
			result[i] = charSequence.charAt(i);
		}
		
		return result;
	}
	
	private CharSequenceUtil() {}
	
}