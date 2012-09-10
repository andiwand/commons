package at.andiwand.commons.lwxml.translator.simple;

import java.io.IOException;
import java.util.Map;

import at.andiwand.commons.io.BlockwiseStringMatcher;
import at.andiwand.commons.lwxml.LWXMLEvent;
import at.andiwand.commons.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.translator.LWXMLTranslator;
import at.andiwand.commons.lwxml.writer.LWXMLWriter;
import at.andiwand.commons.util.collection.OrderedPair;


// TODO: implement remove methods
public class SimpleLWXMLTranslator extends LWXMLTranslator {
	
	private BlockwiseStringMatcher<SimpleElementTranslator> elementTranslators = new BlockwiseStringMatcher<SimpleElementTranslator>();
	
	private BlockwiseStringMatcher<SimpleAttributeTranslator> staticAttributeTranslators = new BlockwiseStringMatcher<SimpleAttributeTranslator>();
	
	public void addElementTranslator(String elementName, String newElementName) {
		addElementTranslator(elementName, new SimpleElementReplacement(
				newElementName));
	}
	
	public void addElementTranslator(String elementName,
			SimpleElementTranslator translator) {
		if (elementName == null) throw new NullPointerException();
		elementTranslators.put(elementName, translator);
		
		for (Map.Entry<String, SimpleAttributeTranslator> entry : staticAttributeTranslators
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
		staticAttributeTranslators.put(attributeName, attributeTranslator);
		
		for (SimpleElementTranslator translator : elementTranslators.values()) {
			translator.addAttributeTranslator(attributeName,
					attributeTranslator);
		}
	}
	
	@Override
	public void translate(LWXMLReader in, LWXMLWriter out) throws IOException {
		LWXMLPushbackReader pin = new LWXMLPushbackReader(in);
		
		OrderedPair<String, SimpleElementTranslator> match = null;
		
		while (true) {
			LWXMLEvent event = pin.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			switch (event) {
			case START_ELEMENT:
			case END_ELEMENT:
				match = elementTranslators.match(pin);
			case END_EMPTY_ELEMENT:
				if (match == null) break;
				
				String elementName = match.getElement1();
				SimpleElementTranslator translator = match.getElement2();
				
				pin.unreadEvent(elementName);
				translator.translate(pin, out);
				
				break;
			case CHARACTERS:
				out.writeEvent(LWXMLEvent.CHARACTERS);
				out.write(pin);
				break;
			}
		}
	}
	
}