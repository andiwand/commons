package at.andiwand.commons.util.object;

public interface ObjectExtractor<S, D> extends ObjectTransformer<S, D> {
	
	public void insert(S into, D object);
	
}