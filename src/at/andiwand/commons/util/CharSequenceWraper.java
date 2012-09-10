package at.andiwand.commons.util;

public class CharSequenceWraper implements CharSequence {
	
	private final CharSequence charSequence;
	
	public CharSequenceWraper(CharSequence charSequence) {
		if (charSequence == null) throw new NullPointerException();
		
		this.charSequence = charSequence;
	}
	
	@Override
	public String toString() {
		return charSequence.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		
		if (!(obj instanceof CharSequenceWraper)) return false;
		CharSequenceWraper other = (CharSequenceWraper) obj;
		
		return (charSequence == other.charSequence)
				|| CharSequenceUtil.equals(charSequence, other.charSequence);
	}
	
	@Override
	public int hashCode() {
		return CharSequenceUtil.hashCode(charSequence);
	}
	
	@Override
	public int length() {
		return charSequence.length();
	}
	
	@Override
	public char charAt(int index) {
		return charSequence.charAt(index);
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		return charSequence.subSequence(start, end);
	}
	
}