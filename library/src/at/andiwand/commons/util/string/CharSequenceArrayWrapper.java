package at.andiwand.commons.util.string;

import at.andiwand.commons.util.array.ArrayUtil;


public class CharSequenceArrayWrapper extends AbstractCharSequence {
	
	private char[] array;
	private int off;
	private int len;
	
	public CharSequenceArrayWrapper(char[] array) {
		this(array, 0, array.length);
	}
	
	public CharSequenceArrayWrapper(char[] array, int off, int len) {
		this.array = array;
		this.off = off;
		this.len = len;
	}
	
	@Override
	public String toString() {
		return new String(array, off, len);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		
		if (obj instanceof CharSequenceArrayWrapper) {
			CharSequenceArrayWrapper other = (CharSequenceArrayWrapper) obj;
			return (len == other.len)
					&& ArrayUtil
							.equals(array, off, other.array, other.off, len);
		} else if (obj instanceof CharSequence) {
			CharSequence other = (CharSequence) obj;
			return CharSequenceUtil.equals(this, other);
		}
		
		return false;
	}
	
	public char[] getCharArray() {
		return array;
	}
	
	public int getOffset() {
		return off;
	}
	
	public int getLength() {
		return len;
	}
	
	@Override
	public int length() {
		return len;
	}
	
	@Override
	public char charAt(int index) {
		return array[off + index];
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		return new CharSequenceArrayWrapper(array, off + start, end - start);
	}
	
}