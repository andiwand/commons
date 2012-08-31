package at.andiwand.common.util.collections.primitive;

public abstract class AbstractDoubleCollection extends
		AbstractPrimitiveCollection<Double> {
	
	public abstract boolean containsValue(double e);
	
	@Override
	public boolean containsByte(byte e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsChar(char e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsShort(short e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsInt(int e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsLong(long e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsFloat(float e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean containsDouble(double e) {
		return containsValue((double) e);
	}
	
	@Override
	public boolean add(Double e) {
		return addValue(e.doubleValue());
	}
	
	public abstract boolean addValue(double e);
	
	@Override
	public boolean addByte(byte e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addChar(char e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addShort(short e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addInt(int e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addLong(long e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addFloat(float e) {
		return addValue((double) e);
	}
	
	@Override
	public boolean addDouble(double e) {
		return addValue((double) e);
	}
	
	public abstract boolean removeValue(double e);
	
	@Override
	public boolean removeByte(byte e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeChar(char e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeShort(short e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeInt(int e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeLong(long e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeFloat(float e) {
		return removeValue((double) e);
	}
	
	@Override
	public boolean removeDouble(double e) {
		return removeValue((double) e);
	}
	
	@Override
	public abstract AbstractDoubleIterator primitiveIterator();
	
	@Override
	public boolean containsAll(PrimitiveCollection<? extends Double> c) {
		AbstractDoubleIterator iterator = primitiveIterator();
		while (iterator.hasNext())
			if (!c.containsDouble(iterator.nextValue())) return false;
		return true;
	}
	
	@Override
	public boolean addAll(PrimitiveCollection<? extends Double> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Double> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= addValue(iterator.nextDouble());
		
		return result;
	}
	
	@Override
	public boolean removeAll(PrimitiveCollection<? extends Double> c) {
		boolean result = false;
		
		PrimitiveIterator<? extends Double> iterator = c.primitiveIterator();
		while (iterator.hasNext())
			result |= removeValue(iterator.nextDouble());
		
		return result;
	}
	
	@Override
	public boolean retainAll(PrimitiveCollection<? extends Double> c) {
		boolean result = false;
		
		AbstractDoubleIterator iterator = primitiveIterator();
		while (iterator.hasNext()) {
			if (!c.containsDouble(iterator.nextValue())) {
				iterator.remove();
				result = true;
			}
		}
		
		return result;
	}
	
}