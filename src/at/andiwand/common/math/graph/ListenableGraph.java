package at.andiwand.common.math.graph;

public interface ListenableGraph<V, E extends Edge> extends Graph<V, E> {
	
	public void addListener(GraphListener listener);
	
	public void removeListener(GraphListener listener);
	
}