package at.andiwand.commons.util.string;

import at.andiwand.commons.util.array.ArrayUtil;

public class CharSequenceArray implements CharSequence {
	
	private final char[] chars;
	private final int off;
	private final int len;
	
	public CharSequenceArray(char[] chars) {
		this(chars, 0, chars.length);
	}
	
	public CharSequenceArray(char[] chars, int off, int len) {
		this.chars = chars;
		this.off = off;
		this.len = len;
	}
	
	@Override
	public String toString() {
		return new String(chars, off, len);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		
		if (!(obj instanceof CharSequenceArray)) return false;
		CharSequenceArray other = (CharSequenceArray) obj;
		
		return (len == other.len)
				&& ArrayUtil.equals(chars, off, other.chars, other.off, len);
	}
	
	@Override
	public int hashCode() {
		return ArrayUtil.hashCode(chars, off, len);
	}
	
	@Override
	public int length() {
		return len;
	}
	
	@Override
	public char charAt(int index) {
		return chars[off + index];
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		return new CharSequenceArray(chars, off + start, end - start);
	}
	
}