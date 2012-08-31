package at.andiwand.common.util.collections.primitive;

import java.util.Iterator;


public interface PrimitiveIterator<E> extends Iterator<E> {
	
	public byte nextByte();
	
	public char nextChar();
	
	public short nextShort();
	
	public int nextInt();
	
	public long nextLong();
	
	public float nextFloat();
	
	public double nextDouble();
	
}