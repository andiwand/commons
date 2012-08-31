package at.andiwand.common.lwxml.translator.simple;

import at.andiwand.common.lwxml.LWXMLAttribute;


// TODO: implement as stream translator
public interface SimpleAttributeTranslator {
	
	public LWXMLAttribute translateAttribute(String name, String value);
	
}