package at.andiwand.common.lwxml.translator.simple;

import java.io.IOException;

import at.andiwand.common.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


public class SimpleElementReplacement extends SimpleElementTranslator {
	
	private final String newElementName;
	
	public SimpleElementReplacement(String newElementName) {
		this.newElementName = newElementName;
	}
	
	public String getNewElementName() {
		return newElementName;
	}
	
	@Override
	public void translateStartElement(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException {
		out.writeStartElement(newElementName);
	}
	
	@Override
	public void translateEndElement(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException {
		out.writeEndElement(newElementName);
	}
	
}