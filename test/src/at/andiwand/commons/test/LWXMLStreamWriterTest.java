package at.andiwand.commons.test;

import java.io.StringWriter;

import at.andiwand.commons.lwxml.writer.LWXMLStreamWriter;
import at.andiwand.commons.lwxml.writer.LWXMLWriter;

public class LWXMLStreamWriterTest {

    public static void main(String[] args) throws Throwable {
	StringWriter writer = new StringWriter();
	LWXMLWriter lwxmlWriter = new LWXMLStreamWriter(writer);

	lwxmlWriter.writeStartElement("html");
	lwxmlWriter.writeAttribute("name", "value");
	lwxmlWriter.writeStartElement("head");
	lwxmlWriter.writeStartElement("title");
	lwxmlWriter.writeCharacters("html");
	lwxmlWriter.writeEndElement("title");
	lwxmlWriter.writeEndElement("head");
	lwxmlWriter.writeStartElement("body");
	lwxmlWriter.writeStartElement("empty");
	lwxmlWriter.writeEndElement("empty");
	lwxmlWriter.writeEndElement("body");
	lwxmlWriter.writeEndElement("html");

	lwxmlWriter.close();
	System.out.println(writer.toString());
    }

}