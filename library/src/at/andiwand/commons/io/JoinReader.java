package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Arrays;


// TODO: improve
public class JoinReader extends Reader {
	
	private Reader[] ins;
	private int index;
	
	public JoinReader(Reader... ins) {
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
	public int read(char[] cbuf) throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read(cbuf);
		} while (result == -1);
		
		return result;
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read(cbuf, off, len);
		} while (result == -1);
		
		return result;
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		int result;
		
		do {
			if (!isStreamAvailable()) return -1;
			result = ins[index].read(target);
		} while (result == -1);
		
		return result;
	}
	
	// TODO: improve
	@Override
	public long skip(long n) throws IOException {
		if (!isStreamAvailable()) return 0;
		
		return CharStreamUtil.skipCharwise(this, n);
	}
	
	@Override
	public boolean ready() throws IOException {
		if (!isStreamAvailable()) return false;
		
		return ins[index].ready();
	}
	
	// TODO: improve
	@Override
	public boolean markSupported() {
		return false;
	}
	
	// TODO: improve
	@Override
	public void mark(int readAheadLimit) throws IOException {}
	
	// TODO: improve
	@Override
	public void reset() throws IOException {}
	
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