package at.andiwand.common.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;

import at.andiwand.common.graphics.GraphicsUtil;
import at.andiwand.common.math.graph.Edge;
import at.andiwand.common.math.graph.Graph;
import at.andiwand.common.math.graph.GraphAdapter;
import at.andiwand.common.math.graph.ListenableGraph;


// TODO: make use of Vertex2d
public class GraphViewer extends JComponent {
	
	private static final long serialVersionUID = -3851523788422302409L;
	
	public static final int SELECTION_MODE_NONE = 0;
	public static final int SELECTION_MODE_SINGLE = 1;
	public static final int SELECTION_MODE_MULTI = 2;
	
	private class UpdateHandler extends GraphAdapter {
		public void vertexAdded(Object vertex) {
			addVertex(vertex);
		}
		
		public void edgeAdded(Edge edge) {
			addEdge(edge);
		}
		
		public void vertexRemoved(Object vertex) {
			removeVertex(vertex);
		}
		
		public void edgeRemoved(Edge edge) {
			removeEdge(edge);
		}
	}
	
	public class MouseHandler extends MouseAdapter {
		private GraphViewerVertex movingVertex;
		private int movingDx;
		private int movingDy;
		
		public void mousePressed(MouseEvent e) {
			if (e.getButton() != MouseEvent.BUTTON1) return;
			
			synchronized (vertexMap) {
				for (GraphViewerVertex vertex : vertexMap.values()) {
					if (vertex.intersects(e.getPoint())) {
						movingVertex = vertex;
						movingDx = vertex.getPosition().x - e.getPoint().x;
						movingDy = vertex.getPosition().y - e.getPoint().y;
						
						break;
					}
				}
			}
		}
		
		public void mouseDragged(MouseEvent e) {
			if (movingVertex == null) return;
			
			Point point = new Point(e.getPoint().x + movingDx, e.getPoint().y
					+ movingDy);
			layout.moveVertex(movingVertex, point);
			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() != MouseEvent.BUTTON1) return;
			
			movingVertex = null;
		}
	}
	
	private class MouseListenerHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			handleMouseEvent(e);
		}
		
		public void mouseClicked(MouseEvent e) {
			handleMouseEvent(e);
		}
		
		public void mouseReleased(MouseEvent e) {
			handleMouseEvent(e);
		}
	}
	
	private Graph<? extends Object, ? extends Edge> model;
	private UpdateHandler updateHandler;
	
	private final Map<Object, GraphViewerVertex> vertexMap = new HashMap<Object, GraphViewerVertex>();
	private final Map<Edge, GraphViewerEdge> edgeMap = new HashMap<Edge, GraphViewerEdge>();
	
	private final Set<GraphViewerVertexFactory> vertexFactories = new HashSet<GraphViewerVertexFactory>();
	private final Set<GraphViewerEdgeFactory> edgeFactories = new HashSet<GraphViewerEdgeFactory>();
	
	private GraphLayout layout = new NullGraphLayout(this);
	
	private final List<MouseListener> vertexMouseListeners = new ArrayList<MouseListener>();
	private final List<MouseListener> edgeMouseListeners = new ArrayList<MouseListener>();
	
	private int selectionMode;
	private Rectangle selectionRectangle;
	private final Set<GraphViewerVertex> selectedVertices = new HashSet<GraphViewerVertex>();
	
	private Map<Object, Object> renderingHints = new HashMap<Object, Object>();
	
	public GraphViewer() {
		addVertexFactory(new DefaultGraphViewerVertexFactory());
		addEdgeFactory(new DefaultGraphViewerEdgeFactory());
		
		MouseHandler moveHandler = new MouseHandler();
		addMouseListener(moveHandler);
		addMouseMotionListener(moveHandler);
		
		MouseListenerHandler listenerHandler = new MouseListenerHandler();
		addMouseListener(listenerHandler);
		
		setSelectionMode(SELECTION_MODE_MULTI);
	}
	
	public GraphViewer(Graph<? extends Object, ? extends Edge> graph) {
		this();
		
		setModel(graph);
	}
	
	public void setModel(Graph<? extends Object, ? extends Edge> graph) {
		if (model != null) {
			if (updateHandler != null) {
				((ListenableGraph<?, ?>) model).addListener(updateHandler);
				updateHandler = null;
			}
			
			synchronized (vertexMap) {
				vertexMap.clear();
			}
			
			synchronized (edgeMap) {
				edgeMap.clear();
			}
		}
		
		if (graph instanceof ListenableGraph<?, ?>) {
			updateHandler = new UpdateHandler();
			((ListenableGraph<?, ?>) graph).addListener(new UpdateHandler());
		}
		
		for (Object vertex : graph.getVertices()) {
			addVertex(vertex);
		}
		
		for (Edge edge : graph.getEdges()) {
			addEdge(edge);
		}
		
		model = graph;
	}
	
	public void setGraphLayout(GraphLayout layout) {
		if (this.layout == layout) return;
		
		synchronized (vertexMap) {
			synchronized (edgeMap) {
				for (GraphViewerVertex vertex : vertexMap.values()) {
					this.layout.addViewerVertex(vertex);
				}
				
				for (GraphViewerEdge edge : edgeMap.values()) {
					this.layout.addViewerEdge(edge);
				}
				
				this.layout = layout;
				
				for (GraphViewerVertex vertex : vertexMap.values()) {
					layout.addViewerVertex(vertex);
				}
				
				for (GraphViewerEdge edge : edgeMap.values()) {
					layout.addViewerEdge(edge);
				}
			}
		}
	}
	
	public void setRenderingHints(Map<?, ?> renderingHints) {
		synchronized (renderingHints) {
			this.renderingHints = new HashMap<Object, Object>(renderingHints);
		}
	}
	
	public void setSelectionMode(int selectionMode) {
		if ((selectionMode < SELECTION_MODE_NONE)
				|| (selectionMode > SELECTION_MODE_MULTI))
			throw new IllegalArgumentException("The value out of range!");
		
		this.selectionMode = selectionMode;
	}
	
	public Graph<? extends Object, ? extends Edge> getModel() {
		return model;
	}
	
	public Map<?, ?> getRenderingHints() {
		synchronized (renderingHints) {
			return new HashMap<Object, Object>(renderingHints);
		}
	}
	
	public int getSelectionMode() {
		return selectionMode;
	}
	
	public Object getSelectedVertex() {
		synchronized (selectedVertices) {
			if (selectedVertices.isEmpty() || (selectedVertices.size() > 1))
				return null;
			return selectedVertices.iterator().next().getVertex();
		}
	}
	
	public Set<Object> getSelectedVertices() {
		Set<Object> result = new HashSet<Object>();
		
		synchronized (selectedVertices) {
			for (GraphViewerVertex vertex : selectedVertices) {
				result.add(vertex.getVertex());
			}
		}
		
		return result;
	}
	
	public Collection<GraphViewerVertex> getGraphViewerVertices() {
		synchronized (vertexMap) {
			return vertexMap.values();
		}
	}
	
	public Collection<GraphViewerEdge> getGraphViewerEdges() {
		synchronized (edgeMap) {
			return edgeMap.values();
		}
	}
	
	public GraphViewerVertex getGraphViewerVertex(Object vertex) {
		synchronized (vertexMap) {
			return vertexMap.get(vertex);
		}
	}
	
	public GraphViewerEdge getGraphViewerEdge(Edge edge) {
		synchronized (edgeMap) {
			return edgeMap.get(edge);
		}
	}
	
	private GraphViewerVertexFactory getSuitableVertexFactory(
			Class<? extends Object> vertexClass) {
		GraphViewerVertexFactory result = null;
		Class<? extends Object> returnClass = Object.class;
		
		synchronized (vertexFactories) {
			for (GraphViewerVertexFactory factory : vertexFactories) {
				Class<? extends Object> factoryVertexClass = factory
						.getVertexClass();
				
				if (returnClass.isAssignableFrom(factoryVertexClass)) {
					result = factory;
					returnClass = factoryVertexClass;
				}
			}
		}
		
		return result;
	}
	
	private GraphViewerEdgeFactory getSuitableEdgeFactory(
			Class<? extends Edge> edgeClass) {
		GraphViewerEdgeFactory result = null;
		Class<? extends Edge> returnClass = Edge.class;
		
		synchronized (edgeFactories) {
			for (GraphViewerEdgeFactory factory : edgeFactories) {
				Class<? extends Edge> factoryEdgeClass = factory.getEdgeClass();
				
				if (returnClass.isAssignableFrom(factoryEdgeClass)) {
					result = factory;
					returnClass = factory.getEdgeClass();
				}
			}
		}
		
		return result;
	}
	
	private void addVertex(Object vertex) {
		GraphViewerVertexFactory factory = getSuitableVertexFactory(vertex
				.getClass());
		GraphViewerVertex viewerVertex = factory.buildVertex(vertex);
		addViewerVertex(viewerVertex);
	}
	
	private void addEdge(Edge edge) {
		GraphViewerEdgeFactory factory = getSuitableEdgeFactory(edge.getClass());
		Set<GraphViewerVertex> vertices = new HashSet<GraphViewerVertex>();
		
		synchronized (vertexMap) {
			for (Object vertex : edge.getVertices()) {
				GraphViewerVertex viewerVertex = vertexMap.get(vertex);
				vertices.add(viewerVertex);
			}
		}
		
		GraphViewerEdge viewerEdge = factory.buildEdge(edge, vertices);
		addViewerEdge(viewerEdge);
	}
	
	private void addViewerVertex(GraphViewerVertex vertex) {
		synchronized (vertexMap) {
			vertexMap.put(vertex.getVertex(), vertex);
		}
		
		vertex.setViewer(this);
		layout.addViewerVertex(vertex);
		
		repaint();
	}
	
	private void addViewerEdge(GraphViewerEdge edge) {
		synchronized (edgeMap) {
			edgeMap.put(edge.getEdge(), edge);
		}
		
		edge.setViewer(this);
		layout.addViewerEdge(edge);
		
		repaint();
	}
	
	public boolean addVertexFactory(GraphViewerVertexFactory factory) {
		synchronized (vertexFactories) {
			return vertexFactories.add(factory);
		}
	}
	
	public boolean addEdgeFactory(GraphViewerEdgeFactory factory) {
		synchronized (edgeFactories) {
			return edgeFactories.add(factory);
		}
	}
	
	public void addVertexMouseListener(MouseListener listener) {
		synchronized (vertexMouseListeners) {
			vertexMouseListeners.add(listener);
		}
	}
	
	public void addEdgeMouseListener(MouseListener listener) {
		synchronized (edgeMouseListeners) {
			edgeMouseListeners.add(listener);
		}
	}
	
	public void addRenderingHint(Object key, Object value) {
		synchronized (renderingHints) {
			renderingHints.put(key, value);
		}
		
		repaint();
	}
	
	private void removeVertex(Object vertex) {
		synchronized (vertexMap) {
			GraphViewerVertex viewerVertex = vertexMap.get(vertex);
			removeViewerVertex(viewerVertex);
		}
	}
	
	private void removeEdge(Edge edge) {
		synchronized (edgeMap) {
			GraphViewerEdge viewerEdge = edgeMap.get(edge);
			removeViewerEdge(viewerEdge);
		}
	}
	
	private void removeViewerVertex(GraphViewerVertex vertex) {
		synchronized (vertexMap) {
			vertexMap.remove(vertex.getVertex());
		}
		
		layout.removeViewerVertex(vertex);
		
		repaint();
	}
	
	private void removeViewerEdge(GraphViewerEdge edge) {
		synchronized (edgeMap) {
			edgeMap.remove(edge.getEdge());
		}
		
		layout.removeViewerEdge(edge);
		
		repaint();
	}
	
	public boolean removeVertexFactory(GraphViewerVertexFactory factory) {
		synchronized (vertexFactories) {
			return vertexFactories.remove(factory);
		}
	}
	
	public boolean removeEdgeFactory(GraphViewerEdgeFactory factory) {
		synchronized (edgeFactories) {
			return edgeFactories.remove(factory);
		}
	}
	
	public void removeRenderingHint(Object key) {
		synchronized (renderingHints) {
			renderingHints.remove(key);
		}
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		
		synchronized (renderingHints) {
			g.setRenderingHints(renderingHints);
		}
		
		layout.paint(g.create());
		
		synchronized (edgeMap) {
			for (GraphViewerEdge edge : edgeMap.values()) {
				edge.paint(g.create());
			}
		}
		
		synchronized (vertexMap) {
			for (GraphViewerVertex vertex : vertexMap.values()) {
				vertex.paint(g.create());
			}
		}
		
		if ((selectionMode == SELECTION_MODE_MULTI)
				&& (selectionRectangle != null)) {
			GraphicsUtil graphicsUtil = new GraphicsUtil(g);
			graphicsUtil.setColor(new Color(1f, 1f, 1f, 0.6f));
			graphicsUtil.fillRectangle(selectionRectangle);
			graphicsUtil.setColor(Color.BLACK);
			graphicsUtil.drawRectangle(selectionRectangle);
		}
	}
	
	private void handleMouseEvent(MouseEvent e) {
		synchronized (vertexMap) {
			for (GraphViewerVertex vertex : vertexMap.values()) {
				if (vertex.intersects(e.getPoint())) {
					e.setSource(vertex.getVertex());
					fireMouseEvent(vertexMouseListeners, e);
					return;
				}
			}
			
			for (GraphViewerEdge edge : edgeMap.values()) {
				if (edge.intersects(e.getPoint())) {
					e.setSource(edge.getEdge());
					fireMouseEvent(edgeMouseListeners, e);
					return;
				}
			}
		}
	}
	
	private void fireMouseEvent(List<MouseListener> listeners, MouseEvent e) {
		synchronized (listeners) {
			for (MouseListener listener : listeners) {
				switch (e.getID()) {
				case MouseEvent.MOUSE_PRESSED:
					listener.mousePressed(e);
					break;
				case MouseEvent.MOUSE_CLICKED:
					listener.mouseClicked(e);
					break;
				case MouseEvent.MOUSE_RELEASED:
					listener.mouseReleased(e);
					break;
				}
			}
		}
	}
	
	@Override
	public void revalidate() {
		super.revalidate();
	}
	
	protected void revalidateVertex(GraphViewerVertex vertex) {
		layout.revalidate(vertex);
		repaint();
	}
	
}