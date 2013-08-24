package at.stefl.commons.swing.graph;

import java.awt.Point;

public class NullGraphLayout extends AbstractGraphLayout {
    
    public NullGraphLayout(GraphViewer viewer) {
        super(viewer);
    }
    
    @Override
    protected void addViewerVertexImpl(GraphViewerVertex vertex) {
        vertex.setPosition(new Point());
    }
    
    @Override
    protected void removeViewerVertexImpl(GraphViewerVertex vertex) {}
    
}