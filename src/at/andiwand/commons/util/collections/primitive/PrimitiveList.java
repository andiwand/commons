package at.andiwand.commons.util.collections.primitive;

import java.util.List;


public interface PrimitiveList<E> extends List<E>, PrimitiveCollection<E> {
	
	public void addBoolean(int i, boolean e);
	
	public void addByte(int i, byte e);
	
	public void addChar(int i, char e);
	
	public void addShort(int i, short e);
	
	public void addInt(int i, int e);
	
	public void addLong(int i, long e);
	
	public void addFloat(int i, float e);
	
	public void addDouble(int i, double e);
	
	public boolean setBoolean(int i, boolean e);
	
	public byte setByte(int i, byte e);
	
	public char setChar(int i, char e);
	
	public short setShort(int i, short e);
	
	public int setInt(int i, int e);
	
	public long setLong(int i, long e);
	
	public float setFloat(int i, float e);
	
	public double setDouble(int i, double e);
	
	public boolean getBoolean(int i);
	
	public byte getByte(int i);
	
	public char getChar(int i);
	
	public short getShort(int i);
	
	public int getInt(int i);
	
	public long getLong(int i);
	
	public float getFloat(int i);
	
	public double getDouble(int i);
	
}