package at.andiwand.common.util.collections.primitive;

import java.util.AbstractCollection;


public abstract class AbstractPrimitiveCollection<E> extends
		AbstractCollection<E> implements PrimitiveCollection<E> {
	
	@Override
	public byte[] toByteArray() {
		byte[] result = new byte[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextByte();
		}
		
		return result;
	}
	
	@Override
	public char[] toCharArray() {
		char[] result = new char[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextChar();
		}
		
		return result;
	}
	
	@Override
	public short[] toShortArray() {
		short[] result = new short[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextShort();
		}
		
		return result;
	}
	
	@Override
	public int[] toIntArray() {
		int[] result = new int[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextInt();
		}
		
		return result;
	}
	
	@Override
	public long[] toLongArray() {
		long[] result = new long[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextLong();
		}
		
		return result;
	}
	
	@Override
	public float[] toFloatArray() {
		float[] result = new float[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextFloat();
		}
		
		return result;
	}
	
	@Override
	public double[] toDoubleArray() {
		double[] result = new double[size()];
		
		PrimitiveIterator<E> iterator = primitiveIterator();
		for (int i = 0; iterator.hasNext(); i++) {
			result[i] = iterator.nextDouble();
		}
		
		return result;
	}
	
}