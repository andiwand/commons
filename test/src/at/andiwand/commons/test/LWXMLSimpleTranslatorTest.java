package at.andiwand.commons.test;

import java.io.IOException;
import java.io.InputStream;

import at.andiwand.commons.io.CharArrayWriter;
import at.andiwand.commons.io.FluidInputStreamReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.reader.LWXMLStreamReader;
import at.andiwand.commons.lwxml.translator.simple.SimpleLWXMLTranslator;
import at.andiwand.commons.lwxml.writer.LWXMLStreamWriter;
import at.andiwand.commons.lwxml.writer.LWXMLWriter;

public class LWXMLSimpleTranslatorTest {

    public static void main(String[] args) throws IOException {
	InputStream inputStream = LWXMLSimpleTranslatorTest.class
		.getResourceAsStream("test.xml");
	LWXMLReader in = new LWXMLStreamReader(new FluidInputStreamReader(
		inputStream));

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