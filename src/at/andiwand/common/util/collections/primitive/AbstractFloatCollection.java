package at.andiwand.common.util.collections.primitive;

public abstract class AbstractFloatCollection extends
		AbstractPrimitiveCollection<Float> {
	
	public abstract boolean containsValue(float e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((float) e);
	}
	
	@Override
	public boolean add(Float e) {
		return addValue(e.floatValue());
	}
	
	public abstract boolean addValue(float e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((float) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((float) e);
	}
	
	public abstract boolean removeValue(float e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((float) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((float) e);
	}
	
	@Override
	public abstract AbstractFloatIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Float> c) {
		AbstractFloatIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsFloat(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Float> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Float> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextFloat());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Float> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Float> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextFloat());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Float> c) {
		boolean result = false;
		
		AbstractFloatIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsFloat(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}