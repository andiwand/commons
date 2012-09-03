package at.andiwand.commons.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;


public class ForwardReader extends FilterReader {
	
	public ForwardReader(Reader in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		return CharStreamUtil.readForward(in);
	}
	
	public int read(char[] cbuf) throws IOException {
		return CharStreamUtil.readForward(in, cbuf);
	}
	
	public int read(char[] cbuf, int off, int len) throws IOException {
		return CharStreamUtil.readForward(in, cbuf, off, len);
	}
	
	public int read(java.nio.CharBuffer target) throws IOException {
		return CharStreamUtil.readForward(in, target);
	}
	
}