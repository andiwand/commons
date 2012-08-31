package at.andiwand.common.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Deque;
import java.util.LinkedList;


// TODO: optimize
public class UnlimitedPushbackReader extends PushbackReader {
	
	private Deque<Character> buffer = new LinkedList<Character>();
	
	public UnlimitedPushbackReader(Reader in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		if (buffer.isEmpty()) return super.read();
		else return buffer.remove();
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int count = 0;
		
		while (!buffer.isEmpty()) {
			int read = read();
			if (read == -1) return count;
			cbuf[off] = (char) read;
			off++;
			len--;
			count++;
		}
		
		if ((off < cbuf.length) & (len > 0))
			count += super.read(cbuf, off, len);
		
		return count;
	}
	
	@Override
	public void unread(int c) throws IOException {
		buffer.offer((char) c);
	}
	
	@Override
	public void unread(char[] cbuf) throws IOException {
		for (char c : cbuf) {
			buffer.offer(c);
		}
	}
	
	@Override
	public void unread(char[] cbuf, int off, int len) throws IOException {
		for (int i = off; i < (off + len); i++) {
			buffer.offer(cbuf[i]);
		}
	}
	
	@Override
	public boolean ready() throws IOException {
		return !buffer.isEmpty() || super.ready();
	}
	
	@Override
	public void close() throws IOException {
		buffer = null;
		super.close();
	}
	
}