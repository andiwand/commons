package at.andiwand.commons.util.collections.primitive;

public abstract class AbstractByteCollection extends
		AbstractPrimitiveCollection<Byte> {
	
	public abstract boolean containsValue(byte e);
	
	@Override
	public boolean containsBoolean(boolean e) {
		return containsValue((byte) (e ? 1 : 0));
	}
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((byte) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((byte) e);
	}
	
	public abstract boolean addValue(byte e);
	
	@Override
	public boolean add(Byte e) {
		return addValue(e.byteValue());
	}
	
	@Override
	public boolean addBoolean(boolean e) {
		return addValue((byte) (e ? 1 : 0));
	}
	
	@Override
	public boolean addByte(byte e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((byte) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((byte) e);
	}
	
	public abstract boolean removeValue(byte e);
	
	@Override
	public boolean removeBoolean(boolean e) {
		return removeValue((byte) (e ? 1 : 0));
	}
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((byte) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((byte) e);
	}
	
	@Override
	public abstract AbstractByteIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Byte> c) {
		AbstractByteIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsByte(iterator.nextValue())) return false;
		}
		
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Byte> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Byte> iterator = c.primitiveIterator();
		while (iterator.hasNext()) {
			result |= addValue(iterator.nextByte());
		}
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Byte> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Byte> iterator = c.primitiveIterator();
		while (iterator.hasNext()) {
			result |= removeValue(iterator.nextByte());
		}
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Byte> c) {
		boolean result = false;
		
		AbstractByteIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (c.containsByte(iterator.nextValue())) continue;
			iterator.remove();
			result = true;
		}
		
		return result;
	}
	
}