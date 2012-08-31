package at.andiwand.common.component;

public interface GraphViewerVertexFactory {
	
	public abstract Class<? extends Object> getVertexClass();
	
	public abstract Class<? extends GraphViewerVertex> getViewerVertexClass();
	
	public abstract GraphViewerVertex buildVertex(Object vertex);
	
}