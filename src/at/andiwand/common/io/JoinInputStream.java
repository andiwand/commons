package at.andiwand.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


// TODO: improve
public class JoinInputStream extends InputStream {
	
	private InputStream[] ins;
	private int index;
	
	public JoinInputStream(InputStream... ins) {
		this.ins = Arrays.copyOf(ins, ins.length);
	}
	
	public int getStreamIndex() {
		return index;
	}
	
	public int getStreamCount() {
		return ins.length;
	}
	
	public boolean isStreamAvailable() {
		return index < ins.length;
	}
	
	@Override
	public int read() throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read();
		} while (result == -1);
		
		return result;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read(b);
		} while (result == -1);
		
		return result;
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read(b, off, len);
		} while (result == -1);
		
		return result;
	}
	
	// TODO: improve
	@Override
	public long skip(long n) throws IOException {
		if (!isStreamAvailable()) return 0;
		
		return ByteStreamUtil.skipBytewise(this, n);
	}
	
	@Override
	public int available() throws IOException {
		if (!isStreamAvailable()) return 0;
		
		return ins[index].available();
	}
	
	// TODO: improve
	@Override
	public boolean markSupported() {
		return false;
	}
	
	// TODO: improve
	@Override
	public synchronized void mark(int readlimit) {}
	
	// TODO: improve
	@Override
	public synchronized void reset() throws IOException {}
	
	@Override
	public void close() throws IOException {
		IOException last = null;
		
		for (; index < ins.length; index++) {
			try {
				ins[index].close();
			} catch (IOException e) {
				last = e;
			}
		}
		
		if (last != null) throw last;
	}
	
}