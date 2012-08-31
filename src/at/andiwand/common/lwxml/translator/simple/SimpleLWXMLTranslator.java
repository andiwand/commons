package at.andiwand.common.lwxml.translator.simple;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.translator.LWXMLTranslator;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


// TODO: implement character matching map
public class SimpleLWXMLTranslator extends LWXMLTranslator {
	
	private Map<String, SimpleElementTranslator> elementTranslatorMap = new HashMap<String, SimpleElementTranslator>();
	
	private Map<String, SimpleAttributeTranslator> staticAttributeTranslator = new HashMap<String, SimpleAttributeTranslator>();
	
	public void addElementTranslator(String elementName, String newElementName) {
		addElementTranslator(elementName, new SimpleElementReplacement(
				newElementName));
	}
	
	public void addElementTranslator(String elementName,
			SimpleElementTranslator translator) {
		if (elementName == null) throw new NullPointerException();
		elementTranslatorMap.put(elementName, translator);
		
		for (Map.Entry<String, SimpleAttributeTranslator> entry : staticAttributeTranslator
				.entrySet()) {
			translator.addAttributeTranslator(entry.getKey(), entry.getValue());
		}
	}
	
	public void addStaticAttributeTranslator(String attributeName,
			String newAttributeName) {
		addStaticAttributeTranslator(attributeName,
				new SimpleStaticAttributeTranslator(newAttributeName));
	}
	
	public void addStaticAttributeTranslator(String attributeName,
			SimpleAttributeTranslator attributeTranslator) {
		if (attributeTranslator == null) throw new NullPointerException();
		staticAttributeTranslator.put(attributeName, attributeTranslator);
		
		for (SimpleElementTranslator translator : elementTranslatorMap.values()) {
			translator.addAttributeTranslator(attributeName,
					attributeTranslator);
		}
	}
	
	public void removeElementTranslator(String elementName) {
		SimpleElementTranslator translator = elementTranslatorMap
				.remove(elementName);
		if (translator == null) return;
		
		for (String attributeName : staticAttributeTranslator.keySet()) {
			translator.removeAttributeTranslator(attributeName);
		}
	}
	
	public void removeStaticAttributeTranslator(String attributeName) {
		if (staticAttributeTranslator.remove(attributeName) == null) return;
		
		for (SimpleElementTranslator translator : elementTranslatorMap.values()) {
			translator.removeAttributeTranslator(attributeName);
		}
	}
	
	@Override
	public void translate(LWXMLReader reader, LWXMLWriter out)
			throws IOException {
		LWXMLPushbackReader in = new LWXMLPushbackReader(reader);
		
		String elementName = null;
		SimpleElementTranslator translator = null;
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			switch (event) {
			case START_ELEMENT:
			case END_ELEMENT:
				elementName = in.readValue();
				translator = elementTranslatorMap.get(elementName);
			case END_EMPTY_ELEMENT:
				if (translator == null) break;
				
				in.unreadEvent(elementName);
				translator.translate(in, out);
				
				elementName = null;
				break;
			case CHARACTERS:
				out.writeEvent(LWXMLEvent.CHARACTERS);
				out.write(in);
				break;
			}
		}
	}
	
}