package at.andiwand.commons.test;

import at.andiwand.commons.lwxml.writer.LWXMLEventListWriter;


public class LWXMLEventListWriterTest {
	
	public static void main(String[] args) throws Throwable {
		LWXMLEventListWriter writer = new LWXMLEventListWriter();
		
		writer.writeStartElement("html");
		writer.writeAttribute("name", "value");
		writer.writeStartElement("head");
		writer.writeStartElement("title");
		writer.writeCharacters("html");
		writer.writeEndElement("title");
		writer.writeEndElement("head");
		writer.writeStartElement("body");
		writer.writeStartElement("empty");
		writer.writeEndElement("empty");
		writer.writeEndElement("body");
		writer.writeEndElement("html");
		
		writer.close();
	}
	
}