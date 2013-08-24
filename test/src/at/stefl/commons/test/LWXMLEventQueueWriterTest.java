package at.stefl.commons.test;

import java.io.StringWriter;

import at.stefl.commons.lwxml.writer.LWXMLEventQueueWriter;
import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLEventQueueWriterTest {
    
    public static void main(String[] args) throws Throwable {
        LWXMLEventQueueWriter writer = new LWXMLEventQueueWriter();
        
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
        
        StringWriter stringWriter = new StringWriter();
        LWXMLWriter tmpWriter = new LWXMLStreamWriter(stringWriter);
        
        writer.writeTo(tmpWriter);
        
        tmpWriter.close();
        System.out.println(stringWriter.toString());
    }
    
}