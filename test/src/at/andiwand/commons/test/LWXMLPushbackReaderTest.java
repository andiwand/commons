package at.andiwand.commons.test;

import java.io.InputStream;

import at.andiwand.commons.io.FluidInputStreamReader;
import at.andiwand.commons.lwxml.LWXMLEvent;
import at.andiwand.commons.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLPushbackReaderTest {

    public static void main(String[] args) throws Throwable {
	InputStream inputStream = LWXMLSimpleTranslatorTest.class
		.getResourceAsStream("test.xml");
	LWXMLReader lwxmlReader = new LWXMLStreamReader(
		new FluidInputStreamReader(inputStream));
	LWXMLPushbackReader in = new LWXMLPushbackReader(lwxmlReader);

	System.out.println(in.readEvent());

	in.unreadEvent(LWXMLEvent.ATTRIBUTE_VALUE, "value");
	in.unreadEvent(LWXMLEvent.ATTRIBUTE_NAME, "name");

	while (true) {
	    LWXMLEvent event = in.readEvent();
	    if (event == LWXMLEvent.END_DOCUMENT)
		break;

	    System.out.println(event);
	    if (event.hasValue())
		System.out.println(in.readValue());
	}

	in.close();
    }

}