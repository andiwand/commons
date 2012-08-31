package at.andiwand.common.xml.namespace;

import java.util.Iterator;


public interface NamespaceContext {
	
	public String getNamespaceURI(String prefix);
	
	public String getPrefix(String namespaceURI);
	
	public Iterator<String> getPrefixes(String namespaceURI);
	
}