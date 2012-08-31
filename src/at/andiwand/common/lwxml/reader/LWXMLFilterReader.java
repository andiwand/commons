package at.andiwand.common.lwxml.reader;

import java.io.IOException;


public abstract class LWXMLFilterReader<T extends LWXMLReader> extends
		LWXMLReader {
	
	protected final T in;
	
	public LWXMLFilterReader(T in) {
		if (in == null) throw new NullPointerException();
		
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
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