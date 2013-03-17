package at.andiwand.commons.util.collection.primitive;

public abstract class AbstractByteCollection extends
		AbstractPrimitiveCollection<Byte> {
	
	public abstract boolean containsValue(byte e);
	
	public abstract boolean addValue(byte e);
	
	public abstract boolean removeValue(byte e);
	
}