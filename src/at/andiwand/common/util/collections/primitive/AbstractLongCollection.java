package at.andiwand.common.util.collections.primitive;

public abstract class AbstractLongCollection extends
		AbstractPrimitiveCollection<Long> {
	
	public abstract boolean containsValue(long e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((long) e);
	}
	
	@Override
	public boolean add(Long e) {
		return addValue(e.longValue());
	}
	
	public abstract boolean addValue(long e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((long) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((long) e);
	}
	
	public abstract boolean removeValue(long e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((long) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((long) e);
	}
	
	@Override
	public abstract AbstractLongIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Long> c) {
		AbstractLongIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsLong(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Long> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Long> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextLong());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Long> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Long> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextLong());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Long> c) {
		boolean result = false;
		
		AbstractLongIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsLong(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}