package at.andiwand.commons.lwxml.translator;

import java.io.IOException;

import at.andiwand.commons.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.commons.lwxml.writer.LWXMLWriter;

public class LWXMLElementReplacement<C> extends LWXMLElementTranslator<C> {

    protected final String elementName;

    public LWXMLElementReplacement(String elementName) {
	this.elementName = elementName;
    }

    @Override
    public void translateStartElement(LWXMLPushbackReader in, LWXMLWriter out,
	    C context) throws IOException {
	out.writeStartElement(elementName);
    }

    @Override
    public void translateEndElement(LWXMLPushbackReader in, LWXMLWriter out,
	    C context) throws IOException {
	out.writeEndElement(elementName);
    }

}