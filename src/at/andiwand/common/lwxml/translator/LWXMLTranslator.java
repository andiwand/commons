package at.andiwand.common.lwxml.translator;

import java.io.IOException;

import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


public abstract class LWXMLTranslator {
	
	public abstract void translate(LWXMLReader in, LWXMLWriter out)
			throws IOException;
	
}