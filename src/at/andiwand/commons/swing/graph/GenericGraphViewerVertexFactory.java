package at.andiwand.commons.swing.graph;

public abstract class GenericGraphViewerVertexFactory<V extends Object, GV extends GraphViewerVertex>
	implements GraphViewerVertexFactory {

    private final Class<V> vertexClass;
    private final Class<GV> viewerVertexClass;

    public GenericGraphViewerVertexFactory(Class<V> vertexClass,
	    Class<GV> viewerVertexClass) {
	this.vertexClass = vertexClass;
	this.viewerVertexClass = viewerVertexClass;
    }

    @Override
    public final Class<V> getVertexClass() {
	return vertexClass;
    }

    @Override
    public final Class<GV> getViewerVertexClass() {
	return viewerVertexClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final GV buildVertex(Object vertex) {
	return buildVertexGeneric((V) vertex);
    }

    protected abstract GV buildVertexGeneric(V vertex);

}