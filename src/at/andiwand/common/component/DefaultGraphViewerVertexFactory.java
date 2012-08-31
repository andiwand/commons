package at.andiwand.common.component;

public class DefaultGraphViewerVertexFactory extends
		GenericGraphViewerVertexFactory<Object, DefaultGraphViewerVertex> {
	
	public DefaultGraphViewerVertexFactory() {
		super(Object.class, DefaultGraphViewerVertex.class);
	}
	
	@Override
	protected DefaultGraphViewerVertex buildVertexGeneric(Object vertex) {
		return new DefaultGraphViewerVertex(vertex);
	}
	
}