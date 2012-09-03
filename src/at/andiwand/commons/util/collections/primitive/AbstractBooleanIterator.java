package at.andiwand.commons.util.collections.primitive;

public abstract class AbstractBooleanIterator implements
		PrimitiveIterator<Boolean> {
	
	public abstract boolean nextValue();
	
	@Override
	public Boolean next() {
		return nextValue();
	}
	
	@Override
	public boolean nextBoolean() {
		return nextValue();
	}
	
	@Override
	public byte nextByte() {
		return (byte) (nextValue() ? 1 : 0);
	}
	
	@Override
	public char nextChar() {
		return (char) (nextValue() ? 1 : 0);
	}
	
	@Override
	public short nextShort() {
		return (short) (nextValue() ? 1 : 0);
	}
	
	@Override
	public int nextInt() {
		return (int) (nextValue() ? 1 : 0);
	}
	
	@Override
	public long nextLong() {
		return (long) (nextValue() ? 1 : 0);
	}
	
	@Override
	public float nextFloat() {
		return (float) (nextValue() ? 1 : 0);
	}
	
	@Override
	public double nextDouble() {
		return (double) (nextValue() ? 1 : 0);
	}
	
}