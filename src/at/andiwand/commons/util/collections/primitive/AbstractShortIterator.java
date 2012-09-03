package at.andiwand.commons.util.collections.primitive;

public abstract class AbstractShortIterator implements PrimitiveIterator<Short> {
	
	public abstract short nextValue();
	
	@Override
	public Short next() {
		return nextValue();
	}
	
	@Override
	public boolean nextBoolean() {
		return nextValue() != 0;
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