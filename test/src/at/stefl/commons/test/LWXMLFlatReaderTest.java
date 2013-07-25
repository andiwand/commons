package at.stefl.commons.test;

import java.io.IOException;
import java.io.InputStream;

import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLFlatReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLFlatReaderTest {

    public static void main(String[] args) throws IOException {
	InputStream inputStream = LWXMLFlatReaderTest.class
		.getResourceAsStream("test.xml");
	LWXMLReader lwxmlReader = new LWXMLStreamReader(
		new FluidInputStreamReader(inputStream));
	LWXMLFlatReader in = new LWXMLFlatReader(lwxmlReader);

	lwxmlReader.readEvent();

	while (true) {
	    LWXMLEvent event = in.readEvent();
	    if (event == LWXMLEvent.END_DOCUMENT)
		break;

	    System.out.println(event);
	    if (event.hasValue())
		System.out.println("value: " + in.readValue());
	    System.out.println();
	}

	in.close();
    }

}