package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Deque;
import java.util.LinkedList;


// TODO: optimize
public class UnlimitedPushbackInputStream extends PushbackInputStream {
	
	private Deque<Byte> buffer = new LinkedList<Byte>();
	
	public UnlimitedPushbackInputStream(InputStream in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		if (buffer.isEmpty()) return super.read();
		else return buffer.remove();
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int count = 0;
		
		while (!buffer.isEmpty()) {
			int read = read();
			if (read == -1) return count;
			b[off] = (byte) read;
			off++;
			len--;
			count++;
		}
		
		if ((off < b.length) & (len > 0)) count += super.read(b, off, len);
		
		return count;
	}
	
	@Override
	public void unread(int b) throws IOException {
		buffer.offer((byte) b);
	}
	
	@Override
	public void unread(byte[] b) throws IOException {
		for (byte i : b) {
			buffer.offer(i);
		}
	}
	
	@Override
	public void unread(byte[] b, int off, int len) throws IOException {
		for (int i = off; i < (off + len); i++) {
			buffer.offer(b[i]);
		}
	}
	
	@Override
	public int available() throws IOException {
		return buffer.size() + super.available();
	}
	
	@Override
	public void close() throws IOException {
		buffer = null;
		super.close();
	}
	
}