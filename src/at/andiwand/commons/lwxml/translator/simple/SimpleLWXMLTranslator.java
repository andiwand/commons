package at.andiwand.commons.lwxml.translator.simple;

import java.io.IOException;
import java.util.Map;

import at.andiwand.commons.io.BlockwiseStringMatcher;
import at.andiwand.commons.lwxml.LWXMLEvent;
import at.andiwand.commons.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.translator.LWXMLTranslator;
import at.andiwand.commons.lwxml.writer.LWXMLWriter;


// TODO: implement character matching map
public class SimpleLWXMLTranslator extends LWXMLTranslator {
	
	private BlockwiseStringMatcher<SimpleElementTranslator> elementTranslatorMap = new BlockwiseStringMatcher<SimpleElementTranslator>();
	
	private BlockwiseStringMatcher<SimpleAttributeTranslator> staticAttributeTranslator = new BlockwiseStringMatcher<SimpleAttributeTranslator>();
	
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
	public void translate(LWXMLReader in, LWXMLWriter out) throws IOException {
		LWXMLPushbackReader pin = new LWXMLPushbackReader(in);
		
		String elementName = null;
		SimpleElementTranslator translator = null;
		
		while (true) {
			LWXMLEvent event = pin.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			switch (event) {
			case START_ELEMENT:
			case END_ELEMENT:
				elementName = pin.readValue();
				translator = elementTranslatorMap.get(elementName);
			case END_EMPTY_ELEMENT:
				if (translator == null) break;
				
				pin.unreadEvent(elementName);
				translator.translate(pin, out);
				
				elementName = null;
				break;
			case CHARACTERS:
				out.writeEvent(LWXMLEvent.CHARACTERS);
				out.write(pin);
				break;
			}
		}
	}
	
}