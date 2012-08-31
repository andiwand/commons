package at.andiwand.common.util.collections.primitive;

public abstract class AbstractIntIterator implements PrimitiveIterator<Integer> {
	
	public abstract int nextValue();
	
	@Override
	public Integer next() {
		return nextValue();
	}
	
	@Override
	public byte nextByte() {
		return (byte) nextValue();
	}
	
	@Override
	public char nextChar() {
		return (char) nextValue();
	}
	
	@Override
	public short nextShort() {
		return (short) nextValue();
	}
	
	@Override
	public int nextInt() {
		return (int) nextValue();
	}
	
	@Override
	public long nextLong() {
		return (long) nextValue();
	}
	
	@Override
	public float nextFloat() {
		return (float) nextValue();
	}
	
	@Override
	public double nextDouble() {
		return (double) nextValue();
	}
	
}