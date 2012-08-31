package at.andiwand.common.util.collections.primitive;

public abstract class AbstractCharCollection extends
		AbstractPrimitiveCollection<Character> {
	
	public abstract boolean containsValue(char e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((char) e);
	}
	
	@Override
	public boolean add(Character e) {
		return addValue(e.charValue());
	}
	
	public abstract boolean addValue(char e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((char) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((char) e);
	}
	
	public abstract boolean removeValue(char e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((char) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((char) e);
	}
	
	@Override
	public abstract AbstractCharIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Character> c) {
		AbstractCharIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsChar(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Character> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Character> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextChar());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Character> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Character> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextChar());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Character> c) {
		boolean result = false;
		
		AbstractCharIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsChar(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}