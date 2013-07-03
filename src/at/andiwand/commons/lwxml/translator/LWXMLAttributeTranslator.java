package at.andiwand.commons.lwxml.translator;

import at.andiwand.commons.lwxml.LWXMLAttribute;

// TODO: implement as stream translator
public interface LWXMLAttributeTranslator<C> {

    public LWXMLAttribute translate(String name, String value, C context);

}