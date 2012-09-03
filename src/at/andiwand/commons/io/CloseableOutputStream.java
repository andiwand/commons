package at.andiwand.commons.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class CloseableOutputStream extends FilterOutputStream {
	
	private boolean closed;
	
	public CloseableOutputStream(OutputStream out) {
		super(out);
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	@Override
	public void write(int c) throws IOException {
		if (closed) return;
		out.write(c);
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		if (closed) return;
		out.write(b);
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		if (closed) return;
		out.write(b, off, len);
	}
	
	@Override
	public void flush() throws IOException {
		if (closed) return;
		out.flush();
	}
	
	@Override
	public void close() throws IOException {
		if (closed) return;
		closed = true;
		out.flush();
	}
	
}