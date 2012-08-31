package at.andiwand.common.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;


public class TeeWriter extends FilterWriter {
	
	private final Writer tee;
	
	public TeeWriter(Writer out, Writer tee) {
		super(out);
		
		this.tee = tee;
	}
	
	@Override
	public void write(int c) throws IOException {
		out.write(c);
		tee.write(c);
	}
	
	@Override
	public void write(char[] cbuf) throws IOException {
		out.write(cbuf);
		tee.write(cbuf);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		out.write(cbuf, off, len);
		tee.write(cbuf, off, len);
	}
	
	@Override
	public void flush() throws IOException {
		out.flush();
		tee.flush();
	}
	
}