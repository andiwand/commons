package at.stefl.commons.lwxml.translator;

import at.stefl.commons.lwxml.LWXMLAttribute;

// TODO: implement as stream translator
public interface LWXMLAttributeTranslator<C> {
    
    public LWXMLAttribute translate(String name, String value, C context);
    
}