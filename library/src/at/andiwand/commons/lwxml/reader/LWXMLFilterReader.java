package at.andiwand.commons.lwxml.reader;

import java.io.IOException;

import at.andiwand.commons.lwxml.LWXMLEvent;


// TODO: improve
public abstract class LWXMLFilterReader extends LWXMLReader {
	
	protected final LWXMLReader in;
	
	public LWXMLFilterReader(LWXMLReader in) {
		if (in == null) throw new NullPointerException();
		
		this.in = in;
	}
	
	@Override
	public LWXMLEvent getCurrentEvent() {
		return in.getCurrentEvent();
	}
	
	@Override
	public boolean ready() throws IOException {
		return in.ready();
	}
	
	@Override
	public void close() throws IOException {
		in.close();
	}
	
}