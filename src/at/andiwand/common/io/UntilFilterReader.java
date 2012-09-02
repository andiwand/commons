package at.andiwand.common.io;

import java.io.IOException;
import java.io.Reader;


public class UntilFilterReader extends CharwiseFilterReader {
	
	private boolean closed;
	
	private final CharFilter filter;
	
	public UntilFilterReader(Reader in, CharFilter filter) {
		super(in);
		
		this.filter = filter;
	}
	
	@Override
	public int read() throws IOException {
		if (closed) return -1;
		
		int read = in.read();
		
		if ((read == -1) || !filter.accept((char) read)) {
			closed = true;
			return -1;
		}
		
		return read;
	}
	
}