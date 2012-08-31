package at.andiwand.common.test;

import java.io.InputStream;

import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.reader.LWXMLStreamReader;


public class LWXMLPushbackReaderTest {
	
	public static void main(String[] args) throws Throwable {
		InputStream inputStream = LWXMLSimpleTranslatorTest.class
				.getResourceAsStream("test.xml");
		LWXMLReader lwxmlReader = new LWXMLStreamReader(inputStream);
		LWXMLPushbackReader in = new LWXMLPushbackReader(lwxmlReader);
		
		System.out.println(in.readEvent());
		
		in.unreadEvent(LWXMLEvent.ATTRIBUTE_VALUE, "value");
		in.unreadEvent(LWXMLEvent.ATTRIBUTE_NAME, "name");
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			System.out.println(event);
			if (event.hasValue()) System.out.println(in.readValue());
		}
	}
	
}