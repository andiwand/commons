package at.stefl.commons.test;

import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import at.stefl.commons.math.graph.HashUndirectedGraph;
import at.stefl.commons.math.graph.SimpleUndirectedEdge;
import at.stefl.commons.swing.graph.GraphViewer;
import at.stefl.commons.swing.graph.GridGraphLayout;

public class GridGraphLayoutTest {
    
    public static void main(String[] args) {
        HashUndirectedGraph<String, SimpleUndirectedEdge> graph = new HashUndirectedGraph<String, SimpleUndirectedEdge>();
        
        GraphViewer graphViewer = new GraphViewer(graph);
        graphViewer.setGraphLayout(new GridGraphLayout(graphViewer));
        graphViewer.addRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphViewer.addRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        JScrollPane scrollPane = new JScrollPane(graphViewer);
        
        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        graphViewer.addVertexMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e);
            }
        });
        
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));
    }
    
}