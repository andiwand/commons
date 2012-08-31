package at.andiwand.common.lwxml.translator;

import java.io.IOException;

import at.andiwand.common.lwxml.reader.LWXMLPushbackReader;
import at.andiwand.common.lwxml.reader.LWXMLReader;
import at.andiwand.common.lwxml.writer.LWXMLWriter;


public abstract class LWXMLPushbackTranslator extends LWXMLTranslator {
	
	@Override
	public void translate(LWXMLReader in, LWXMLWriter out) throws IOException {
		if (!(in instanceof LWXMLPushbackReader))
			throw new IllegalArgumentException("illigal reader instance");
		translate((LWXMLPushbackReader) in, out);
	}
	
	public abstract void translate(LWXMLPushbackReader in, LWXMLWriter out)
			throws IOException;
	
}