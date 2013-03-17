package at.andiwand.commons.math.graph;

import java.util.Collection;


public interface Edge {
	
	public String toString();
	
	public boolean equals(Object obj);
	
	public int hashCode();
	
	public int getVertexCount();
	
	public Collection<? extends Object> getVertices();
	
	public boolean contains(Object vertex);
	
}