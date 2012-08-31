package at.andiwand.common.util.collections.primitive;

public abstract class AbstractShortCollection extends
		AbstractPrimitiveCollection<Short> {
	
	public abstract boolean containsValue(short e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((short) e);
	}
	
	@Override
	public boolean add(Short e) {
		return addValue(e.shortValue());
	}
	
	public abstract boolean addValue(short e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((short) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((short) e);
	}
	
	public abstract boolean removeValue(short e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((short) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((short) e);
	}
	
	@Override
	public abstract AbstractShortIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Short> c) {
		AbstractShortIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsShort(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Short> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Short> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextShort());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Short> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Short> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextShort());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Short> c) {
		boolean result = false;
		
		AbstractShortIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsShort(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}