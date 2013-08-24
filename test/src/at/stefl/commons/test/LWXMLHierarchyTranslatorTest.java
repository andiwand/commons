package at.stefl.commons.test;

import java.io.IOException;
import java.io.InputStream;

import at.stefl.commons.io.DividedCharArrayWriter;
import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;
import at.stefl.commons.lwxml.translator.LWXMLHierarchyTranslator;
import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLHierarchyTranslatorTest {
    
    public static void main(String[] args) throws IOException {
        InputStream inputStream = LWXMLHierarchyTranslatorTest.class
                .getResourceAsStream("test.xml");
        LWXMLReader in = new LWXMLStreamReader(new FluidInputStreamReader(
                inputStream));
        
        DividedCharArrayWriter writer = new DividedCharArrayWriter();
        LWXMLWriter out = new LWXMLStreamWriter(writer);
        
        LWXMLHierarchyTranslator<Object> lwxmlTranslator = new LWXMLHierarchyTranslator<Object>();
        lwxmlTranslator.addElementTranslator("html", "xml");
        lwxmlTranslator.addElementTranslator("head", "kopf");
        lwxmlTranslator.addElementTranslator("title", "name");
        lwxmlTranslator.addElementTranslator("body", "bauch");
        lwxmlTranslator.addElementTranslator("empty", "leer");
        lwxmlTranslator.translate(in, out, null);
        
        out.close();
        
        System.out.println(writer);
    }
    
}