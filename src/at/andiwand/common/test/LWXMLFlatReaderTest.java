package at.andiwand.common.test;

import java.io.IOException;
import java.io.InputStream;

import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.reader.LWXMLFlatReader;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.reader.LWXMLStreamReader;


public class LWXMLFlatReaderTest {
	
	public static void main(String[] args) throws IOException {
		InputStream inputStream = LWXMLSimpleTranslatorTest.class
				.getResourceAsStream("test.xml");
		LWXMLReader lwxmlReader = new LWXMLStreamReader(inputStream);
		LWXMLFlatReader in = new LWXMLFlatReader(lwxmlReader);
		
		lwxmlReader.readEvent();
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			System.out.println(event);
			if (event.hasValue())
				System.out.println("value: " + in.readValue());
			System.out.println();
		}
	}
	
}