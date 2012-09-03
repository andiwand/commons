package at.andiwand.commons.util.collections.primitive;

public abstract class AbstractBooleanCollection extends
		AbstractPrimitiveCollection<Boolean> {
	
	public abstract boolean containsValue(boolean e);
	
	@Override
	public boolean containsBoolean(boolean e) {
		return containsValue(e);
	}
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue(e != 0);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue(e != 0);
	}
	
	public abstract boolean addValue(boolean e);
	
	@Override
	public boolean add(Boolean e) {
		return addValue(e);
	}
	
	@Override
	public boolean addBoolean(boolean e) {
		return addValue(e);
	}
	
	@Override
	public boolean addByte(byte e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue(e != 0);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue(e != 0);
	}
	
	public abstract boolean removeValue(boolean e);
	
	@Override
	public boolean removeBoolean(boolean e) {
		return removeValue(e);
	}
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue(e != 0);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue(e != 0);
	}
	
	@Override
	public abstract AbstractBooleanIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Boolean> c) {
		AbstractBooleanIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsBoolean(iterator.nextValue())) return false;
		}
		
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Boolean> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Boolean> iterator = c.primitiveIterator();
		while (iterator.hasNext()) {
			result |= addValue(iterator.nextBoolean());
		}
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Boolean> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Boolean> iterator = c.primitiveIterator();
		while (iterator.hasNext()) {
			result |= removeValue(iterator.nextBoolean());
		}
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Boolean> c) {
		boolean result = false;
		
		AbstractBooleanIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (c.containsBoolean(iterator.nextValue())) continue;
			iterator.remove();
			result = true;
		}
		
		return result;
	}
	
}