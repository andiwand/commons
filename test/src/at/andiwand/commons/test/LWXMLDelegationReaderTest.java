package at.andiwand.commons.test;

import java.io.IOException;
import java.io.InputStream;

import at.andiwand.commons.io.FluidInputStreamReader;
import at.andiwand.commons.lwxml.LWXMLEvent;
import at.andiwand.commons.lwxml.reader.LWXMLElementDelegationReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLDelegationReaderTest {

    public static void main(String[] args) throws IOException {
	InputStream inputStream = LWXMLDelegationReaderTest.class
		.getResourceAsStream("test.xml");
	LWXMLReader lwxmlReader = new LWXMLStreamReader(
		new FluidInputStreamReader(inputStream));
	LWXMLElementDelegationReader in = new LWXMLElementDelegationReader(
		lwxmlReader);

	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println(in.readEvent());
	System.out.println();

	System.out.println("---------------");

	while (true) {
	    LWXMLEvent event = in.getElementReader().readEvent();
	    if (event == LWXMLEvent.END_DOCUMENT)
		break;

	    System.out.println(event);
	    if (event.hasValue())
		System.out.println("value: "
			+ in.getElementReader().readValue());
	    System.out.println();
	}

	System.out.println("---------------");

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