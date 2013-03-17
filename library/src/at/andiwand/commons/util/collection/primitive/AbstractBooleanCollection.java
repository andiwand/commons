package at.andiwand.commons.util.collection.primitive;

public abstract class AbstractBooleanCollection extends
		AbstractPrimitiveCollection<Boolean> {
	
	public abstract boolean containsValue(boolean e);
	
	public abstract boolean addValue(boolean e);
	
	public abstract boolean removeValue(boolean e);
	
}