package at.stefl.commons.test;

import at.stefl.commons.math.graph.Edge;
import at.stefl.commons.math.graph.GraphAdapter;
import at.stefl.commons.math.graph.HashUndirectedGraph;
import at.stefl.commons.math.graph.SimpleUndirectedEdge;

public class HashUndirectedGraphTest {
    
    public static void main(String[] args) {
        HashUndirectedGraph<String, SimpleUndirectedEdge> graph = new HashUndirectedGraph<String, SimpleUndirectedEdge>();
        graph.addListener(new GraphAdapter() {
            
            @Override
            public void vertexAdded(Object vertex) {
                System.out.println("a " + vertex);
            }
            
            @Override
            public void edgeAdded(Edge edge) {
                System.out.println("a " + edge);
            }
            
            @Override
            public void vertexRemoved(Object vertex) {
                System.out.println("r " + vertex);
            }
            
            @Override
            public void edgeRemoved(Edge edge) {
                System.out.println("r " + edge);
            }
        });
        
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));
        
        System.out.println(graph);
        
        graph.removeVertex("b");
        
        System.out.println(graph);
    }
    
}