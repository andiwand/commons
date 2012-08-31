package at.andiwand.common.lwxml.translator.simple;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import at.andiwand.common.lwxml.LWXMLAttribute;
import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.LWXMLIllegalEventException;
import at.andiwand.common.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.common.lwxml.translator.LWXMLPushbackTranslator;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


public abstract class SimpleElementTranslator extends LWXMLPushbackTranslator {
	
	private final Map<String, SimpleAttributeTranslator> attributeTranslatorMap = new HashMap<String, SimpleAttributeTranslator>();
	
	private final Set<String> parseAttributes = new HashSet<String>();
	private final Map<String, String> currentParsedAttributes = new HashMap<String, String>();
	
	private final Map<String, String> newAttributes = new HashMap<String, String>();
	
	public Map<String, String> getCurrentParsedAttributes() {
		return currentParsedAttributes;
	}
	
	public String getCurrentParsedAttribute(String attributeName) {
		return currentParsedAttributes.get(attributeName);
	}
	
	public void addAttributeTranslator(String attributeName,
			String newAttributeName) {
		addAttributeTranslator(attributeName,
				new SimpleStaticAttributeTranslator(newAttributeName));
	}
	
	public void addAttributeTranslator(String attributeName,
			SimpleAttributeTranslator translator) {
		attributeTranslatorMap.put(attributeName, translator);
	}
	
	public void addParseAttribute(String attributeName) {
		parseAttributes.add(attributeName);
	}
	
	public void addNewAttribute(LWXMLAttribute attribute) {
		if (attribute == null) throw new NullPointerException();
		newAttributes.put(attribute.getName(), attribute.getValue());
	}
	
	public void addNewAttribute(String name, String value) {
		addNewAttribute(new LWXMLAttribute(name, value));
	}
	
	public void removeAttributeTranslator(String attributeName) {
		attributeTranslatorMap.remove(attributeName);
	}
	
	public void removeParseAttribute(String attributeName) {
		parseAttributes.remove(attributeName);
	}
	
	public void removeNewAttribute(String name) {
		newAttributes.remove(name);
	}
	
	@Override
	public final void translate(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException {
		LWXMLEvent event = in.readEvent();
		
		switch (event) {
		case START_ELEMENT:
			translateStartElement(in, out);
			translateAttributeList(in, out);
			translateEndAttributeList(in, out);
			translateChildren(in, out);
			break;
		case END_EMPTY_ELEMENT:
		case END_ELEMENT:
			translateEndElement(in, out);
			break;
		default:
			throw new LWXMLIllegalEventException(event);
		}
	}
	
	public abstract void translateStartElement(LWXMLPushbackReader in,
			LWXMLWriter out) throws IOException;
	
	public LWXMLAttribute translateAttribute(LWXMLPushbackReader in,
			LWXMLWriter out) throws IOException {
		String attributeName = in.readValue();
		String attributeValue = null;
		
		if (parseAttributes.contains(attributeName)) {
			attributeValue = in.readFollowingValue();
			currentParsedAttributes.put(attributeName, attributeValue);
		}
		
		SimpleAttributeTranslator attributeTranslator = attributeTranslatorMap
				.get(attributeName);
		if (attributeTranslator == null) return null;
		
		if (attributeValue == null) attributeValue = in.readFollowingValue();
		LWXMLAttribute result = attributeTranslator.translateAttribute(
				attributeName, attributeValue);
		return result;
	}
	
	public void translateAttributeList(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException {
		for (Map.Entry<String, String> entry : newAttributes.entrySet()) {
			out.writeAttribute(entry.getKey(), entry.getValue());
		}
		
		loop:
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				LWXMLAttribute attribute = translateAttribute(in, out);
				if (attribute != null) out.writeAttribute(attribute);
			case ATTRIBUTE_VALUE:
				break;
			case END_ATTRIBUTE_LIST:
				break loop;
			default:
				throw new LWXMLIllegalEventException(event);
			}
		}
	}
	
	public void translateEndAttributeList(LWXMLPushbackReader in,
			LWXMLWriter out) throws IOException {
		currentParsedAttributes.clear();
		
		out.writeEvent(LWXMLEvent.END_ATTRIBUTE_LIST);
	}
	
	public void translateChildren(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException {}
	
	public abstract void translateEndElement(LWXMLPushbackReader in,
			LWXMLWriter out) throws IOException;
	
}