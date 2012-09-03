package at.andiwand.commons.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.andiwand.commons.lwxml.LWXMLEvent;


public class LWXMLBranchReader extends LWXMLFilterReader<LWXMLReader> {
	
	private LWXMLEvent lastEvent;
	private boolean done;
	private int depth;
	
	public LWXMLBranchReader(LWXMLReader in) {
		super(in);
	}
	
	@Override
	public LWXMLEvent getCurrentEvent() {
		if (done) return LWXMLEvent.END_DOCUMENT;
		return lastEvent;
	}
	
	@Override
	public LWXMLEvent readEvent() throws IOException {
		if (done) return LWXMLEvent.END_DOCUMENT;
		
		LWXMLEvent event = in.readEvent();
		if (event == null) return null;
		
		switch (event) {
		case START_ELEMENT:
			depth++;
			break;
		case END_EMPTY_ELEMENT:
		case END_ELEMENT:
			depth--;
			break;
		}
		
		if (depth < 0) {
			done = true;
			return LWXMLEvent.END_DOCUMENT;
		}
		
		return lastEvent = event;
	}
	
	@Override
	public int read() throws IOException {
		if (done) return -1;
		return in.read();
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		if (done) return -1;
		return in.read(cbuf);
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		if (done) return -1;
		return in.read(cbuf, off, len);
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		if (done) return -1;
		return in.read(target);
	}
	
	@Override
	public long skip(long n) throws IOException {
		if (done) return 0;
		return in.skip(n);
	}
	
	@Override
	public void close() throws IOException {
		done = true;
		in.close();
	}
	
}