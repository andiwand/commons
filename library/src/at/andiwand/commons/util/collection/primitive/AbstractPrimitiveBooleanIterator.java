package at.andiwand.commons.util.collection.primitive;

public abstract class AbstractPrimitiveBooleanIterator implements
		PrimitiveBooleanIterator {
	
	@Override
	public Boolean next() {
		return nextPrimitive();
	}
	
}