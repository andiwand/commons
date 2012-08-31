package at.andiwand.common.test;

import java.io.IOException;
import java.io.InputStream;

import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.reader.LWXMLBranchDelegationReader;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.reader.LWXMLStreamReader;


public class LWXMLBranchDelegationReaderTest {
	
	public static void main(String[] args) throws IOException {
		InputStream inputStream = LWXMLSimpleTranslatorTest.class
				.getResourceAsStream("test.xml");
		LWXMLReader lwxmlReader = new LWXMLStreamReader(inputStream);
		LWXMLBranchDelegationReader in = new LWXMLBranchDelegationReader(
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
			LWXMLEvent event = in.getBranchReader().readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) break;
			
			System.out.println(event);
			if (event.hasValue())
				System.out
						.println("value: " + in.getBranchReader().readValue());
			System.out.println();
		}
		
		System.out.println("---------------");
		
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