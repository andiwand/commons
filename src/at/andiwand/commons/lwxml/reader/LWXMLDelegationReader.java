package at.andiwand.commons.lwxml.reader;

import java.io.IOException;


public abstract class LWXMLDelegationReader<T extends LWXMLReader> extends
		LWXMLReader {
	
	protected T in;
	
	public LWXMLDelegationReader(T in) {
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