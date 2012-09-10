package at.andiwand.commons.util.collection.primitive;

import java.util.Collection;


public interface PrimitiveCollection<E> extends
		Collection<E>,
		PrimitiveIterable<E> {
	
	public boolean containsBoolean(boolean e);
	
	public boolean containsByte(byte e);
	
	public boolean containsChar(char e);
	
	public boolean containsShort(short e);
	
	public boolean containsInt(int e);
	
	public boolean containsLong(long e);
	
	public boolean containsFloat(float e);
	
	public boolean containsDouble(double e);
	
	public boolean addBoolean(boolean e);
	
	public boolean addByte(byte e);
	
	public boolean addChar(char e);
	
	public boolean addShort(short e);
	
	public boolean addInt(int e);
	
	public boolean addLong(long e);
	
	public boolean addFloat(float e);
	
	public boolean addDouble(double e);
	
	public boolean removeBoolean(boolean e);
	
	public boolean removeByte(byte e);
	
	public boolean removeChar(char e);
	
	public boolean removeShort(short e);
	
	public boolean removeInt(int e);
	
	public boolean removeLong(long e);
	
	public boolean removeFloat(float e);
	
	public boolean removeDouble(double e);
	
	public boolean containsAll(PrimitiveCollection<? extends E> c);
	
	public boolean addAll(PrimitiveCollection<? extends E> c);
	
	public boolean removeAll(PrimitiveCollection<? extends E> c);
	
	public boolean retainAll(PrimitiveCollection<? extends E> c);
	
	public boolean[] toBooleanArray();
	
	public byte[] toByteArray();
	
	public char[] toCharArray();
	
	public short[] toShortArray();
	
	public int[] toIntArray();
	
	public long[] toLongArray();
	
	public float[] toFloatArray();
	
	public double[] toDoubleArray();
	
}