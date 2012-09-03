package at.andiwand.commons.lwxml.translator.simple;

import at.andiwand.commons.lwxml.LWXMLAttribute;


public class SimpleStaticAttributeTranslator implements
		SimpleAttributeTranslator {
	
	private final String newAttributeName;
	
	public SimpleStaticAttributeTranslator(String newAttributeName) {
		this.newAttributeName = newAttributeName;
	}
	
	@Override
	public LWXMLAttribute translateAttribute(String name, String value) {
		return new LWXMLAttribute(newAttributeName, value);
	}
	
}