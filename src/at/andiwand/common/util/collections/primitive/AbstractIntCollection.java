package at.andiwand.common.util.collections.primitive;

public abstract class AbstractIntCollection extends
		AbstractPrimitiveCollection<Integer> {
	
	public abstract boolean containsValue(int e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((int) e);
	}
	
	@Override
	public boolean add(Integer e) {
		return addValue(e.intValue());
	}
	
	public abstract boolean addValue(int e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((int) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((int) e);
	}
	
	public abstract boolean removeValue(int e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((int) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((int) e);
	}
	
	@Override
	public abstract AbstractIntIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Integer> c) {
		AbstractIntIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsInt(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Integer> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Integer> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextInt());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Integer> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Integer> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextInt());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Integer> c) {
		boolean result = false;
		
		AbstractIntIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsInt(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}