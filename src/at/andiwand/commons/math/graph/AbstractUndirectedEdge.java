package at.andiwand.commons.math.graph;

import at.andiwand.commons.util.collections.HashMultiset;
import at.andiwand.commons.util.collections.Multiset;


public abstract class AbstractUndirectedEdge extends AbstractEdge implements
		UndirectedEdge {
	
	public static final int VERTEX_COUNT = 2;
	
	@Override
	public int getVertexCount() {
		return VERTEX_COUNT;
	}
	
	@Override
	public Multiset<? extends Object> getVertices() {
		Multiset<Object> result = new HashMultiset<Object>(VERTEX_COUNT);
		
		result.add(getVertexA());
		result.add(getVertexB());
		
		return result;
	}
	
	@Override
	public boolean isLoop() {
		return getVertexA().equals(getVertexB());
	}
	
}