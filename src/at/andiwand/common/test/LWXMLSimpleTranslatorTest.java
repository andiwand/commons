package at.andiwand.common.test;

import java.io.IOException;
import java.io.InputStream;

import at.andiwand.common.io.CharArrayWriter;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.reader.LWXMLStreamReader;
import at.andiwand.common.lwxml.translator.simple.SimpleLWXMLTranslator;
import at.andiwand.common.lwxml.writer.LWXMLStreamWriter;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


public class LWXMLSimpleTranslatorTest {
	
	public static void main(String[] args) throws IOException {
		InputStream inputStream = LWXMLSimpleTranslatorTest.class
				.getResourceAsStream("test.xml");
		LWXMLReader in = new LWXMLStreamReader(inputStream);
		
		CharArrayWriter writer = new CharArrayWriter();
		LWXMLWriter out = new LWXMLStreamWriter(writer);
		
		SimpleLWXMLTranslator lwxmlTranslator = new SimpleLWXMLTranslator();
		lwxmlTranslator.addElementTranslator("html", "xml");
		lwxmlTranslator.addElementTranslator("head", "kopf");
		lwxmlTranslator.addElementTranslator("title", "name");
		lwxmlTranslator.addElementTranslator("body", "bauch");
		lwxmlTranslator.addElementTranslator("empty", "leer");
		lwxmlTranslator.addStaticAttributeTranslator("name", "attribute");
		lwxmlTranslator.translate(in, out);
		
		out.close();
		
		System.out.println(writer);
	}
	
}