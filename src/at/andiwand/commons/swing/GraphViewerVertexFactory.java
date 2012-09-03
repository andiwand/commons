package at.andiwand.commons.swing;

public interface GraphViewerVertexFactory {
	
	public abstract Class<? extends Object> getVertexClass();
	
	public abstract Class<? extends GraphViewerVertex> getViewerVertexClass();
	
	public abstract GraphViewerVertex buildVertex(Object vertex);
	
}