package at.andiwand.commons.lwxml.translator.simple;

import at.andiwand.commons.lwxml.LWXMLAttribute;


// TODO: implement as stream translator
public interface SimpleAttributeTranslator {
	
	public LWXMLAttribute translateAttribute(String name, String value);
	
}